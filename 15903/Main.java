import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static long[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            Arrays.sort(arr);
            long N = arr[0] + arr[1];
            arr[0] = N;
            arr[1] = N;
        }

        long ans = 0;

        for (int i = 0; i < N; i++) {
            ans += arr[i];
        }
        System.out.println(ans);
    }
}
