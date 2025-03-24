import java.util.Scanner;

public class Main {
    public static final int MOD = 1000000007;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();  // 맵의 길이 N
        
        // dp[i] : i번째 지점에 도달할 수 있는 경우의 수
        long[] dp = new long[N + 1];
        
        // 시작점은 항상 바닥이므로 dp[0] = 1
        dp[0] = 1;
        
        // 장애물에 대한 규칙 처리
        for (int i = 1; i <= N; i++) {
            // 첫 번째 조건: 공룡은 한 칸 또는 두 칸을 뛰어넘을 수 있다.
            // 단, 이전 두 칸의 장애물 높이에 의해 뛰어넘을 수 없다면 그 지점은 dp[i]에 영향을 미치지 않는다.
            if (i - 1 >= 0) {
                dp[i] = (dp[i] + dp[i - 1]) % MOD;
            }
            if (i - 2 >= 0) {
                dp[i] = (dp[i] + dp[i - 2]) % MOD;
            }
        }
        
        // 마지막 지점에 도달할 수 있는 경우의 수를 출력
        System.out.println(dp[N]);
    }
}
