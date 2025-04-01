import java.util.*;
import java.io.*;

public class Main {
    static int V;
    static int ans = 0;
    static List<Edge>[] graph;
    static boolean[] visited;

    static class Edge {
        int to, weight;
        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        graph = new ArrayList[V + 1];

        for (int i = 0; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            while (true) {
                int next = Integer.parseInt(st.nextToken());
                if (next == -1) break;
                int weight = Integer.parseInt(st.nextToken());
                graph[num].add(new Edge(next, weight));
            }
        }

        // 트리의 임의의 한 점에서 가장 먼 노드를 찾고, 그 노드에서 가장 먼 거리 계산
        visited = new boolean[V + 1];
        int farthestNode = dfs(1, 0);

        // 다시 한 번 가장 먼 거리 찾기
        visited = new boolean[V + 1];
        ans = 0;
        dfs(farthestNode, 0);

        System.out.println(ans);
    }

    static int dfs(int node, int sum) {
        visited[node] = true; // 방문 체크

        if (sum > ans) {
            ans = sum;
        }

        int farthestNode = node;

        for (Edge edge : graph[node]) {
            if (!visited[edge.to]) { // 방문하지 않은 노드만 탐색
                int candidate = dfs(edge.to, sum + edge.weight);
                if (sum + edge.weight > ans) {
                    farthestNode = candidate;
                }
            }
        }

        return farthestNode;
    }
}
