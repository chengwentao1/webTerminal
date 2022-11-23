package com.webterminal.backendterminal.websocket;


import com.alibaba.fastjson2.JSON;
import com.webterminal.backendterminal.connect.ssh.SshSession;
import com.webterminal.backendterminal.connect.ssh.defaults.DefaultSshSession;
import com.webterminal.backendterminal.connect.ssh.model.SshConnectInfo;
import com.webterminal.backendterminal.connect.ssh.model.WebSshData;
import com.webterminal.backendterminal.connect.ssh.sshEnum.OperateType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;


/**
* @Description: WebSSH的WebSocket处理器
* @Author: NoCortY
* @Date: 2020/3/8
*/
@Component
public class WebSSHWebSocketHandler implements WebSocketHandler{
    private Logger logger = LoggerFactory.getLogger(WebSSHWebSocketHandler.class);

    private SshSession sshSession;
    public WebSSHWebSocketHandler(){
        sshSession= new DefaultSshSession(new SshConnectInfo());
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
//        logger.info("用户:{},连接WebSSH", webSocketSession.getAttributes().get(ConstantPool.USER_UUID_KEY));
        //调用初始化连接
        sshSession.startSsh();
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        //此处处理逻辑
        String ms=((TextMessage) message).getPayload();
        WebSshData webSshData= JSON.parseObject(ms, WebSshData.class);
        if(OperateType.COMMAND.equals(webSshData.getOperate())){
            webSshData.setOperate(OperateType.COMMAND);
        }else if(OperateType.CONNECT.equals(webSshData.getOperate())){
            webSshData.setOperate(OperateType.CONNECT);
        }
        webSshData.setClientSession(session);
       sshSession.processSsh(webSshData);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        sshSession.closeSsh();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        sshSession.closeSsh();
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}
