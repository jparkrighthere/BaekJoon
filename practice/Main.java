import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    static void backtrack(int at, int depth, int[] temp) {
        if (depth == M) {
            for (int i : temp) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = at; i < N; i++) {
            temp[at] = arr[i];
            backtrack(i + 1, depth + 1, temp);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        backtrack(0, 0, new int[M]);
        System.out.println(sb.toString());
    }
}