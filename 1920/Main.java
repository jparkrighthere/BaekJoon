import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());

            int left = 0;
            int right = N - 1;
            boolean flag = false;

            while (left <= right) {
                int mid = (left + right) / 2;
                if (arr[mid] == num) {
                    flag = true;
                    sb.append(1).append("\n");
                    break;
                }

                else if (arr[mid] < num) {
                    left = mid + 1;
                }

                else {
                    right = mid - 1;
                }
            }

            if (!flag) sb.append(0).append("\n");
        }
        System.out.println(sb.toString());
    }
}

