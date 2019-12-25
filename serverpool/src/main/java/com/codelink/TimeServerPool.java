package com.codelink;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 伪异步的IO服务端
 * 服务端：向客户端提供时间
 */
public class TimeServerPool {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("the time server is start in port:" + port);
            TimeServerHandlerExecutePool singleExecutor= new TimeServerHandlerExecutePool(50,10000);
            Socket socket = null;
            while (true) {
                socket = server.accept();
                singleExecutor.execute(new TimeServerHandler(socket));
            }
        } finally {
            if (null != server) {
                System.out.println("The Time Server close");
                server.close();
                server = null;
            }
        }
    }
}


