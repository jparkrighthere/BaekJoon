import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] dy = {-1 ,1 , 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][] visited;
    static int ans = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
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
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    List<int[]> list = new ArrayList<>();
                    list.add(new int[]{i, j});
                    dfs(i, j, map[i][j], list);
                    visited[i][j] = false;
                }
            }
        }
        System.out.println(ans);
    }

    static void dfs(int y, int x, int sum, List<int[]> list) {
        if (list.size() == 3) {
            int totalSum = createCombi(sum, list);
            ans = Math.max(ans, totalSum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny >= 0 && ny < N && nx >= 0 && nx < M && !visited[ny][nx]) {
                visited[ny][nx] = true;
                list.add(new int[]{ny, nx});
                dfs(ny, nx, sum + map[ny][nx], list);
                visited[ny][nx] = false;
                list.remove(list.size() - 1);
            }
        }
    }

    static int createCombi(int sum, List<int[]> list) {
        int max = sum;
        for (int[] pos : list) {
            for (int i = 0; i < 4; i++) {
                int ny = pos[0] + dy[i];
                int nx = pos[1] + dx[i];

                if (ny >= 0 && ny < N && nx >= 0 && nx < M && !visited[ny][nx]) {
                    int newSum = sum + map[ny][nx];
                    if (newSum > max) max = newSum;
                }
            }
        }
        return max;
    }
}
