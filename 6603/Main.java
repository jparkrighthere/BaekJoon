import java.util.*;
import java.io.*;

public class Main {
    static int[] arr;
    static boolean[] visited;
    static int K;
    static StringBuilder sb = new StringBuilder();

    static void backtrack(int depth, int at, int[] temp) {
        if (depth == 6) {
            for (int i : temp) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = at; i < K; i++) {
            if (!visited[i]) {
                visited[i] = true;
                temp[depth] = arr[i];
                backtrack(depth + 1, i + 1, temp);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            if (K == 0) {
                break;
            }
            arr = new int[K];
            visited = new boolean[K];

            for (int i = 0; i < K; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            backtrack(0, 0, new int[6]);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
