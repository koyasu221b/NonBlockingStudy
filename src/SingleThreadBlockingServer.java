import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadBlockingServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8089);
        while (true){
            Socket s = ss.accept();
            handle(s);
        }

    }

    private static void handle(Socket s) throws IOException {
        InputStream in = s.getInputStream();
        OutputStream out = s.getOutputStream();
        int data;
//            in.transferTo(out);
        while ((data = in.read()) != -1){
            out.write(transmogrify(data));
        }

        out.close();
        in.close();
        s.close();
    }

    private static int transmogrify(int data){
        return Character.isLetter(data) ? data ^ ' ' : data;
    }
}
