import java.util.*;
import java.io.*;

public class Main {
    static int count = 0;
    static int N, M;
    static boolean[][] pairs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pairs = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            pairs[a][b] = true;
            pairs[b][a] = true;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                if (pairs[i][j]) continue;
                for (int k = j + 1; k <= N; k++) {
                    if (!pairs[i][k] && !pairs[j][k]) {
                        count++;
                    }
                }
            }
        }

        System.out.println(count);
    }
}
