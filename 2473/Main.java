import java.util.*;
import java.io.*;

public class Main {
    static int N;
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
        long best = Long.MAX_VALUE;
        int a = 0;
        int b = 0;
        int c = 0;

        for(int i = 0; i < N; i++) {
            int num = arr[i];
            int left = i + 1;
            int right = N - 1;

            while (left < right) {
                long sum = (long)num + (long)arr[left] + (long)arr[right];

                if (best > Math.abs(sum)) {
                    best = Math.abs(sum);
                    a = num;
                    b = arr[left];
                    c = arr[right];
                }

                if (sum >= 0) {
                    right--;
                }

                else {
                    left++;
                }
            }
        }
        System.out.println(a + " " + b + " " + c);
    }
}