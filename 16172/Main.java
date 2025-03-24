import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static class Edge implements Comparable<Edge> {
        int to, w;
        public Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }

        @Override
        public String toString() {
            return "(" + to + ", " + w + ")";
        }
    }

    static List<Edge>[] graph;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        graph = new List[N * M];
        for (int i = 0; i < N * M; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int d = 0; d < 4; d++) {
                    int ny = i + dy[d];
                    int nx = j + dx[d];
                    if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                    graph[i * M + j].add(new Edge(ny * M + nx, map[ny][nx] == 1 ? 1 : 0));
                }
            }
        }

        int[] dist = dijkstra(0);
        System.out.println(dist[N * M - 1]);
    }

    static int[] dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] dist = new int[N * M];
        Arrays.fill(dist, 10000);
        dist[start] = 0;
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int current = edge.to;

            for (Edge e : graph[current]) {
                int next = e.to;
                int nw = e.w;
                if (dist[current] + nw < dist[next]) {
                    dist[next] = dist[current] + nw;
                    pq.add(new Edge(next, dist[next]));
                }
            }
        }
        return dist;
    }
}