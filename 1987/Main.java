import java.util.*;
import java.io.*;

public class Main {
    static int R, C;
    static String[][] map;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int max = Integer.MIN_VALUE;

    static void dfs(StringBuilder sb, int y, int x) {
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            String str = sb.toString();
            if (ny >= 0 && ny < R && nx >= 0 && nx < C && !str.contains(map[ny][nx])) {
                sb.append(map[ny][nx]);
                dfs(sb, ny, nx);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        max = Math.max(max, sb.length());
        return;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new String[R][C];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = String.valueOf(line.charAt(j));
            }
        }
        sb.append(map[0][0]);
        dfs(sb, 0, 0);
        System.out.println(max);
    }
}
