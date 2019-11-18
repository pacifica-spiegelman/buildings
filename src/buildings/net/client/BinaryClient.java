package buildings.net.client;

import buildings.Building;
import buildings.Buildings;
import buildings.net.server.sequental.BinaryServer;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class BinaryClient {
    public static void main() throws IOException {
        Socket socket = new Socket("127.0.0.1", 5000);
        System.out.println("Connected to " + socket.getInetAddress() + ":" + socket.getPort());
        OutputStream out = socket.getOutputStream();
        try (FileReader reader = new FileReader("netBuildings.dat")) {
            BufferedReader bReader = new BufferedReader(reader);
            String line;
            Scanner inputReader = new Scanner(new FileReader("netInput.dat"));
            while ((line = bReader.readLine()) != null) {
                Building newBuilding = Buildings.readBuilding(inputReader);
                Buildings.outputBuilding(newBuilding, out);
                System.out.println(String.format("Read building from file and sent: %s", newBuilding));
            }
            inputReader.close();
        }
        DataInputStream din = new DataInputStream(socket.getInputStream());
        FileWriter fw = new FileWriter("netOutput.txt");
        while (true) {
            if (din.available() > 0) {
                try {
                    float response = din.readFloat();
                    if (response == BinaryServer.SOCKET_CLOSE) {
                        break;
                    }
                    if (response == BinaryServer.ARRESTED) {
                        fw.append("Arrested\n");
                    }
                    else
                        fw.append(String.format("%f\n", response));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Closing the connection...");
        try {
            fw.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
