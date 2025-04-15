import java.util.*;
import java.io.*;


public class Main {
    static int N;
    static List<Long> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < 10; i++) {
            dfs(i, i);
        }

        Collections.sort(list);
        System.out.println(list);

        if (N >= list.size()) System.out.println(-1);
        else System.out.println(list.get(N));
    }

    static void dfs(long num, int lastDigit) {
        list.add(num);

        for (int i = 0; i < lastDigit; i++) {
            dfs(num * 10 + i, i);
        }
    }
}