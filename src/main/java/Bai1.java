import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Bai1 {
    public static int xulychuoi(String E) throws Exception {
        int a = 0;
        int b = 0;
        char c = 0;
        int error = 0;
        //StringTokenizer
            if (!E.matches("[0-9]+[\\+\\-\\*/][0-9]+")) {
                throw new Exception("Đầu vào không hợp lệ");
            }
        String[] parts = E.split("[\\+\\-\\*/]");
        a = Integer.parseInt(parts[0]);
        b = Integer.parseInt(parts[1]);
        char operator = E.charAt(parts[0].length());
        //Cach2 //StringTokenizer

        int result = 0;
        switch (operator) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                result = a / b;
                break;
            default:
                throw new Exception("Đầu vào không hợp lệ");
        }
        return result;
    }
    public static void main(String[] args) throws Exception {
        String e = "";
        Scanner scanner = new Scanner(System.in);
        e = scanner.nextLine();
        System.out.print(xulychuoi(e));
    }
}

