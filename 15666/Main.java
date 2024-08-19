import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static List<Integer> arr;
    static StringBuilder sb = new StringBuilder();

    static void backtrack(int depth, int[] temp, int lastNum) {
        if (depth == M) {
            for (int i : temp) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < arr.size(); i++) {
            if (lastNum <= arr.get(i)) {
                temp[depth] = arr.get(i);
                backtrack(depth + 1, temp, arr.get(i));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (!arr.contains(num)){
                arr.add(num);
            }
        }

        Collections.sort(arr);
        backtrack(0, new int[M], 0);

        System.out.println(sb.toString());
    }
}
