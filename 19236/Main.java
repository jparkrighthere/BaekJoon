import java.util.*;
import java.io.*;

public class Main {
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int max = Integer.MIN_VALUE;

    public static class Fish {
        int y, x, dir;
        boolean isAlive;

        Fish(int y, int x, int dir, boolean isAlive) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.isAlive = isAlive;
        }
    }

    static void dfs(int[][] map, Fish[] fish, int sharkY, int sharkX, int sharkDir, int sum) {
        int[][] copyMap = new int[4][4];
        Fish[] copyFish = new Fish[16];

        for (int i = 0; i < 4; i++) {
            copyMap[i] = map[i].clone();
        }
        copyFish = fish.clone();

        // shark eats
        int eat = copyMap[sharkY][sharkX];
        copyFish[eat].isAlive = false;
        sharkDir = copyFish[eat].dir;
        copyMap[sharkY][sharkX] = -1; // no fish

        sum += (eat + 1);

        // fish moves
        for (int i = 0; i < 16; i++) {
            Fish currentFish = copyFish[i];
            if (currentFish.isAlive) {
                int cy = currentFish.y;
                int cx = currentFish.x;
                int cd = currentFish.dir;

                int ny = cy + dy[cd];
                int nx = cx + dx[cd];
                int nd = cd;

                while (ny < 0 || ny >= 4 || nx < 0 || nx >= 4 || (ny == sharkY && nx == sharkX)) {
                    nd = (nd + 1) % 8;
                    ny = cy + dy[nd];
                    nx = cx + dx[nd];
                }

                if (copyMap[ny][nx] != -1) {
                    int next = copyMap[ny][nx];
                    Fish changeFish = copyFish[next];
                    changeFish.y = cy;
                    changeFish.x = cx;
                    copyMap[cy][cx] = next;

                    currentFish.y = ny;
                    currentFish.x = nx;
                    currentFish.dir = nd;
                    copyMap[ny][nx] = i;
                }

                else {
                    currentFish.y = ny;
                    currentFish.x = nx;
                    currentFish.dir = nd;
                    copyMap[ny][nx] = i;
                    copyMap[cy][cx] = -1;
                }
            }
        }

        // shark moves
        for (int i = 1; i <= 3; i++) {
            int ny = sharkY + dy[sharkDir] * i;
            int nx = sharkX + dx[sharkDir] * i;

            if (ny >= 0 && ny < 4 && nx >= 0 && nx < 4 && copyMap[ny][nx] != -1) {
                dfs(copyMap, copyFish, ny, nx, sharkDir, sum);
            }
        }

        max = Math.max(max, sum);
        return;
    }

    public static void main(String[] args) throws Exception {
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
        dfs(map, fish, 0, 0, 0, 0);

        System.out.println(max);
    }
}