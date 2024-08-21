import java.util.*;
import java.io.*;

public class Main {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[][] map;
    static Set<String> set = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[5][5];

        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                backtrack(0, new int[6], i, j);
            }
        }

        System.out.println(set.size());
    }

    static void backtrack(int depth, int[] temp, int y, int x) {
        if (depth == 6) {
            StringBuilder sb = new StringBuilder();
            for (int i : temp) {
                sb.append(i);
            }
            set.add(sb.toString());
            return;
        }

        temp[depth] = map[y][x];

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny >= 0 && ny < 5 && nx >= 0 && nx < 5) {
                backtrack(depth + 1, temp, ny, nx);
            }
        }
    }
}
