import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] map;
    static int max = 0;
    static int count = 0;

    static void dfs(int num) {
        if (num == 5) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    max = Math.max(max, map[i][j]);
                }
            }
            return;
        }
        int copyMap[][] = new int[N][N];
        for (int i =0; i < N; i++) {
            copyMap[i] = map[i].clone();
        }

        for (int i = 0; i < 4; i++) {
            move(i);
            dfs(num + 1);
            for (int j = 0; j < N; j++) {
                map[j] = copyMap[j].clone();
            }
        }
    }

    static void move(int dir) {
        // up
        if (dir == 0) {
            for (int i = 0; i < N; i++) {
                int block = 0;
                int index = 0;
                for (int j = 0; j < N; j++) {
                    if (map[j][i] != 0) {
                        if (block == map[j][i]) {
                            map[index - 1][i] = block * 2;
                            block = 0;
                            map[j][i] = 0;
                        }
                        else {
                            block = map[j][i];
                            map[j][i] = 0;
                            map[index][i] = block;
                            index++;
                        }
                    }
                }
            }
        }
        // down
        if (dir == 1) {
            for (int i = 0; i < N; i++) {
                int block = 0;
                int index = N - 1;
                for (int j = N -1; j >= 0; j--) {
                    if (map[j][i] != 0) {
                        if (block == map[j][i]) {
                            map[index + 1][i] = block * 2;
                            block = 0;
                            map[j][i] = 0;
                        }
                        else {
                            block = map[j][i];
                            map[j][i] = 0;
                            map[index][i] = block;
                            index--;
                        }
                    }
                }
            }
        }
        // left
        if (dir == 2) {
            for (int i = 0; i < N; i++) {
                int block = 0;
                int index = 0;
                for (int j = 0; j < N; j++) {
                    if (map[i][j] != 0) {
                        if (block == map[i][j]) {
                            map[i][index - 1] = block * 2;
                            block = 0;
                            map[i][j] = 0;
                        }
                        else {
                            block = map[i][j];
                            map[i][j] = 0;
                            map[i][index] = block;
                            index++;
                        }
                    }
                }
            }
        }
        // right
        if (dir == 3) {
            for (int i = 0; i < N; i++) {
                int block = 0;
                int index = N - 1;
                for (int j = N - 1; j >= 0; j--) {
                    if (map[i][j] != 0) {
                        if (block == map[i][j]) {
                            map[i][index + 1] = block * 2;
                            block = 0;
                            map[i][j] = 0;
                        }
                        else {
                            block = map[i][j];
                            map[i][j] = 0;
                            map[i][index] = block;
                            index--;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0);
        System.out.println(max);
    }
}
