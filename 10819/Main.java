import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] arr;
    static int[] result;
    static int sum;
    static int max = Integer.MIN_VALUE;

    static void dfs(int depth, int[] result, boolean[] visited) {
        if (depth == N) {
            sum = 0;
            for (int i = 0; i < N - 1; i++) {
                sum += Math.abs(result[i] - result[i+1]);
            }
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result[depth] = arr[i];
                dfs(depth + 1, result, visited);
                visited[i] = false;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        result = new int[N];
        dfs(0, result, new boolean[N]);
        System.out.println(max);
    }
}
