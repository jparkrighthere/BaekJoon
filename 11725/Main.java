import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static List<List<Integer>> tree;
    static int[] parents;
    static boolean[] visited;

    static void dfs(int idx) {
        visited[idx] = true;
        for (int node : tree.get(idx)) {
            if (!visited[node]) {
                parents[node] = idx;
                dfs(node);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st= new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        tree = new ArrayList<>();
        for (int i =0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }
        parents = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        dfs(1);
        for (int i = 2; i <= N; i++) {
            bw.write(parents[i] +"\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
