//package com.webterminal.backendterminal.connect.ssh;
//
//import org.apache.sshd.server.Environment;
//import org.apache.sshd.server.ExitCallback;
//import org.apache.sshd.server.command.Command;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//
///**
// * @ClassName SshResult
// * @Description处理数据返回情况
// * @Author cwt
// * @Date 2022/10/5 8:58
// */
//public class SshResult implements Command, Runnable {
//    private InputStream in;
//    private OutputStream out, err;
//    private ExitCallback callback;
//
//    public SshResult() {
//        super();
//    }
//
//    @Override
//    public void setInputStream(InputStream in) {
//        this.in = in;
//    }
//
//    @Override
//    public void setOutputStream(OutputStream out) {
//        this.out = out;
//    }
//
//    @Override
//    public void setErrorStream(OutputStream err) {
//        this.err = err;
//    }
//
//    @Override
//    public void setExitCallback(ExitCallback callback) {
//        this.callback = callback;
//    }
//
//
//
//    @Override
//    public void run() {
//        while(true) {
//            try {
//                String cmd = readCommand(in);
//                if ("exit".equals(cmd)) {
//                    break;
//                }
//
//                handleCommand(cmd, out);
//            } catch (Exception e) {
//                writeError(err, e);
//                callback.onExit(-1, e.getMessage());
//                return;
//            }
//
//            callback.onExit(0);
//        }
//
//    }
