package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Executors;

import handler.BlockingChannelHandler;
import handler.ExecutorServiceHandler;
import handler.Handler;
import handler.PrintingHandler;
import handler.TransmogrifyChannelHandler;

public class SingleThreadedPollingNonBlockingServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8089));
        ssc.configureBlocking(false);
        System.out.println(ssc.toString());
        Handler<SocketChannel> handler =
                        new TransmogrifyChannelHandler();
        Collection<SocketChannel> sockets = new ArrayList<>();
        while (true){
//            System.out.println("before accept");
            SocketChannel sc = ssc.accept(); // alomst always null
//            System.out.println("after accept");
            if(sc != null){
                sockets.add(sc);
                System.out.println("Connected to " + sc);
                sc.configureBlocking(false);
            }
            for(SocketChannel socketChannel: sockets){
               if(socketChannel.isConnected()) {
                   handler.handle(socketChannel);
               }
            }

            sockets.removeIf(socketChannel -> !socketChannel.isConnected());
        }

    }

}
