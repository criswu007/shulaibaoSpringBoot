package com.example.test;

import com.example.mybatisplus.utils.ConcurrentUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 有连接不一定有线程介入处理业务（根据有无数据判断）
 */
@Slf4j
public class NIO {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8088));

        //底层，操作系统的特性 --jdk跨平台同意封装    选择我们程序需要处理的链接
        //事件机制
        Selector selector = Selector.open();


        while (true) {
            //获取新链接
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false); //显示设置为非阻塞

            //注册事件通知机制，READ
            socketChannel.register(selector, SelectionKey.OP_READ);
            selector.select();
            Set<SelectionKey> envetKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = envetKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey envet = iterator.next();
                if (envet.isReadable()){
                    SocketChannel connection = (SocketChannel) envet.channel();
                    //线程介入

                    ConcurrentUtils.getInstance();
                    ConcurrentUtils.submitTask(() -> {
                        try {
                            //byte[]的高级封装
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            connection.read(byteBuffer);    //非阻塞

                            byteBuffer.flip();//转换为读模式

                            System.out.println(new String(byteBuffer.array()));
                        } catch (Exception e) {
                            log.error(e.getMessage(), e);
                        }
                        return null;
                    });
                }
            }



//            //byte[]的高级封装
//            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//            socketChannel.read(byteBuffer);
//
//            byteBuffer.flip();//转换为读模式

        }

    }
}
