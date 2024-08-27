    import java.util.*;
    import java.io.*;

    public class Main {
        static int N;
        static int[][] map;
        static boolean[][] visited;
        static int[] dy = {-1, 1, 0, 0};
        static int[] dx = {0, 0, -1, 1};
        static int min = Integer.MAX_VALUE;

        static void dfs(int y, int x, int num) {
            visited[y][x] = true;
            map[y][x] = num;
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny >= 0 && ny < N && nx >= 0 && nx < N && !visited[ny][nx] && map[ny][nx] == 1) {
                    dfs(ny, nx, num);
                }
            }
        }

        static void bfs(int y, int x, int hometown) {
            Queue<int[]> q = new LinkedList<>();
            visited = new boolean[N][N];
            q.add(new int[] {y, x, 0});
            visited[y][x] = true;

            while (!q.isEmpty()) {
                int[] pos = q.poll();
                int cy = pos[0];
                int cx = pos[1];
                int cnt = pos[2];

                if (map[cy][cx] != 0 && map[cy][cx] != hometown) {
                    min = Math.min(min, cnt - 1);
                    return;
                }

                for (int i = 0; i < 4; i++) {
                    int ny = cy + dy[i];
                    int nx = cx + dx[i];

                    if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
                        if (map[ny][nx] != hometown && !visited[ny][nx]) {
                            visited[ny][nx] = true;
                            q.add(new int[]{ny, nx, cnt + 1});
                        }
                    }
                }
            }
        }

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int num = 2;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 1 && !visited[i][j]) {
                        dfs(i, j, num++);
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(map[i][j] > 1) {
                        bfs(i, j, map[i][j]);
                    }
                }
            }
            System.out.println(min);
        }
    }