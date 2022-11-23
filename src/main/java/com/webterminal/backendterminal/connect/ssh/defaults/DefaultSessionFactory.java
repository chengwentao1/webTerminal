package com.webterminal.backendterminal.connect.ssh.defaults;

import com.webterminal.backendterminal.connect.ssh.SshSession;
import com.webterminal.backendterminal.connect.ssh.SshSessionFactory;
import com.webterminal.backendterminal.connect.ssh.model.SshConnectInfo;
import org.apache.sshd.client.session.ClientSession;

/**
 * @ClassName DefaultSesionFactory
 * @Description
 * @Author cwt
 * @Date 2022/10/4 20:09
 */
public class DefaultSessionFactory implements SshSessionFactory {
    private SshConnectInfo sshInfo;
    public DefaultSessionFactory(SshConnectInfo info){
        sshInfo=info;
    }
    @Override
    public SshSession getSession() {
        return new DefaultSshSession(sshInfo);
    }
}
