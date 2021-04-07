package com.wjf.write_source_code.netProgram;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class IOClient {
    public static void main(String[] args) {
   //     new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                int i=0;
                while (i<10) {
                    i++;
                    try {
                        socket.getOutputStream().write((new Date() + "client1: hello world").getBytes());
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                }
            } catch (IOException e) {
            }
  //      }).start();
    }
}
