package util;

import java.nio.ByteBuffer;

public class Util {
    public static int transmogrify(int data){
        return Character.isLetter(data) ? data ^ ' ' : data;
    }

    public static void transmogrify(ByteBuffer buf) {
        // pos = 0, limit=80, capacity=80
        // "hello\n"  pos=6, limit = 80, capacity=80

//        buf.limit(buf.position());
//        buf.position(0);

        buf.flip();
        // "hello\n"  pos=0, limit = 6, capacity=80        //

        for(int i = 0; i < buf.limit(); i++){
            buf.put(i, (byte) transmogrify(buf.get(i)));
        }
    }
}
