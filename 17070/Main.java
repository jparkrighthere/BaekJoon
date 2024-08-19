import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        Queue<int[]> q = new LinkedList<>();
        check = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    check[i][j] = true;
                }
            }
        }

        if (check[N-1][N-1]) {
            System.out.println(0);
            return;
        }

        q.add(new int[] {0, 1, 0}); // status 가로:0, 세로:1, 대각선:2
        int count = 0;

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int cy = pos[0];
            int cx = pos[1];
            int status = pos[2];

            if (cy == N -1 && cx == N -1) {
                count++;
                continue;
            }

            int ny, nx;
            switch(status) {
                case 0:
                    // 가로
                    ny = cy;
                    nx = cx + 1;
                    if (ny >= 0 && ny < N && nx >= 0 && nx < N && map[ny][nx] == 0) {
                        q.add(new int[]{ny, nx, 0});
                    }

                    // 대각선
                    ny = cy + 1;
                    nx = cx + 1;
                    if (ny >= 0 && ny < N && nx >= 0 && nx < N && map[ny][nx] == 0 && map[ny][cx] == 0 && map[cy][nx] == 0) {
                        q.add(new int[]{ny, nx, 2});
                    }
                    break;
                case 1:
                    // 세로
                    ny = cy + 1;
                    nx = cx;
                    if (ny >= 0 && ny < N && nx >= 0 && nx < N && map[ny][nx] == 0) {
                        q.add(new int[]{ny, nx, 1});
                    }

                    // 대각선
                    ny = cy + 1;
                    nx = cx + 1;
                    if (ny >= 0 && ny < N && nx >= 0 && nx < N && map[ny][nx] == 0 && map[ny][cx] == 0 && map[cy][nx] == 0) {
                        q.add(new int[]{ny, nx, 2});
                    }
                    break;
                case 2:
                    // 가로
                    ny = cy;
                    nx = cx + 1;
                    if (ny >= 0 && ny < N && nx >= 0 && nx < N && map[ny][nx] == 0) {
                        q.add(new int[]{ny, nx, 0});
                    }

                    // 세로
                    ny = cy + 1;
                    nx = cx;
                    if (ny >= 0 && ny < N && nx >= 0 && nx < N && map[ny][nx] == 0) {
                        q.add(new int[]{ny, nx, 1});
                    }

                    // 대각선
                    ny = cy + 1;
                    nx = cx + 1;
                    if (ny >= 0 && ny < N && nx >= 0 && nx < N && map[ny][nx] == 0 && map[ny][cx] == 0 && map[cy][nx] == 0) {
                        q.add(new int[]{ny, nx, 2});
                    }
                    break;
            }
        }

        System.out.println(count);
    }
}
