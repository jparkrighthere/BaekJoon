import java.util.*;
import java.io.*;

public class Main {
    static int W, S;
    static String key, word;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        key = br.readLine();
        word = br.readLine();

        Map<Character, Integer> keyMap = new HashMap<>();
        for (char ch : key.toCharArray()) {
            keyMap.put(ch, keyMap.getOrDefault(ch, 0) + 1);
        }

        Map<Character, Integer> windowMap = new HashMap<>();
        int count = 0;
        int matches = 0;

        for (int i = 0; i < W; i++) {
            char ch = word.charAt(i);
            windowMap.put(ch, windowMap.getOrDefault(ch, 0) + 1);
        }

        for (char ch : keyMap.keySet()) {
            if (keyMap.get(ch).equals(windowMap.getOrDefault(ch, 0))) {
                matches++;
            }
        }

        for (int i = 0; i <= S - W; i++) {
            if (matches == keyMap.size()) count++;

            // 다음 문자 추가
            if (i + W < S) {
                char newChar = word.charAt(i + W);
                windowMap.put(newChar, windowMap.getOrDefault(newChar, 0) + 1);

                if (keyMap.containsKey(newChar)) {
                    if (windowMap.get(newChar).equals(keyMap.get(newChar))) {
                        matches++;
                    } 
                    else if (windowMap.get(newChar) - 1 == keyMap.get(newChar)) {
                        matches--;
                    }
                }
            }

            // 이전 문자 제거
            char oldChar = word.charAt(i);
            if (keyMap.containsKey(oldChar)) {
                if (windowMap.get(oldChar).equals(keyMap.get(oldChar))) {
                    matches--;
                } 
                else if (windowMap.get(oldChar) - 1 == keyMap.get(oldChar)) {
                    matches++;
                }
            }

            windowMap.put(oldChar, windowMap.get(oldChar) - 1);
            if (windowMap.get(oldChar) == 0) {
                windowMap.remove(oldChar);
            }
        }

        System.out.println(count);
    }
}
