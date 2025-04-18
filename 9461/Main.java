import java.util.*;
import java.io.*;

public class Main {
    static int T, N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        long[] dp = new long[101];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 2;
        dp[6] = 3;
        dp[7] = 4;
        dp[8] = 5;
        dp[9] = 7;
        dp[10] = 9;

        for (int i = 11; i <= 100; i++) {
            dp[i] = dp[i - 1] + dp[i - 5];
        }
        
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            System.out.println(dp[N]);
        }
    }
}
