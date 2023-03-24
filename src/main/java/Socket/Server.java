package Socket;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Socket socket = null;
    static BufferedReader in = null;
    static BufferedWriter out = null;
    public static void main(String[] args) throws IOException {
        try {
            ServerSocket server = new ServerSocket(5000);
            System.out.println("Server started");
            System.out.println("Waiting for a client...");
            socket = server.accept();
            System.out.println("Client accepted");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String line = "";
            while(true){
                line = in.readLine();
                if (line.equals("Over")){
                    out.write("Server Closed");
                    out.newLine();
                    out.flush();
                    break;
                }
                System.out.println("Server received " + line);
                StringBuilder strB = new StringBuilder();
                strB.append(line);
                out.write("Server Response " + line);
                out.newLine();
                out.flush();

            }
            System.out.println("closing connection");
            in.close();
            out.close();
            socket.close();
            server.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
