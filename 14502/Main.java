import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int map[][];
    static int safe;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static void dfs(int count) {
        if (count == 3) {
            bfs();
            return;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(count+1);
                    map[i][j] = 0;
                }
            }
        }
    }   

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        int copyMap[][] = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            copyMap[i] = map[i].clone();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copyMap[i][j] == 2) {
                    q.add(i * 10 + j);
                }
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            int cy = cur / 10;
            int cx = cur % 10;

            for (int i = 0; i < 4; i++) {
                int ny = cy + dy[i];
                int nx = cx + dx[i];

                if (ny < 0 || nx < 0 || ny >= n || nx >= m) {
                    continue;
                }

                if (copyMap[ny][nx] == 0) {
                    copyMap[ny][nx] = 2;
                    q.add(ny * 10 + nx);
                }
            }
        }
        countSafe(copyMap);
    }

    static void countSafe(int[][] map) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    count++;
                }
            }
        }
        safe = Math.max(safe, count);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        safe = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0);
        System.out.println(safe);
    }
}
