import java.util.*;
import java.io.*;

public class Main { //패스 못함 미완성
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int ret;

    static class Fish {
        int y, x, dir;
        boolean isAlive;

        Fish(int y, int x, int dir, boolean isAlive) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.isAlive = isAlive;
        }
    }

    static void dfs(int[][] map, Fish[] fish, int shark_y, int shark_x, int sum) {

        int[][] copyMap = new int[4][4];
        Fish[] copyFish = new Fish[16];
        

        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 4; k++) {
                copyMap[j][k] = map[j][k];
            }
        }

        for (int j = 0; j < 16; j++) {
            copyFish[j] = fish[j];
        }

        // eat
        int eat = copyMap[shark_y][shark_x];
        int shark_dir = copyFish[eat].dir;
        copyFish[eat].isAlive = false;
        copyMap[shark_y][shark_x] = -1; // 물고기 없음

        sum += eat;
        if (ret < sum) {
            ret = sum;
        }


        //Fish move
        for (int i = 0; i < 16; i++) {
            if (!copyFish[i].isAlive) continue;

            int cy = copyFish[i].y;
            int cx = copyFish[i].x;
            int cd = copyFish[i].dir;

            int ny = cy + dy[cd];
            int nx = cx + dx[cd];
            int nd = cd;

            while (ny < 0 || ny >= 4 || nx < 0 || nx >= 4 || (ny == shark_y && nx == shark_x)) {
                nd = (nd + 1) % 8;
                ny = cy + dy[nd];
                nx = cx + dx[nd];
            }

            if (copyMap[ny][nx] != -1) {
                int next = copyMap[ny][nx];
                copyFish[next].y = cy;
                copyFish[next].x = cx;
                copyFish[i].y = ny;
                copyFish[i].x = nx;
                copyFish[i].dir = nd;

                copyMap[ny][nx] = i;
                copyMap[cy][cx] = next;
            }
            else{
                copyFish[i].y = ny;
                copyFish[i].x = nx;
                copyFish[i].dir = nd;

                copyMap[ny][nx] = i;
                copyMap[cy][cx] = -1;
            }
        }

        // Shark move
        for (int i = 1; i < 4; i++) {
            int ny = shark_y + dy[shark_dir] * i;
            int nx = shark_x + dx[shark_dir] * i;

            if (ny < 0 || ny >= 4 || nx < 0 || nx >= 4) break;
            if (copyMap[ny][nx] != -1) {
                dfs(copyMap, copyFish, ny, nx, sum);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] map = new int[4][4];
        Fish[] fish = new Fish[16];

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                fish[a] = new Fish(i, j, b, true);
                map[i][j] = a;
            }
        }

        dfs(map, fish, 0, 0, 0);

        System.out.println(ret);
    }
}
