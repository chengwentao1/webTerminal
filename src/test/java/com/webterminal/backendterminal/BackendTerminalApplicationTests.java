package com.webterminal.backendterminal;

import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.channel.ChannelExec;
import org.apache.sshd.client.channel.ClientChannel;
import org.apache.sshd.client.channel.ClientChannelEvent;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.sftp.client.SftpClient;
import org.apache.sshd.sftp.client.SftpClientFactory;
import org.apache.sshd.sftp.client.fs.SftpFileSystem;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

@SpringBootTest
class BackendTerminalApplicationTests {

    @Test
    public void clentTest() throws IOException
    {

        String cmd="ls";

        SshClient client=SshClient.setUpDefaultClient();
        client.start();

        ClientSession session=client.
                connect("root", "ip", 22).verify(2000).getClientSession();

        session.addPasswordIdentity("password");
        session.auth().verify();

//session.addPublicKeyIdentity(SecurityUtils.loadKeyPairIdentity("keyname",
// new FileInputStream("priKey.pem"), null));if(!session.auth().await().isSuccess())

        System.out.println("auth failed");

        ChannelExec ec=session.createExecChannel(cmd);

        ec.setOut(System.out);

        ec.open();

        ec.waitFor(Collections.singleton(ClientChannelEvent.CLOSED),0);

        ec.close();

        client.stop();

    }

}
