import java.util.*;
import java.io.*;

public class Main {
    static int L, C;
    static char[] arr;
    static StringBuilder sb = new StringBuilder();
    static List<Character> vowelList = new ArrayList<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    static void backtrack(int depth, int at, char[] temp, int vowel, int rest) {
        if (depth == L) {
            if (vowel >= 1 && rest >= 2) {
                sb.append(temp).append("\n");
            }
            return;
        }

        for (int i = at; i < C; i++) {
            temp[depth] = arr[i];
            if (vowelList.contains(arr[i])) {
                backtrack(depth + 1, i + 1, temp, vowel + 1, rest);
            } else {
                backtrack(depth + 1, i + 1, temp, vowel, rest + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[C];

        String line = br.readLine().replaceAll("\\s","");
        for (int i = 0; i < C; i++) {
            arr[i] = line.charAt(i);
        }

        Arrays.sort(arr);
        backtrack(0,0,new char[L], 0, 0);

        System.out.println(sb.toString());
    }
}
