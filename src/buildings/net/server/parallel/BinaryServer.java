package buildings.net.server.parallel;

import java.io.IOException;
import java.net.ServerSocket;

public class BinaryServer {

    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(5000);
            System.out.println("Starting server..");
            //noinspection InfiniteLoopStatement
            while (true) {
                int i = 1;
                BinaryServerRunnable sr = new BinaryServerRunnable(socket.accept());
                System.out.println(String.format("Client №%d accepted", i));
                Thread thread = new Thread(sr);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("Server is shutting down (Error)");
        }
    }
}
