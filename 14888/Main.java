import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] arr;
    static int plus, minus, mult, div;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        plus = Integer.parseInt(st.nextToken());
        minus = Integer.parseInt(st.nextToken());
        mult = Integer.parseInt(st.nextToken());
        div = Integer.parseInt(st.nextToken());

        backtrack(arr[0], 1);

        System.out.println(max);
        System.out.println(min);
    }

    static void backtrack(int result, int idx) {
        if (idx == N) {
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        if (plus > 0) {
            plus--;
            backtrack(result + arr[idx], idx + 1);
            plus++;
        }
        if (minus > 0) {
            minus--;
            backtrack(result - arr[idx], idx + 1);
            minus++;
        }
        if (mult > 0) {
            mult--;
            backtrack(result * arr[idx], idx + 1);
            mult++;
        }
        if (div > 0) {
            div--;
            backtrack(result / arr[idx], idx + 1);
            div++;
        }
    }
}
