package com.example.mybatisplus.socket;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 监听服务
 * @author use
 */
public class ListenServer extends Thread {

    //监听程序是否已经建立的标志
    private static boolean flag = false;

    ServerSocket serverSocket = null;

    public ListenServer(int serverPort) {
        try {
            serverSocket = new ServerSocket(serverPort);
            System.out.println("start listen "+serverPort+"\n");
            start();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        super.run();
        while (true) {
            // 监听一端口，等待客户接入
            try {
                Socket socket = serverSocket.accept();
                System.out.println("client " + socket +" connected\n");
                // 将会话交给线程处理
                new ServerThread(socket);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    class ServerThread extends Thread {
        private Socket socket;
        private BufferedReader in;

        public ServerThread(Socket s) throws IOException {
            this.socket = s;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            start();
        }

        /**
         * 接收电话系统推送信息的线程
         */
        @Override
        public void run() {
            try {
                int n = 1;
                String rtnxml = "";
                while(true) {
                    // 通过输入流接收客户端信息
                    String line = in.readLine();
                    if(line==null || line.length()<=0){
                        if(n==1){//API信息中过滤http头和内容直接的空白
                            n = 0;
                            continue;
                        }else{
                            break;
                        }
                    }
                    line = StringUtils.trim(line);
                    System.out.println(line);
                    if(line.startsWith("<") && line.endsWith(">")){
                        rtnxml += line;
                    }
                }
                System.out.println("client " + socket + " str:" + rtnxml);
                System.out.println("client " + socket +" finished\n");
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(in != null){
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(socket != null){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 标志监听程序是否已经建立
     * @param flag 监听程序是否已经建立的标志
     */
    public static void setFlag(boolean flag){
        ListenServer.flag = flag;
    }

    /**
     * 监听程序是否已经建立
     * @return 监听程序是否已经建立的标志
     */
    public static boolean getFlag(){
        return flag;
    }

    public static void main(String[] args) {
        if (!ListenServer.getFlag()) {
            ListenServer listenServer = new ListenServer(8088);
        }

        SocketClient.sendMsg("<Cdr>\r<callid>123123</callid>\r</Cdr>");
    }
}