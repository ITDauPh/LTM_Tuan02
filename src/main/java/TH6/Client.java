package TH6;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private static Socket socket = null;
    private static BufferedWriter out = null;
    private static BufferedReader in = null;
    private static BufferedReader stdin = null;

    public static void main(String[] args) throws IOException {
        InetAddress inetAddress = InetAddress.getLocalHost();

        socket = new Socket(inetAddress.getHostAddress(), 5000);
        System.out.println("Connected");
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        stdin = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        while (true){
            System.out.println("Input request: ");
            line = stdin.readLine();
            System.out.println("Client sent: " + line);
            out.write(line);
            out.newLine();
            out.flush();
            while (true) {
                line = in.readLine();
                System.out.println(line);
                if (line.equals("--end--"))
                    break;
            }
            if(line.equals("Server Closed"))
                break;
        }
        System.out.println("exits");
        in.close();
        out.close();
        socket.close();

    }
}
