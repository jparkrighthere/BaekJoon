import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int MAX = Integer.MAX_VALUE / 2;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] fw = new int[N][N];
        int[][] grid = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(fw[i], MAX);
            Arrays.fill(grid[i], -1);
            fw[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());

            fw[from][to] = t;
            fw[to][from] = t;
            grid[from][to] = to + 1;
            grid[to][from] = from + 1;
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (fw[i][k] != MAX && fw[k][j] != MAX && fw[i][k] + fw[k][j] < fw[i][j]) {
                        fw[i][j] = fw[i][k] + fw[k][j];
                        grid[i][j] = grid[i][k];
                    }
                }
            }
        }

        // for (int[] row : fw) {
        //     System.out.println(Arrays.toString(row));
        // }
        // System.out.println();
        // for (int[] row : grid) {
        //     System.out.println(Arrays.toString(row));
        // }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N ; j++) {
                if (grid[i][j] == - 1) sb.append("-").append(" ");
                else sb.append(grid[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
