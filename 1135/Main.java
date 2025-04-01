import java.util.*;
import java.io.*;


public class Main {
    static int N;
    static List<Integer>[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent != -1) tree[parent].add(i);
        }

        System.out.println(dfs(0));
    }

    static int dfs(int node) {
        if (tree[node].isEmpty()) {
            return 0;
        }

        List<Integer> times = new ArrayList<>();
        for (int child : tree[node]) {
            times.add(dfs(child));
        }

        Collections.sort(times, Collections.reverseOrder());
        int maxTime = 0;
        for (int i = 0; i < times.size(); i++) {
            maxTime = Math.max(maxTime, times.get(i) + i + 1); // i + 1은 전파 순서를 고려한 시간
        }

        return maxTime;
    }
}