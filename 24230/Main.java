import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] colors;
    static List<Integer>[] graph;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        colors = new int[N + 1];
        graph = new ArrayList[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            colors[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(1, 0, 0);
        System.out.println(answer);
    }

    static void dfs(int node, int parentColor, int parent) {
        if (colors[node] != parentColor) answer++;

        for (int next : graph[node]) {
            if (next != parent) {
                dfs(next, colors[node], node);
            }
        }
    }
}
