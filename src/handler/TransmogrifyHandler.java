package handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.net.Socket;

import util.Util;

public class TransmogrifyHandler implements Handler<Socket>{
    @Override
    public void handle(Socket s) throws IOException {
        try (
                s; // Java 9
                InputStream in = s.getInputStream();
                OutputStream out = s.getOutputStream();
        ) {
            int data;
//            in.transferTo(out);
            while ((data = in.read()) != -1) {
                out.write(Util.transmogrify(data));
            }

        }
        catch (IOException e){
            throw new UncheckedIOException(e);
        }
        finally {
            System.out.println("Disconnected from " + s);
        }

    }
}
