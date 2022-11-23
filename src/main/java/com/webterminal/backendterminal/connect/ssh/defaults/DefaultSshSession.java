package com.webterminal.backendterminal.connect.ssh.defaults;

import com.webterminal.backendterminal.connect.ssh.model.SshConnectInfo;
import com.webterminal.backendterminal.connect.ssh.SshExecutor;
import com.webterminal.backendterminal.connect.ssh.SshSession;
import com.webterminal.backendterminal.connect.ssh.model.WebSshData;
import org.apache.sshd.client.ClientBuilder;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.session.ClientSession;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.security.KeyPair;

/**
 * @ClassName DefaultSshSession
 * @Description sshseesion
 * @Author cwt
 * @Date 2022/10/3 21:21
 */
public class DefaultSshSession implements SshSession {
    private SshClient client;
    private ClientSession session;
    private SshConnectInfo sshInfo;
    private SshExecutor sshExecutor;


    public DefaultSshSession(SshConnectInfo info) {
        sshInfo = info;
        client = ClientBuilder.builder().build();
    }


    public ClientSession getSession() {
        return this.session;
    }


    @Override
    public SshSession startSsh() throws IOException {
        if (client.isStarted()) {
            return this;
        }
        client.start();
        return initSession();
    }

    private SshSession initSession() {
        try {
            if (session != null && session.isOpen()) {
                return this;
            }
            session = client.connect(sshInfo.getUsername(), sshInfo.getIp(), sshInfo.getPort()).verify(2000).getClientSession();
            switch (sshInfo.getAuthType()) {
                case PASSWORD:
                    session.addPasswordIdentity(sshInfo.getPassword());
                    break;
                case KEYPAIR:
                    session.addPublicKeyIdentity(new KeyPair(sshInfo.getPublicKey(), sshInfo.getPrivateKey()));
            }
            session.auth().verify(sshInfo.getAuthTimeout());
        } catch (IOException e) {
            throw new RuntimeException("获取请求报错" + e);
        }
        return this;
    }

    /**
     * @param WebSshData 执行的命令
     */
    @Override
    public <T> T processSsh(WebSshData webSshData) throws IOException {

        if (webSshData.getClientSession() instanceof String) {
//            sshExecutor= new DefaultSshExecutor(this,sshInfo);
        } else if (webSshData.getClientSession() instanceof WebSocketSession) {
            if(sshExecutor==null){
                sshExecutor = new WebSocketExecutor(this, sshInfo);
            }
        }
        return sshExecutor.process(webSshData);
    }

    @Override
    public SshSession closeSsh() {

        try {
            if(sshExecutor!=null){
                sshExecutor.close();
            }
            if (session != null || session.isOpen()) {
                session.close();
            }
            if (client != null || client.isOpen()) {
                client.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("关闭会话" + e);
        }
        return null;
    }
}
