import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;

    static void dfs(int idx, int count) {
        if (count== N/2) {
            calculate();
        }

        for (int i = idx; i < N; ++i) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i+1, count+1);
                visited[i] = false;
            }
        }
    }

    static void calculate() {
        int team1 = 0;
        int team2 = 0;

        for (int i = 0; i < N - 1; ++i) {
            for (int j = i + 1; j < N; ++j) {
                if (visited[i] == true && visited[j] == true) {
                    team1 += map[i][j];
                    team1 += map[j][i];
                }

                if (visited[i] == false && visited[j] == false) {
                    team2 += map[i][j];
                    team2 += map[j][i];
                }
            }
        }
        int val = Math.abs(team1 - team2);

        if (val == 0) {
            System.out.println(val);
            System.exit(0);
        }

        min = Math.min(min, val);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N];

        for (int i = 0; i < N; ++i) {
            visited[i] = false;
        }

        dfs(0, 0);
        System.out.println(min);
    }
}
