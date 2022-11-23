package com.webterminal.backendterminal.connect.ssh.defaults;

import com.webterminal.backendterminal.connect.ssh.model.SshConnectInfo;
import com.webterminal.backendterminal.connect.ssh.SshExecutor;
import com.webterminal.backendterminal.connect.ssh.SshSession;
import com.webterminal.backendterminal.connect.ssh.model.WebSshData;
import org.apache.sshd.client.channel.ClientChannel;
import org.apache.sshd.client.channel.ClientChannelEvent;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.common.util.io.output.NoCloseOutputStream;
import org.springframework.web.socket.WebSocketSession;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.EnumSet;


/**
 * @ClassName SshExecutor
 * @Description 执行器
 * @Author cwt
 * @Date 2022/10/3 22:46
 */
public abstract class BaseSshExecutor implements SshExecutor {
    protected SshConnectInfo sshConnectInfo;

    protected ClientChannel channel;

    protected SshSession session;

    public BaseSshExecutor(SshSession clientSession, SshConnectInfo info) {
        session = clientSession;
        sshConnectInfo = info;
        initChannel();
    }

    private void initChannel(){
        if(channel==null){
            try {
                channel= session.getSession().createShellChannel();
                channel.setRedirectErrorStream(true);
                channel.open().verify(sshConnectInfo.getExecuteTimeout());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected abstract <T> T doProcess(WebSshData webSshData) throws IOException;
    @Override
    public <T> T process(WebSshData webSshData) throws IOException {
               return this.doProcess(webSshData);
    }
    /**
     * 关闭通道
     * */
    public void close() throws IOException {
        if(channel!=null&&!channel.isClosed()){
            channel.close();
        }
    }
}
