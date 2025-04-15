import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[N];
        char[] locations = br.readLine().toCharArray();
        int answer = 0;
        for (int i = 0; i < N; i++) {
            if (locations[i] == 'P') {
                for (int j = Math.max(i - K, 0); j <= Math.min(i + K, N - 1); j++) {
                    if (locations[j] == 'H' && !visited[j]) {
                        visited[j] = true;
                        answer++;
                        break;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
