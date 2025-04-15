import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static Map<Character,Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                map.put(c, map.getOrDefault(c, 0) + (int)Math.pow(10, word.length() - j - 1));
            }
        }
        Integer[] values = map.values().toArray(new Integer[0]);
        Arrays.sort(values, Collections.reverseOrder());
        int result = 0;
        int offset = 9;
        for (int i = 0; i < values.length; i++) {
            result += values[i] * offset;
            offset--;
        }

        System.out.println(result);
    }
}
