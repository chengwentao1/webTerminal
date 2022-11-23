package com.webterminal.backendterminal.connect.ssh.defaults;

import com.webterminal.backendterminal.connect.ssh.SshSession;
import com.webterminal.backendterminal.connect.ssh.model.SshConnectInfo;
import com.webterminal.backendterminal.connect.ssh.model.WebSshData;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.*;;
import java.util.Arrays;

/**
 * @ClassName WebSockletExceutour
 * @Description
 * @Author cwt
 * @Date 2022/10/5 12:33
 */
public class WebSocketExecutor extends BaseSshExecutor {


    /**
     * 写入服务器
     * */
   private static OutputStream outputStream ;
    /**
     * 写出服务器
     * */
    private static InputStream reader;
    public WebSocketExecutor(SshSession clientSession, SshConnectInfo info) {
        super(clientSession, info);
    }

//    @Override
//    protected <T> T doProcess(WebSshData webSshData) throws IOException {
//        if (channel == null || channel.isClosed()) {
//            channel = session.getSession().createShellChannel();
//        }
//        ByteArrayOutputStream err = new ByteArrayOutputStream();
//        channel.setErr(err);
//        channel.open().verify(sshConnectInfo.getExecuteTimeout());
//        WebSocketSession webSocketSession = (WebSocketSession) webSshData.getClientSession();
//        //循环读取
//
//        //如果没有数据来，线程会一直阻塞在这个地方等待数据。
//        new Thread() {
//            @Override
//            public void run() {
//                System.out.println("12"+ currentThread().getName());
//                try {
//                    OutputStream outputStream = channel.getInvertedIn();
//                    if(webSshData.getCommand()==null){
//                        webSshData.setCommand("");
//                    }
//                    outputStream.write(webSshData.getCommand().getBytes(StandardCharsets.UTF_8));
//                    outputStream.flush();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    System.out.println("111"+ currentThread().getName());
//                    InputStream reader = channel.getInvertedOut();
//                    byte[] buffer = new byte[1024];
//                    int i = 0;
//                    channel.close();
//                    //如果没有数据来，线程会一直阻塞在这个地方等待数据。
//                    while ((i = reader.read(buffer)) != -1) {
//                        sendMessage((WebSocketSession) webSshData.getClientSession(), Arrays.copyOfRange(buffer, 0, i));
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//         channel.waitFor(EnumSet.of(ClientChannelEvent.CLOSED), 0L);
//        return null;
//    }

    @Override
    protected <T> T doProcess(WebSshData webSshData) throws IOException {
        WebSocketSession webSocketSession = (WebSocketSession) webSshData.getClientSession();
        //循环读取
        if(outputStream==null){
            outputStream = channel.getInvertedIn();
        }
        if(reader==null){
            reader = channel.getInvertedOut();
        }
        //如果没有数据来，线程会一直阻塞在这个地方等待数据。
        switch (webSshData.getOperate()) {
            case COMMAND:
                outputStream.write(webSshData.getCommand().getBytes());
                outputStream.flush();
                System.out.println("写数据" + webSshData.getCommand());
                break;
            case CONNECT:
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("111" + currentThread().getName());
                            int i = 0;
                            byte[] buffer = new byte[1024];
                            //如果没有数据来，线程会一直阻塞在这个地方等待数据。
                            while ((i = reader.read(buffer)) != -1){
                                sendMessage(webSocketSession, Arrays.copyOfRange(buffer, 0, i));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }finally {
                            try {
                                reader.close();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }.start();
                break;
            default:
                new RuntimeException("未定义类型");
        }
//        channel.waitFor(EnumSet.of(ClientChannelEvent.CLOSED), 0L);
        return null;
    }


    public void sendMessage(WebSocketSession session, byte[] buffer) throws IOException {
        session.sendMessage(new TextMessage(buffer));
    }
}
