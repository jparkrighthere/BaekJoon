import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int MAX = Integer.MIN_VALUE;
    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static void dfs(int y, int x, int depth) {
        if (depth == 4) {
            MAX = Math.max(MAX, findMax());
            return;
        }

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny >= 0 && ny < N && nx >= 0 && nx < M && !visited[ny][nx]) {
                visited[ny][nx] = true;
                dfs(ny, nx, depth + 1);
                visited[ny][nx] = false;
            }
        }
    }

    static int findMax() {
        int ret = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] == true) {
                    ret += map[i][j];
                    // System.out.print(i + " " + j + " " + map[i][j]);
                }
                // System.out.println();
            }
        }

        return ret;
    }

    static void combi(int y, int x, int depth, int z, int sum) {
        if (depth == 3) {
            MAX = Math.max(MAX, sum);
            return;
        }

        for (int i = z; i < 4; i++) {
            int ny = y + dy[z];
            int nx = x + dx[z];
            if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
                combi(y,x, depth + 1, i + 1, sum + map[ny][nx]);
            }
        }
    }
 
     public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i,j,1);
                visited[i][j] = false;
                combi(i,j,0,0,map[i][j]);
            }
        }
        

        System.out.println(MAX);
        br.close();
    }  
}
