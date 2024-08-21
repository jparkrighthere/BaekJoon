import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static class Pos {
        int x, y, cnt;
        boolean destroyed;

        public Pos(int y, int x, int cnt, boolean destroyed) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.destroyed = destroyed;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }
        int min = Integer.MAX_VALUE;
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(0, 0, 1, false));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Pos pos = q.poll();
            int cy = pos.y;
            int cx = pos.x;
            int cnt = pos.cnt;

            if (cy == N - 1 && cx == M - 1) {
                if (cnt < min) {
                    min = cnt;
                }
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int ny = cy + dy[i];
                int nx = cx + dx[i];

                if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
                    if (map[ny][nx] == 0) {
                        if (!pos.destroyed && !visited[ny][nx][0]) {
                            visited[ny][nx][0] = true;
                            q.add(new Pos(ny, nx, cnt + 1, false));
                        }
                        else if (pos.destroyed && !visited[ny][nx][1]) {
                            visited[ny][nx][1] = true;
                            q.add(new Pos(ny, nx, cnt + 1, true));
                        }
                    } 

                    if (map[ny][nx] == 1 && !pos.destroyed) {
                        visited[ny][nx][1] = true;
                        q.add(new Pos(ny, nx, cnt + 1, true));
                    }
                }
            }
        }

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }
}
