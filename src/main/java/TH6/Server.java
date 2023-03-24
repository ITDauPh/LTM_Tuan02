package TH6;

import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

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
                line = (in.readLine()).toLowerCase();
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
                out.write("Server response: " + Bai4(line));
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
        return traTuDien(a);
    }
    public String Bai3(String a){
        Double Interval = Double.parseDouble(a);
        Double x,y, Orgin_distance;
        int cir_point = 0, square_point = 0;
        for( int i = 0; i < Interval; i++){
            x = Math.random()*2-1;
            y = Math.random()*2-1;
            Orgin_distance = Math.sqrt(x*x+y*y);
            if(Orgin_distance <= 1){
                cir_point++;
            }
        }
        double pi = (4*cir_point)/Interval;
        return String.valueOf(pi);
    }

    public String Bai2(String a){
        String url = "http://ip-api.com/json/"+ a +"?fields=message,continent,country,city,query";
        String result = "";
        String ip = "";
        String city = "";
        String country = "";
        String continent = "";
        try {
            Document doc = Jsoup.connect(url)
                    .method(Connection.Method.GET)
                    .ignoreContentType(true)
                    .execute()
                    .parse();
            JSONObject json = new JSONObject(doc.text());
            ip = json.get("query").toString();
            city = json.get("city").toString();
            country = json.get("country").toString();
            continent = json.get("continent").toString();
            result += "IP: " + ip + "- City: " + city + "- Country: "
                        + country + "- Continent: " + continent;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static String Bai4(String a){
        String result = "";
        String info = "";
        if(a.matches("\\d{9}")){
        String url = "https://masothue.com/Search/?q="+ a +"&type=identity&token=EImFsjgYCH&force-search=1";
        try {
            Connection.Response conn = Jsoup.connect(url)
                    .method(Connection.Method.GET)
                    .followRedirects(true)
                    .ignoreContentType(true)
                    .execute();
            Document doc = Jsoup.connect(conn.url().toString())
                    .execute().parse();
            info = doc.getElementsByClass("table-taxinfo").toString();
            result = info.replaceAll("<.*?>", "");
            result += "\n--end--";
            return "\nThông tin mã số thuế cá nhân: \n" + result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        }
        else return "Số CMND phải là chuỗi có 9 số !" + "\n--end--";

    }

    public static String traTuDien(String string){
        LinkedHashMap<String,String> tudien = new LinkedHashMap<>();
        String path = "src/main/java/TH5/tudien.txt";
        Scanner sc = null;
        String result = "";
        try {
            sc = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (sc.hasNextLine()) {
            String k = sc.nextLine();
            StringTokenizer stk = new StringTokenizer(k, ";");
            while(stk.hasMoreTokens()) {
                String a = stk.nextToken();
                String b = stk.nextToken();
                tudien.put(a, b);
            }
        }
        for(String key : tudien.keySet()){
            if(string.equals(key)){
                return tudien.get(key);
            } else {
                if(string.equals(tudien.get(key))){
                    return key;
                }
            }
        }
        return "Không tìm thấy";
    }


    public static void main(String[] args) throws IOException {
    Server server = new Server(5000);

    }
}
