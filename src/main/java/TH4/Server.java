package TH4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.Random;

public class Server {
    private static Socket socket = null;
    static BufferedReader in = null;
    static BufferedWriter out = null;
    static String value_bai4 = "";
    static int cout_bai4 = 0;
    static LocalDateTime startTime;
    public Server(int port){
        Random random = new Random();
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client...");
            socket = server.accept();
            System.out.println("Client accepted");
            value_bai4 = String.valueOf(random.nextInt((100 - 0) + 1));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String line = "";
            while(true){
                line = in.readLine();
                if (startTime == null)
                    startTime = LocalDateTime.now();
                if (line.equals("Over")){
                    out.write("Server Closed");
                    out.newLine();
                    out.flush();
                    break;
                }
                System.out.println("Server received " + line);
                //BaiTap
                out.write(Bai5(line));
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
    public String Bai1(String a){
        StringBuilder stringBuilder = new StringBuilder();
        String b = "Server Response Reverse String: ";
        return b + String.valueOf(stringBuilder.append(a).reverse());
    }
    public String Bai2(String a){
        int b = Integer.parseInt(a);
        int s = 0;
        for (int i = 1; i <= b/2; i++){
            if(b%i == 0)
                s += i;
        }
        if (s == b){
            return a + " Là số hoàn chỉnh";
        } else {
            while(s != b){
                s = 0;
                b += 1;
                for (int i = 1; i < b; i++){
                    if(b%i == 0)
                        s += i;
                }
            }
        }
        return  a + " không phải là số hoàn chỉnh, số hoàn chỉnh gần nhất là " + b;
    }

    public String Bai3(String a){
        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
        int b = Integer.parseInt(a);
        String str = "";
        if (b > 2){
            for (int i = 2; i <= b; i++){
                int k = 0;
                while(b%i == 0){
                    b = b/i;
                    k++;
                    linkedHashMap.put(i, k);
                }
            }
            int k = 0;
            for(int i : linkedHashMap.keySet()){
                k++;
                str += i + "^" + linkedHashMap.get(i);
                if(k < linkedHashMap.size()){
                    str += "*";
                }
            }
        }
        return "Thừa số nguyên tố của " + a + " là: " + str;
    }
    public String Bai4(String a){
        int b = Integer.parseInt(a);
        int c = Integer.parseInt(value_bai4);
        int i = ++cout_bai4;
        System.currentTimeMillis();
        if (b == c){
            LocalDateTime endTime = LocalDateTime.now();
            Duration duration = Duration.between(startTime, endTime);
            long seconds = duration.getSeconds();
            return "Bạn đã nhập đúng với số lần: " + i + " và tổng thời gian " + seconds + "s";
        }
        if  (b > c){

            return "Nhỏ hơn";
        }
        return "Lớn hơn";
    }
    public String Bai5(String a){
        String result = "";
        if(!a.matches("[0-9]+[\\+\\*\\-\\/]+[0-9]")){
            return "Dữ liệu đầu vào không hợp lệ !";
        } else {
            String[] b = a.split("[\\+\\*\\-\\/]");
             double a1 = Double.parseDouble(String.valueOf(b[0]));
             double a2 = Double.parseDouble(String.valueOf(b[1]));
             char operator = a.charAt(b[0].length());
             switch (operator){
                 case '+':
                     result += String.valueOf(a1+a2);
                     break;
                 case '-':
                     result += String.valueOf(a1-a2);
                     break;
                 case '*':
                     result += String.valueOf(a1*a2);
                     break;
                 case '/':
                     result += String.valueOf(a1/a2);
                     break;
             }
        }
        return result;
    }
    public static void main(String[] args) throws IOException {
        Server server = new Server(5000);
    }
}
