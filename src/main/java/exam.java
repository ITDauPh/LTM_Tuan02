import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class exam {
    public static void bai1(String domain){
        InetAddress[] add = new InetAddress[0];
        try {
            add = InetAddress.getAllByName(domain);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        for(int i = 0; i < add.length; i++){
            System.out.println(add[i]);
        }
    }
    public static void bai2(){
        String path = "src/main/java/domainName.txt";
        Scanner sc = null;
        try {
            sc = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        InetAddress[] add = new InetAddress[0];

        while (sc.hasNextLine()) {
            String k = sc.nextLine();
            try {
                add = InetAddress.getAllByName(k);
                for(int i = 0; i < add.length; i++){
                    StringTokenizer str = new StringTokenizer(add[i].toString(), "/");
                    while(str.hasMoreTokens()) {
                        String a = str.nextToken();
                        String b = str.nextToken();
                        System.out.println("Domain name " + a + " has IP " + b);
                    }

                }
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public static void bai3() {
        String path = "src/main/java/ipAddress.txt";
        Scanner sc = null;
        try {
            sc = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (sc.hasNextLine()) {
            String k = sc.nextLine();
            try {
                InetAddress add = InetAddress.getByName(k);
                boolean timeRequest = false;
                try {
                    timeRequest = add.isReachable(5000);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("IP " + k + (timeRequest? " is Reachable":" not Reachable"));
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void bai4() throws UnknownHostException {
        String address = "192.168.0.";
        for(int i = 1; i < 255; i++){
            String ip = address + i;
            InetAddress add = InetAddress.getByName(ip);
            try {
                System.out.print(ip);
                if(add.isReachable(1000)){
                    System.out.println(" ==> is online");
                } else System.out.println();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public static void main(String[] args) throws UnknownHostException {
        Scanner scanner = new Scanner(System.in);
        String a = "";
//        while (true){
//            System.out.print("Nhập domain muốn xem IP: ");
//            a = scanner.nextLine();
//            if(a.equals("exit"))
//                break;
//            bai1(a);
//        }
            bai4();
    }
}
