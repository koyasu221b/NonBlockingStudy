package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import handler.Handler;
import handler.PrintingHandler;
import handler.ThreadHandler;
import handler.TransmogrifyHandler;

public class MultiThreadBlockingServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8089);
        System.out.println(ss.toString());
        Handler<Socket> handler = new ThreadHandler<>(
                new PrintingHandler<>(
                        new TransmogrifyHandler()
        ));
        while (true){
            System.out.println("before accept");
            Socket s = ss.accept();
            System.out.println("after accept");
            handler.handle(s);
        }

    }

}
