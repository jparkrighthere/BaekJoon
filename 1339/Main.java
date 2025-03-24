import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static String[] words;
    static Map<Character, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        words = new String[N];
        for(int i=0; i<N; i++){
            words[i] = br.readLine();
            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                map.put(c, map.getOrDefault(c, 0) + (int)Math.pow(10, words[i].length()-j-1));
            }
        }
        Integer[] values = map.values().toArray(new Integer[0]);
        Arrays.sort(values, Collections.reverseOrder());
        int result = 0;
        int num = 9;
        for (int i = 0; i < values.length; i++) {
            result += values[i] * num;
            num--;
        }
        System.out.println(result);
    }
}
