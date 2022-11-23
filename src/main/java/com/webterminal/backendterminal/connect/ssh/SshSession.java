package com.webterminal.backendterminal.connect.ssh;

import com.webterminal.backendterminal.connect.ssh.model.WebSshData;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.common.session.Session;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * @ClassName SshSession
 * @Description
 * @Author cwt
 * @Date 2022/10/3 22:15
 */
public interface SshSession {
    /**
     *
    * 实现s开启mina-ssh客户端
    * */

    SshSession startSsh() throws IOException;
    /**
     *
     * 执行命令
     * */
    <T> T  processSsh(WebSshData webSshData) throws IOException;


    /**
     *
     * 关闭请求
     * */
    SshSession closeSsh();

    /**
     *
     * 拿到请求
     * */
    ClientSession getSession();

}
