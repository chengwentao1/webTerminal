package com.webterminal.backendterminal.connect.ssh.model;

import com.webterminal.backendterminal.connect.ssh.sshEnum.AuthType;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @ClassName SshConntectInfo
 * @Description
 * @Author cwt
 * @Date 2022/10/3 21:25
 */
public class SshConnectInfo {

    /**
     * 认证类型：PASSWORD/KEYPAIR
     */
    private AuthType authType=AuthType.PASSWORD ;

    /**
     * ip
     */
    private String ip = "118.178.105.247";

    /**
     * ssh 端口
     */
    private Integer port = 22;

    /**
     * ssh用户
     */
    private String username = "root";

    /**
     * ssh密码
     */
    private String password="123456@Cwt";

    /**
     * 密钥对类型， 默认ssh-rsa
     */
    private final static String KEYPAIRTYPE = "ssh-rsa";

    /**
     * 公钥路径，默认~/.ssh/id_rsa.pub
     */
    private PublicKey publicKey;

    /**
     * 私玥路径， 默认~/.ssh/id_rsa
     */
    private PrivateKey privateKey;


    /**
     * 认证超时时间
     */
    private Long authTimeout = 10000L;
    /**
     * 执行超时时间
     */
    private Long executeTimeout = 60000L;



    public AuthType getAuthType() {
        return authType;
    }

    public void setAuthType(AuthType authType) {
        this.authType = authType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public Long getAuthTimeout() {
        return authTimeout;
    }

    public void setAuthTimeout(Long authTimeout) {
        this.authTimeout = authTimeout;
    }

    public Long getExecuteTimeout() {
        return executeTimeout;
    }

    public void setExecuteTimeout(Long executeTimeout) {
        this.executeTimeout = executeTimeout;
    }


}
