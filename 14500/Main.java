import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int MAX = Integer.MIN_VALUE;
    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static List<int[]> list;

    static void dfs(int y, int x, int depth, int sum, List<int[]> store) {
        if (depth == 3) {
            combi(sum, store);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny >= 0 && ny < N && nx >= 0 && nx < M && !visited[ny][nx]) {
                visited[ny][nx] = true;
                store.add(new int[]{ny, nx});
                dfs(ny, nx, depth + 1, sum + map[ny][nx], store);
                visited[ny][nx] = false;
                store.remove(store.size() - 1);
            }
        }
    }

    static void combi(int sum, List<int[]> store) {
        for (int i = 0; i < store.size(); i++) {
            int cy = store.get(i)[0];
            int cx = store.get(i)[1];
            for (int j = 0; j < 4; j++) {
                int ny = cy + dy[j];
                int nx = cx + dx[j];
                if (ny >= 0 && ny < N && nx >= 0 && nx < M && !visited[ny][nx]) {
                    int tempSum = sum + map[ny][nx];
                    MAX = Math.max(MAX, tempSum);
                }
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
        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    List<int[]> store = new ArrayList<>();
                    store.add(new int[]{i, j});
                    dfs(i, j, 1, map[i][j], store);
                    visited[i][j] = false;
                }
            }
        }

        System.out.println(MAX);
    }
}
