package com.webterminal.backendterminal.connect.ssh.model;

import com.webterminal.backendterminal.connect.ssh.sshEnum.OperateType;

/**
 * @ClassName webSshData
 * @Description 界面数据，两张状态，新增和命令
 * @Author cwt
 * @Date 2022/10/5 18:09
 */
public class WebSshData<T> {
    private String command;

    private OperateType operate;

    private  T clientSession;
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public OperateType getOperate() {
        return operate;
    }

    public void setOperate(OperateType operate) {
        this.operate = operate;
    }

    public T getClientSession() {
        return clientSession;
    }

    public void setClientSession(T clientSession) {
        this.clientSession = clientSession;
    }
}
