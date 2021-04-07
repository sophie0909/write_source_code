package com.wjf.write_source_code.netProgram;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketConn {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        // (1) 阻塞方法获取新的连接
        Socket socket = serverSocket.accept();
    }

}
