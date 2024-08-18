import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] map, count;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N];
        count = new int[N + 1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int k = Integer.parseInt(st.nextToken());
            count[a] += k;
            if (b + 1 < N) {
                count[b+1] -= k;
            }
        }
        
        for (int i = 1; i < N; i++) {
            count[i] += count[i-1];
        }

        for (int i = 0; i < N; i++) {
            map[i] += count[i];
            System.out.print(map[i] + " ");
        }
        System.out.println();
    }
}
