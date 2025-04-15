import java.util.*;
import java.io.*;

public class Main {
    static int T, N;
    static int[] arr;
    static boolean[] visited;
    static boolean[] checked;
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while(T --> 0) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N + 1];
            checked = new boolean[N + 1];
            visited = new boolean[N + 1];
            count = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= N; i++) {
                if (!checked[i]) dfs(i);
            }

            System.out.println(N - count);
        }
    }

    static void dfs(int index) {
        if (checked[index]) return;

        if (visited[index]) {
            count++;
            checked[index] = true;
        }

        visited[index] = true;
        dfs(arr[index]);
        // 이미 돌아보고 난 index는 그냥 checked 처리
        checked[index] = true;
        visited[index] = false;
    }
}
