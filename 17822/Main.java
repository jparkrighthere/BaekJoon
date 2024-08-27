import java.util.*;
import java.io.*;

public class Main {
    static int N, M, T;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int multipleOf = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int rotateNum = Integer.parseInt(st.nextToken());

            solve(multipleOf, dir, rotateNum);
            delete();
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sum += map[i][j];
            }
        }
        System.out.println(sum);
    }

    static void solve(int multipleOf, int dir, int rotateNum) {
        for (int i = 0; i < N; i++) {
            if ((i + 1) % multipleOf == 0) {
                int[] temp = new int[M];
                for (int j = 0; j < M; j++) {
                    if (dir == 0) {
                        temp[(j + rotateNum) % M] = map[i][j];
                    }
                    else if (dir == 1) {
                        temp[((j + M) - (rotateNum % M)) % M] = map[i][j];
                    }
                }
                // update
                for (int j = 0; j < M; j++) {
                    map[i][j] = temp[j];
                }
            }
        }
    }
    
    static void delete() {
        boolean[][] deleted = new boolean[N][M];
        boolean hasAdjacent = false;

         for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) continue;
                int num = map[i][j];

                // 좌우
                if (map[i][(j + 1) % M] == num) {
                    deleted[i][j] = true;
                    deleted[i][(j + 1) % M] = true;
                    hasAdjacent = true;
                }
                if (map[i][(j - 1 + M) % M] == num) {
                    deleted[i][j] = true;
                    deleted[i][(j - 1 + M) % M] = true;
                    hasAdjacent = true;
                }

                //상하
                if (i > 0 && map[i - 1][j] == num) {
                    deleted[i][j] = true;
                    deleted[i-1][j] = true;
                    hasAdjacent = true;
                }
                if (i < N - 1 && map[i + 1][j] == num) {
                    deleted[i][j] = true;
                    deleted[i+1][j] = true;
                    hasAdjacent = true;
                }
            }
        }

        if (hasAdjacent) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (deleted[i][j]) {
                        map[i][j] = 0;
                    }
                }
            }
        }
        else {
            calculate();
        }
    }

    static void calculate() {
        int count = 0;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    sum += map[i][j];
                    count++;
                }
            }
        }
        if (count == 0) return;

        double avg = (double) sum / count;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    if (map[i][j] > avg) {
                        map[i][j] = map[i][j] - 1;
                    }
                    else if (map[i][j] < avg) {
                        map[i][j] = map[i][j] + 1;
                    }
                }
            }
        }

    }
}
