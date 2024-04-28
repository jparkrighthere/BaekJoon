import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] wine = new int[N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            wine[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[N+1];
        dp[1] = wine[1];
        if (N > 1) {
            dp[2] = wine[1] + wine[2];
        }
        else {
            System.out.println(dp[1]);
            return;
        }

        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(dp[i-1], Math.max(dp[i-2], dp[i-3] + wine[i-1])+ wine[i]);
        }
        System.out.println(dp[N]);  
    }
}
