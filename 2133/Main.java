import java.util.*;
import java.io.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        if (N % 2 == 0) {
            dp[0] = 1;
            dp[2] = 3;
            for (int i = 4; i <= N; i += 2) {
                dp[i] = dp[i - 2] * dp[2];
                for (int j = i - 4; j >= 0; j-= 2) {
                    dp[i] += dp[j] * 2;
                }
            }
        }

        System.out.println(dp[N]);
    }
}