import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] nArr, mArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Set<Integer> set = new HashSet<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        StringBuilder sb = new StringBuilder();
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            if (set.contains(Integer.parseInt(st.nextToken()))) {
                sb.append(1).append("\n");
            }
            else sb.append(0).append("\n");
        }

        System.out.println(sb.toString());
        // nArr = new int[N];

        // StringTokenizer st = new StringTokenizer(br.readLine());
        // for (int i = 0; i < N; i++) {
        //     nArr[i] = Integer.parseInt(st.nextToken());
        // }

        // M = Integer.parseInt(br.readLine());
        // mArr = new int[M];
        // st = new StringTokenizer(br.readLine());
        // for (int i = 0; i < M; i++) {
        //     mArr[i] = Integer.parseInt(st.nextToken());
        // }

        // StringBuilder sb = new StringBuilder();
    }
}

