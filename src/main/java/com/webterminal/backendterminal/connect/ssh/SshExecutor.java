package com.webterminal.backendterminal.connect.ssh;

import com.webterminal.backendterminal.connect.ssh.model.WebSshData;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * @ClassName Executor
 * @Description
 * @Author cwt
 * @Date 2022/10/4 13:54
 */
public interface SshExecutor{
    /**
     * 执行器
     *
     */
    <T> T process(WebSshData webSshData) throws IOException;
    void close() throws IOException;
}
