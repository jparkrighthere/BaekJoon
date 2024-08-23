import java.util.*;
import java.io.*;

public class Main {
    static int T, N;
    static int[] arr;
    static boolean[] check, visited;
    static int count = 0;

    static void dfs(int idx) {
        if (check[idx]) return; // 이전에 이미 검사함
        if (visited[idx]) { // 방문을 했었다 == 사이클 구성원이다
            check[idx] = true;
            count++;
        }
        visited[idx] = true;
        dfs(arr[idx]);
        check[idx] = true; // 탐색 후 설정
        visited[idx] = false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while(T --> 0) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N + 1];
            check = new boolean[N + 1];
            visited = new boolean[N + 1];
            count = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= N; i++) {
                if (!check[i]) {
                    dfs(i);
                }
            }

            System.out.println(N - count);
        }
    }
}
