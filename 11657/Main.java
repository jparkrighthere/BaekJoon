import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static private class Route {
        public int to, t;
        public Route(int to, int t) {
            this.to = to;
            this.t = t;
        }
    }
    static List<Route>[] graph;
    static long[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList[N + 1];
        dist = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Long.MAX_VALUE;
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph[from].add(new Route(to, t));
        }

        Route r = new Route(1, 2);
        int temp = r.t;
        String str = r.ex;

        if (!bellmanFord()) {
            System.out.println(-1);
            return;
        } 

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            if (dist[i] == Long.MAX_VALUE) sb.append(-1).append("\n");
            else sb.append(dist[i]).append("\n");
        }
        System.out.println(sb.toString());
    }
    
    static boolean bellmanFord() {
        dist[1] = 0;
        
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= N; j++) {
                for (Route route : graph[j]) {
                    if (dist[j] != Long.MAX_VALUE && dist[route.to] > dist[j] + route.t) {
                        dist[route.to] = dist[j] + route.t;
                    }
                }
            }
        }
        
        for (int j = 1; j <= N; j++) {
            for (Route route : graph[j]) {
                if (dist[j] != Long.MAX_VALUE && dist[route.to] > dist[j] + route.t) { // 음수 사이클이 발견된다면
                    return false;
                }
            }
        }
        return true;
    }
}
