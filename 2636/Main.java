import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int cheese = 0;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) cheese++;
            }
        }

        int t = 0;
        int count = 0;

        while (cheese != 0) {
            count = cheese;
            t++;
            visited = new boolean[N][M];
            bfs();
        }
    }

    static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cy = pos[0];
            int cx = pos[1];

            for (int i = 0; i < 4; i++) [
                int ny = cy + dy[i];
                int nx = cx + dx[i];

                if (ny >= 0 && ny < N && nx >= 0 && nx < M && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    if (map[ny][nx] == 0) 
                }
            ]
        }
    }
}
