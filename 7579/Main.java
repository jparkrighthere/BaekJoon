import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] bytes;
    static int[] costs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        bytes = new int[N + 1];
        costs = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            bytes[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
        }

        int maxCost = Arrays.stream(costs).sum();
        int[][] dp = new int[N + 1][maxCost + 1];

        import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] bytes;
    static int[] costs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        bytes = new int[N + 1];
        costs = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            bytes[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
        }

        int maxCost = Arrays.stream(costs).sum();
        int[][] dp = new int[N + 1][maxCost + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= maxCost; j++) {
                dp[i][j] = dp[i - 1][j];

                if (costs[i] <= j) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - costs[i]] + bytes[i]);
                }
            }
        }

        int result = maxCost;
        for (int cost = 0; cost <= maxCost; cost++) {
            if (dp[N][cost] >= M) {
                result = cost;
                break;
            }
        }

        System.out.println(result);
    }
}