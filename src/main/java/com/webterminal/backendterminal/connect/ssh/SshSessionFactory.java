package com.webterminal.backendterminal.connect.ssh;

/**
 * @ClassName SshSessionFactory
 * @Description 链接的session接口
 * @Author cwt
 * @Date 2022/10/3 13:32
 */
public interface SshSessionFactory {
    SshSession getSession();
}
