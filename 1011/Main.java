import java.util.*;
import java.io.*;

public class Main {
    static int T, N, M;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        dp = new int[31][31];
        combi();
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            System.out.println(dp[N][M]);
        }
    }

    static void combi() {
        // Nc1 = N;
        for (int i = 1; i <= 30; i++) {
            dp[i][1] = i;
        }

        for (int i = 2; i <= 30; i++) {
            for (int j = 2; j <= 30; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
    }
}

