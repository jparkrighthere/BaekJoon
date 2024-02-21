import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] arr;
    static int[] dp;

    static int solve(int day) {
        if (day > N) {
            return -999;
        }
        if (day == N) { // 마지막 날에는 일을 시작할 수 없음
            return 0;
        }
        int ret = dp[day];

        if (ret != -1) { // 이미 값이 계산되어 있다면
            return ret;
        }
        return Math.max(solve(day + 1), solve(day + arr[day][0]) +arr[day][1]);
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][2];
        dp = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            dp[i] = -1;
        }
        
        System.out.println(solve(0));
    }
}