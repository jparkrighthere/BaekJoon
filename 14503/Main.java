import java.util.*;
import java.io.*;

public class Main {
    static int N, M, robotY, robotX, d;
    static int[][] map;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        
        st = new StringTokenizer(br.readLine());
        robotY = Integer.parseInt(st.nextToken());
        robotX = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        // robot move
        while (true) {
            // 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
            if (map[robotY][robotX] == 0) {
                map[robotY][robotX] = 2;
                count++;
            }

            boolean flag = false;
            for (int i = 0; i < 4; i++) {
                d = (d + 4 - 1) % 4;

                int ny = robotY + dy[d];
                int nx = robotX + dx[d];

                // 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
                if (ny >= 0 && ny < N && nx >= 0 && nx < M && map[ny][nx] == 0) {
                    robotY = ny;
                    robotX = nx;
                    flag = true;
                    break;
                }
            }

            // 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
            if (!flag) {
                int ny = robotY - dy[d];
                int nx = robotX - dx[d];
                if (ny >= 0 && ny < N && nx >= 0 && nx < M && map[ny][nx] != 1) {
                    robotY = ny;
                    robotX = nx;
                    continue;
                }
                else {
                    break;
                }
            }
        }

        System.out.println(count);
    }
}
