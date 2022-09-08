package com.example.mybatisplus.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author use
 */
@Slf4j
public class SocketClient {
    public static void sendMsg(String msg) {
        OutputStream os = null;
        PrintWriter pw = null;
        try {
            Socket socket = new Socket("192.168.2.124", 8088);

            os = socket.getOutputStream();
            pw = new PrintWriter(os);
            pw.write(msg);
            pw.flush();
//            socket.shutdownOutput();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
//            try {
//                os.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            pw.close();
        }
    }
}
