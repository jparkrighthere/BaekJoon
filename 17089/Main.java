import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] friend;
    static int[][] friendMap;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        friend = new int[N+1];
        friendMap = new int[N+1][N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            friendMap[x][y] = 1; 
			friendMap[y][x] = 1;
            friend[x]++;
			friend[y]++;
        }


        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j || friendMap[i][j] != 1) continue;
                for (int k = 1; k <= N; k++) {
                    if (i == j || j == k || friendMap[i][k] != 1 || friendMap[j][k] != 1) continue;

                    int cnt = 0;
                    cnt += (friend[i] - 2) + (friend[j] - 2) + (friend[k] - 2);
                    if (min > cnt) {
                        min = cnt;
                    }
                }
            }
        }

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }
}
