import java.util.*;
import java.io.*;

public class Main {
    static int[][] map;
    static int[][] dxy = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; //동, 서, 북, 남
    static int N, M, y, x, count;
    static int[] dice = {0, 0, 0, 0, 0, 0}; // 앞, 위, 뒤, 아래, 왼쪽, 오른쪽

    static void roll(int ny, int nx, int index) {

        int[] nDice = dice.clone();

        if (index == 0) { //동쪽
            nDice[0] = dice[0];
            nDice[1] = dice[4];
            nDice[2] = dice[2];
            nDice[3] = dice[5];
            nDice[4] = dice[3];
            nDice[5] = dice[1];
        }

        else if (index == 1) { //서쪽
            nDice[0] = dice[0];
            nDice[1] = dice[5];
            nDice[2] = dice[2];
            nDice[3] = dice[4];
            nDice[4] = dice[1];
            nDice[5] = dice[3];
        }

        else if (index == 2) { //북쪽
            nDice[0] = dice[1];
            nDice[1] = dice[2];
            nDice[2] = dice[3];
            nDice[3] = dice[0];
            nDice[4] = dice[4];
            nDice[5] = dice[5];
        }

        else if (index == 3) { //남쪽
            nDice[0] = dice[3];
            nDice[1] = dice[0];
            nDice[2] = dice[1];
            nDice[3] = dice[2];
            nDice[4] = dice[4];
            nDice[5] = dice[5];
        }

        dice = nDice.clone();
    }

    static boolean isRange(int ny, int nx) {
        if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        count = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < count; ++i) {
            int index = Integer.parseInt(st.nextToken()) - 1;
            int ny = y + dxy[index][0];
            int nx = x + dxy[index][1];
            if (isRange(ny,nx)) {
                roll(ny, nx, index);
                if (map[ny][nx] == 0) {
                    map[ny][nx] = dice[3];
                }
                else {
                    dice[3] = map[ny][nx];
                    map[ny][nx] = 0;
                }
                y = ny;
                x = nx;
                System.out.println(dice[1]);
            }
        }
    }
}
