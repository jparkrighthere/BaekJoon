import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static class Edge implements Comparable<Edge> {
        int to, time;
        public Edge(int to, int time) {
            this.to = to;
            this.time = time;
        }
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.time, o.time);
        }
    }

    static List<Edge>[] graph;
    static Map<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph[A].add(new Edge(B, C));
            graph[B].add(new Edge(A, C));
        }

        int[] arr = dijkstra(1);
        System.out.println(Arrays.toString(arr));

        // StringBuilder sb = new StringBuilder();
        // sb.append(map.size()).append("\n");
        // for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        //     sb.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        // }
        // System.out.print(sb);
    }

    static int[] dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        int[] weights = new int[N + 1];
        Arrays.fill(weights, Integer.MAX_VALUE);
        weights[start] = 0;

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int curNode = current.to;
            int curTime = current.time;

            if (weights[curNode] < curTime) continue;

            for (Edge next : graph[curNode]) {
                int nextNode = next.to;
                int nextTime = curTime + next.time;

                if (nextTime < weights[nextNode]) {
                    weights[nextNode] = nextTime;
                    pq.add(new Edge(nextNode, nextTime));
                    map.put(nextNode, curNode);
                }
            }
        }

        return weights;
    }
}