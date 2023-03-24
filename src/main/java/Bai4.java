import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.ref.Cleaner;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.awt.event.KeyEvent.VK_ESCAPE;
import static java.awt.event.KeyEvent.getKeyText;

public class Bai4 {
    public static void ATM(){
        LinkedHashMap<Double,Integer> moneyCache = new LinkedHashMap<>();
        String path = "C:\\Users\\blemb\\IdeaProjects\\LTM_Tuan02\\src\\main\\java\\money.txt";
        Scanner sc = null;
        try {
            sc = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Ghi du lieu tu file vao chuoi k
        while (sc.hasNextLine()) {
            String k = sc.nextLine();
            StringTokenizer stk = new StringTokenizer(k, ";");
            //ghi token vao HashMap
            while(stk.hasMoreTokens()) {
                double a = Double.parseDouble(stk.nextToken());
                int b = Integer.parseInt(stk.nextToken());
                moneyCache.put(a, b);
            }
        }
        Scanner scanner = new Scanner(System.in);
        Double soTien;
        String S = "";
        //Kiem tra tong tien con trong may
        double tienHienCo = 0;
        for(Double key : moneyCache.keySet()){
            tienHienCo += key*moneyCache.get(key);
        }
        do {
            boolean flag = true;
            System.out.print("Nhập số tiền: ");
            soTien = scanner.nextDouble();
            //Neu tien hien co nho hon so tien rut thi thong bao
            if(tienHienCo > soTien) {
                double check = 0;
                for (Double key : moneyCache.keySet()) {
                    int count = 0;
                    int a = moneyCache.get(key);
                    if (a > 0) {
                        while (soTien >= key && moneyCache.get(key) != 0) {                            count++;
                            soTien -= key;
                            moneyCache.put(key, a - count);
                            flag = false;
                        }
                        S += key + " số tờ " + count + "\n";
                    }
                    check += count * key;
                }
            }
                if (flag == true) {
                    System.out.println("số tiền còn lại trong ATM không đủ !");
                } else {
                    System.out.println(S);
                }

        } while (!soTien.equals("exit"));

    }
    public static void main(String[] args) {
        ATM();
    }
}
