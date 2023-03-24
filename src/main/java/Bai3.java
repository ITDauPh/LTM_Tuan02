import java.io.*;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Bai3 {
public static void traTuDien(String string){
    LinkedHashMap<String,String> tudien = new LinkedHashMap<>();
    String path = "C:\\Users\\blemb\\IdeaProjects\\LTM_Tuan02\\src\\main\\java\\tudien.txt";
    Scanner sc = null;
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
            System.out.print(tudien.get(key));
        } else {
            if(string.equals(tudien.get(key))){
                System.out.print(key);
            }
        }
    }
}

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập vào một từ tiếng Anh hoặc tiếng Việt: ");
        String word = scanner.nextLine();
        System.out.print("Translated: ");
        traTuDien(word);

    }

}
