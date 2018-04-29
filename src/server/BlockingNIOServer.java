package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executors;

import handler.BlockingChannelHandler;
import handler.ExecutorServiceHandler;
import handler.Handler;
import handler.PrintingHandler;
import handler.TransmogrifyChannelHandler;

public class BlockingNIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8080));
        System.out.println(ssc.toString());
        Handler<SocketChannel> handler = new ExecutorServiceHandler<>(
                new PrintingHandler<>(
                        new BlockingChannelHandler(
                        new TransmogrifyChannelHandler())
                        ),
                Executors.newFixedThreadPool(10)
        );
        while (true){
            System.out.println("before accept");
            SocketChannel s = ssc.accept();
            System.out.println("after accept");
            handler.handle(s);
        }

    }

}
