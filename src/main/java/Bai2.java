import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

public class Bai2 {
    public static String convert(String input){
        LinkedHashMap<String, String> tudien = new LinkedHashMap<>();
        String output = "";
        StringTokenizer stk = new StringTokenizer(input, " ");
        while(stk.hasMoreTokens()) {
            String a = stk.nextToken();
            if(!tudien.containsKey(a.toLowerCase())) {
                tudien.put(a.toLowerCase(), a);
            }
        }
        for(String key : tudien.keySet()){
            output += tudien.get(key);
            if(!tudien.isEmpty()){
                output += " ";
            }
        }
        return output;
    }
    public static void main(String[] args){
        String st = "Dai hoc sai gon la mot trong nhung truong dai hoc lau doi nhat sai gon";

        System.out.print(convert(st));
    }
}
