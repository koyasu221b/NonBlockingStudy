import java.io.IOException;
import java.net.Socket;

public class NastyChump {

    public static void main(String[] args) throws InterruptedException,IOException {
        System.out.println("Hello World!");
        Socket[] sockets = new Socket[30000] ;
        for(int i=0; i< sockets.length; i++){
            sockets[i] = new Socket("localhost", 8089);

        }

        Thread.sleep(100_000);
    }
}
