package com.webterminal.backendterminal.connect.ssh.sshEnum;

/**
 * @ClassName AuthType
 * @Description 登录的模式，两种种是钥匙对，一个是密码
 * @Author cwt
 * @Date 2022/10/3 22:00
 */
public enum AuthType {
    /**
     * 密码认证
     */
    PASSWORD,
    /**
     * 密钥对认证
     */
    KEYPAIR;
}
