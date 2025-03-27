import java.util.*;
import java.io.*;

public class Main {
    static double A, B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = Double.parseDouble(br.readLine()) / 100;
        B = Double.parseDouble(br.readLine()) / 100;

        double[][][] dp = new double[19][19][19];
        dp[0][0][0] = 1.0;
        for (int i = 1; i <= 18; i++) {
            for (int j = 0; j <= i; j++) {
                for (int k = 0; k <= i; k++) {
                    // A팀이 득점하고 B팀이 득점하지 않았을 경우
                    if (j > 0) dp[i][j][k] += dp[i - 1][j - 1][k] * A * (1 - B);
                    // A팀이 득점하지 않았고 B팀이 득점했을 경우
                    if (k > 0) dp[i][j][k] += dp[i - 1][j][k - 1] * (1 - A) * B;
                    // A팀이 득점하고 B팀이 득점했을 경우
                    if (j > 0 && k > 0) dp[i][j][k] += dp[i - 1][j - 1][k - 1] * A * B;
                    // A팀이 득점하지 않았고 B팀이 득점하지 않았을 경우
                    dp[i][j][k] += dp[i - 1][j][k] * (1 - A) * (1 - B);
                }
            }
        }

        double answer = 0.0;
        for (int i = 0; i <= 18; i++) {
            for (int j = 0; j <= 18; j++) {
                if (isPrime(i) || isPrime(j)) answer += dp[18][i][j];
            }
        }

        System.out.println(answer);
    }

    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
