import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] stairs = new int[301];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            stairs[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[301];
        dp[1] = stairs[1];
        dp[2] = stairs[1] + stairs[2];
        dp[3] = Math.max(stairs[1], stairs[2]) + stairs[3];

        for (int i = 4; i <= N; i ++) {
            dp[i] = Math.max(dp[i-3] + stairs[i-1], dp[i-2]) + stairs[i];
        }
        System.out.println(dp[N]);  
    }
}
