import java.util.*;
import java.io.*;

public class Main {
    static int N, answer;
    static int[] T, P, dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        T = new int[N + 1];
        P = new int[N + 1];
        dp = new int[N + 2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N; i >= 1; i--) {
            if (i + T[i] <= N + 1) {
                dp[i] = Math.max(dp[i + 1], dp[i + T[i]] + P[i]);
            }
            else {
                dp[i] = dp[i + 1];
            }
        }

        System.out.println(dp[1]);
    }
}