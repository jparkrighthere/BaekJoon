import java.util.*;
import java.io.*;


public class Main {
    static int c, n;
    static int [] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        c = Integer.parseInt(s[0]);
        n = Integer.parseInt(s[1]);

        dp = new int[c + 101];
        Arrays.fill(dp, 987654321);
        dp[0] = 0;

        for (int z = 0; z < n; z++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());

            for (int i = people; i < c + 101; i++) {
                dp[i] = Math.min(dp[i], dp[i - people] + cost);
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = c; i < c + 101; i++) {
            result = Math.min(result, dp[i]);
        }

        System.out.println(result);
    }
}