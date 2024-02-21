import java.util.*;
import java.io.*;

public class Main {
    static int r;
    static int c;
    static int t;
    static int[][] map;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int cleaner = -1;

    static void spread() {
        int[][] copyMap = new int[r][c];
        for (int i = 0; i < r; i++) {
            copyMap[i] = map[i].clone();
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] < 5) {
                    continue;
                }

                else {
                    int spread = map[i][j] / 5;
                    int count = 0;
                    for (int k = 0; k < 4; k++) {
                        int ny = i + dy[k];
                        int nx = j + dx[k];

                        if (ny < 0 || nx < 0 || ny >= r || nx >= c || map[ny][nx] == -1) {
                            continue;
                        }

                        copyMap[ny][nx] += spread;
                        count++;
                    }
                    copyMap[i][j] = copyMap[i][j] - spread * count;
                }
            }
        }
        map = copyMap;
    }

    static void clean() {
        int top = cleaner;
        int down = cleaner + 1;

        // 반시계
        for (int i = top - 1; i > 0; i--) { // 아래
            map[i][0] = map[i-1][0];
        }
        for (int i = 0; i < c - 1; i++) { // 왼쪽
            map[0][i] = map[0][i+1];
        }
        for (int i = 0; i < top; i++) { // 위
            map[i][c-1] = map[i+1][c-1];
        }
        for (int i = c - 1; i > 1; i--) { // 오른쪽
            map[top][i] = map[top][i-1];
        }
        map[top][1] = 0;

        // 시계
        for (int i = down + 1; i < r - 1; i++) { // 위
            map[i][0] = map[i+1][0];
        }
        for (int i = 0; i < c - 1; i++) { // 왼쪽
            map[r-1][i] = map[r-1][i+1];
        }
        for (int i = r - 1; i > down; i--) { // 아래
            map[i][c-1] = map[i-1][c-1];
        }
        for (int i = c - 1; i > 1; i--) { // 오른쪽
            map[down][i] = map[down][i-1];
        }
        map[down][1] = 0;
    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        map = new int[r][c];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == -1 && cleaner == -1) {
                    cleaner = i;
                }
            }
        }
        int pm = 0;

        while (t --> 0) {
            spread();
            clean();
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == -1) {
                    continue;
                }
                pm += map[i][j];
            }
        }

        System.out.println(pm);

    }

}