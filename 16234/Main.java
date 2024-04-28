import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int ret = 0;

    static void open(int r, int c, List<int[]> union) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        union.add(new int[]{r, c});
        visited[r][c] = true;
        int sum = map[r][c];
        int count = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0];
            int x = cur[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny >= 0 && ny < N && nx >= 0 && nx < N && !visited[ny][nx]) {
                    int diff = Math.abs(map[y][x] - map[ny][nx]);
                    if (diff >= L && diff <= R) {
                        q.add(new int[]{ny, nx});
                        union.add(new int[]{ny, nx});
                        visited[ny][nx] = true;
                        sum += map[ny][nx];
                        count++;
                    }
                }
            }
        }

        int population = sum / count;
        for (int[] pos : union) {
            map[pos[0]][pos[1]] = population;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            visited = new boolean[N][N];
            boolean moved = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        List<int[]> union = new ArrayList<>();
                        open(i, j, union);
                        if (union.size() > 1) {
                            moved = true;
                        }
                    }
                }
            }
            if (!moved) {
                break;
            }
            ret++;
        }
        System.out.println(ret);
    }
}
