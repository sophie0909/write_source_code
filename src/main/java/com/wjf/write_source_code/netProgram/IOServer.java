package com.wjf.write_source_code.netProgram;

import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 传统的io是同步阻塞的
 * // (1) 阻塞方法获取新的连接
 *  Socket socket = serverSocket.accept();
 *  是一个阻塞方法，当前连接未处理完成时，不能接受新的连接请求；一个线程只能处理一个请求
 *  我们通常使用线程池的方式，开启多线程，为每个连接创建一个线程来处理。
 *  当连接数过多，成千上万个连接时，线程资源受限，服务器压力过大
 *
 *  NIO中引入selector的概念，所有的连接进来先注册到selector上，用一个死循环来询问selector中哪些连接有数据可读，然后一并处理
 *
 */
public class IOServer {
    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(8000);
        // (1) 接收新连接线程
//        new Thread(() -> {
           while (true) {
                try {
                    // (1) 阻塞方法获取新的连接
                    Socket socket = serverSocket.accept();

                    // (2) 每一个新的连接都创建一个线程，负责读取数据
//                    new Thread(() -> {
                        try {
                            int len;
                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            // (3) 按字节流方式读取数据
                            while ((len = inputStream.read(data)) != -1) {
                                System.out.println(new String(data, 0, len));
                            }
                        } catch (IOException e) {
                        }
//                    }).start();

                } catch (IOException e) {
                }

            }
//        }).start();
    }
}
