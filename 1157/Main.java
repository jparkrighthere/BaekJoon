import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine().toUpperCase();
        char[] arr = str.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i],1) + 1);
        }

        Set<Character> k = map.keySet();
        Collection<Integer> v = map.values();
        Object[] values = v.toArray();
        Object[] keys = k.toArray();
        char ret = '\0';
        int max = 0;

        for (int i = 0; i < values.length; i++) {
            if (max == (int)values[i]) {
                ret = '?';
            }
            else if (max < (int)values[i]) {
                max = (int)values[i];
                ret = (char)keys[i];
            }
        }
        System.out.println(ret);
    } 
}
