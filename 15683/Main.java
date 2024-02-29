import java.util.*;
import java.io.*;

public class Main {

    public static class Cam {
        int x;
        int y;
        int type;
    }

    static int N;
    static int M;
    static int[][] map;
    static int total = 0;
    static int[] rotate = {4, 2, 4, 4, 1};
    static Cam[] cctv = new Cam[8];
    static int min = Integer.MAX_VALUE;


    static void dfs(int index) { //0부터
        if (index == total) { //cctv 총 개수까지 돌려보면
            int zero = 0;
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < M; ++j) {
                    if (map[i][j] == 0) {
                        ++zero;
                    }
                }
            }
            min = Math.min(min, zero);
            return;
        }

        int type = cctv[index].type - 1; // cctv 번호

        int[][] copyMap = new int[N][M];

        for (int i = 0; i < rotate[type]; ++i) { // 몇번을 rotate
            for (int j = 0; j < N; ++j) { // 백업
                copyMap[j] = map[j].clone();
            }

            if (type == 0) {
                visible(i, cctv[index]);
            }
            else if (type == 1) {
                visible(i, cctv[index]);
                visible(i+2, cctv[index]);
            }
            else if (type == 2) {
                visible(i, cctv[index]);
                visible(i+1, cctv[index]);
            }
            else if (type == 3) {
                visible(i, cctv[index]);
                visible(i+1, cctv[index]);
                visible(i+2, cctv[index]);
            }
            else if (type == 4) {
                visible(i, cctv[index]);
                visible(i+1, cctv[index]);
                visible(i+2, cctv[index]);
                visible(i+3, cctv[index]);
            }

            dfs(index+1);

            for (int j = 0; j < N; ++j) { // 복구
                map[j] = copyMap[j].clone();
            }
        }
    }

    static void visible(int dir, Cam cctv) {
        dir = dir % 4; // 0->동 1->북 2->서 3->남
        int x = cctv.x;
        int y = cctv.y;

        if (dir == 0) {
            for (int i = x + 1; i < M; ++i) {
                if (map[y][i] == 6) break;
                map[y][i] = -1;
            }
        }

        if (dir == 1) {
            for (int i = y - 1; i >= 0; --i) {
                if (map[i][x] == 6) break;
                map[i][x] = -1;
            }
        }

        if (dir == 2) {
            for (int i = x - 1; i >= 0; --i) {
                if (map[y][i] == 6) break;
                map[y][i] = -1;
            }
        }

        if (dir == 3) {
            for (int i = y + 1; i < N; ++i) {
                if (map[i][x] == 6) break;
                map[i][x] = -1;
            }
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];


        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctv[total] = new Cam();
                    cctv[total].type = map[i][j];
                    cctv[total].x = j;
                    cctv[total].y = i;
                    ++total;
                }
            }
        }
        dfs(0);
        System.out.println(min);
    }
}

