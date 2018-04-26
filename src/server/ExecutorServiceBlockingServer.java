package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import handler.ExecutorServiceHandler;
import handler.Handler;
import handler.PrintingHandler;
import handler.ThreadHandler;
import handler.TransmogrifyHandler;

public class ExecutorServiceBlockingServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8089);
        System.out.println(ss.toString());
        Handler<Socket> handler = new ExecutorServiceHandler<>(
                new PrintingHandler<>(
                        new TransmogrifyHandler()),
                Executors.newFixedThreadPool(10)
        );
        while (true){
            System.out.println("before accept");
            Socket s = ss.accept();
            System.out.println("after accept");
            handler.handle(s);
        }

    }

}
