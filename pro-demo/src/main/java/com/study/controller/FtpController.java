package com.study.controller;

import com.jcraft.jsch.*;
import com.study.service.FtpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

/**
 * Ftp控制层
 */
@Slf4j
@RestController
@RequestMapping("/ftp")
public class FtpController {

    @Resource
    private FtpService ftpService;

    /**
     * webSerivce超时测试
     *
     * @param map
     * @throws Exception
     */
    @PostMapping("timeout")
    public void timeout(@RequestBody HashMap map) throws Exception {
        ArrayList<String> strings = new ArrayList<>();
        try {
            this.ftpService.timeOut(map);
        } catch (RuntimeException e){
            throw e;
        } catch (IOException e){
            log.error("io",e);
        }
    }

    /**
     * axis发送http协议调用ftp
     * @param host
     * @param port
     * @return
     * @throws IOException
     */
    @GetMapping("connect")
    public String connect(@RequestParam String host,@RequestParam Integer port) throws IOException {
        FTPClient client = new FTPClient();
        client.login("epadmin","easipass");
        client.connect(host, port);
        boolean connected = client.isConnected();
        System.out.println("connected = " + connected);
        if(client.isConnected()){
            client.changeWorkingDirectory("/download");
            FTPFile[] ftpFiles = client.listFiles();
            System.out.println("ftpFiles = ");
            return "s";
        }else return "f";
    }

    /**
     * jsch发送ssh协议连接，调用ftp服务器
     * @param username user
     * @param password pass
     * @param host address
     * @param port port
     * @return return
     * @throws JSchException
     * @throws SftpException
     */
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
