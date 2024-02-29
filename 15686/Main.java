import java.util.*;
import java.io.*;

public class Main {
    static public class Jwapyo {
        int x;
        int y;

        public Jwapyo(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static int MIN = Integer.MAX_VALUE;
    static int N,M;
    static int[][] map;
    static List<Jwapyo> house;
    static List<Jwapyo> chicken;
    static boolean[] visited;

    static int calculate(List<Jwapyo> pick) {
        int totalDistance = 0;
        for (Jwapyo i : house) {
            int minDist = Integer.MAX_VALUE;
            for (Jwapyo j : pick) {
                int dist = Math.abs(i.y - j.y) + Math.abs(i.x - j.x);
                minDist = Math.min(minDist, dist);
            }
            totalDistance += minDist;
        }
        return totalDistance;
    }

    static void dfs(int index, int count) {
        if (count == M) {
            List<Jwapyo> pick = new ArrayList<>();
            for (int i = 0; i < chicken.size(); i++) {
                if (visited[i]) {
                    pick.add(chicken.get(i));
                }
            }
            MIN = Math.min(MIN, calculate(pick));
            return;
        }

        if (index == chicken.size()) {
            return;
        }

        visited[index] = true;
        dfs(index + 1, count + 1); // 선택하고 다음 치킨집으로
        
        visited[index] = false;
        dfs(index + 1, count); // 선택하지 않고 다음 치킨집으로
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        house = new ArrayList<>();
        chicken = new ArrayList<>();
        visited = new boolean[13];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    house.add(new Jwapyo(j,i));
                }
                if (map[i][j] == 2) {
                    chicken.add(new Jwapyo(j,i));
                }
            }
        }
        
        dfs(0, 0);
        System.out.println(MIN);
    }
}
