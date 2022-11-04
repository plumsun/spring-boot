package com.study.controller;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Properties;

/**
 * @program: FtpController
 * @Date: 2022/11/3
 * @Author: LiHaoHan
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/ftp")
public class FtpController {


    @GetMapping("connect")
    public String connect(@RequestParam String host,@RequestParam Integer port) throws IOException {
        FTPClient client = new FTPClient();
        client.connect(host, port);
        boolean connected = client.isConnected();
        System.out.println("connected = " + connected);
        if(client.isConnected()){
            client.disconnect();
            return "s";
        }else return "f";
    }

    @GetMapping("jsch")
    public String jsch(@RequestParam String username,@RequestParam String password,@RequestParam String host,@RequestParam Integer port) throws JSchException, SftpException {
        JSch jsch = new JSch();
        jsch.getSession(username, host, port);
        Session sshSession = jsch.getSession(username, host, port);
        sshSession.setPassword(password);
        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        sshSession.setConfig(sshConfig);
        sshSession.connect();
        ChannelSftp channel = (ChannelSftp) sshSession.openChannel("sftp");
        channel.connect();
        String filename="/download/domvsl.txt";
        SftpATTRS stat = channel.stat(filename);
        long size = stat.getSize();
        channel.get(filename, "d:/test.txt", new SftpProgressMonitor() {
            @Override
            public void init(int op, String src, String dest, long max) {
                log.info("op:{},src {},dest {},max {}",op,src,dest,max);
                System.out.println("开始下载");
            }

            @Override
            public boolean count(long count) {
                return false;
            }

            @Override
            public void end() {
                System.out.println("下载结束");
            }
        });
        return "s";
    }
}
