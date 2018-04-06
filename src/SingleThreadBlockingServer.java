import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadBlockingServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8089);
        System.out.println(ss.toString());
        while (true){
            System.out.println("before accept");
            Socket s = ss.accept();
            System.out.println("before 1");
            System.out.println(s.getRemoteSocketAddress());
            System.out.println("before 2");
            System.out.println(s.getPort());
            System.out.println("before 3");
            System.out.println(s.toString());
            System.out.println("before 4");
            System.out.println(s.getLocalSocketAddress());
            System.out.println("after accept");
            handle(s);
        }

    }

    private static void handle(Socket s) throws IOException {
        System.out.println("Connected to " + s);
        try(
           s;
           InputStream in = s.getInputStream();
           OutputStream out = s.getOutputStream();
           ){
            int data;
//            in.transferTo(out);
            while ((data = in.read()) != -1){
                out.write(transmogrify(data));
            }

        }finally {
            System.out.println("Disconnected from " + s);
        }

    }

    private static int transmogrify(int data){
        return Character.isLetter(data) ? data ^ ' ' : data;
    }
}
