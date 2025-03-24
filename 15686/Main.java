import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int min = Integer.MAX_VALUE;
    static int chickenSize = 0;
    static List<int[]> homes = new ArrayList<>();
    static List<int[]> chickens = new ArrayList<>();
    static boolean[] selected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) homes.add(new int[]{i, j});
                if (map[i][j] == 2) chickens.add(new int[] {i, j});
            }
        }
        selected = new boolean[chickens.size()];

        backtrack(0, 0);
        System.out.println(min);
    }

    static void backtrack(int start, int count) {
        if (count == M) {
            int sum = 0;
            for (int i = 0; i < homes.size(); i++) {
                int dist = Integer.MAX_VALUE;
                int y1 = homes.get(i)[0];
                int x1 = homes.get(i)[1];
                for (int j = 0; j < chickens.size(); j++) {
                    if (selected[j]) {
                        int y2 = chickens.get(j)[0];
                        int x2 = chickens.get(j)[1];
                        dist = Math.min(dist, calculateDist(y1, x1, y2, x2));
                    }
                }
                sum += dist;
            }
            min = Math.min(min, sum);
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            if (!selected[i]) {
                selected[i] = true;
                backtrack(i + 1, count + 1);
                selected[i] = false;
            }
        }
    }

    static int calculateDist(int y1, int x1, int y2, int x2) {
        return Math.abs(y1 - y2) + Math.abs(x1 - x2);
    }
}
