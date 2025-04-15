import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[][] items = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            items[i][0] = W;
            items[i][1] = V;
        }

        int[][] dp = new int[N + 1][K + 1];
        for (int i = 1; i <= N; i++) { // item
            for (int j = 1; j <= K; j++) { // 무게
                if (items[i][0] > j) {
                    dp[i][j] = dp[i - 1][j];
                }
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - items[i][0]] + items[i][1]);
                // 전 아이템까지 고려했을 때 무게 j에서의 최대 가치(dp[i-1][j])와,
                // 현재 아이템을 포함했을 때의 가치(dp[i-1][j - 현재 아이템의 무게] + 현재 아이템의 가치) 중 더 큰 값을 선택
            }
        }

        System.out.println(dp[N][K]);
    }
}
