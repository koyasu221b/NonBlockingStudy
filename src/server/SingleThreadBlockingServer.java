package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import handler.Handler;
import handler.PrintingHandler;
import handler.TransmogrifyHandler;

public class SingleThreadBlockingServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8089);
        System.out.println(ss.toString());
        Handler<Socket> handler = new PrintingHandler<>(
                new TransmogrifyHandler()
        );
        while (true){
            Socket s = ss.accept();
            System.out.println(s.getRemoteSocketAddress());
            System.out.println(s.getPort());
            System.out.println("before 3");
            System.out.println(s.toString());
            System.out.println("before 4");
            System.out.println(s.getLocalSocketAddress());
            System.out.println("after accept");
            handler.handle(s);
        }

    }

}
