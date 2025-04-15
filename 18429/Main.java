import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static int[] arr;
    static boolean[] visited;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        backtrack(0, new int[N]);
        System.out.println(count);
    }

    static void backtrack(int depth, int[] temp) {
        if (depth == N) {
            calculate(temp);
            // System.out.println(Arrays.toString(temp));
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                temp[depth] = arr[i];
                backtrack(depth + 1, temp);
                visited[i] = false;
            }
        }
    }

    static void calculate(int[] temp) {
        int weight = 500;
        for (int i = 0; i < N; i++) {
            weight += temp[i];
            weight -= K;

            if (weight < 500) return;
        }

        count++;
    }
}
