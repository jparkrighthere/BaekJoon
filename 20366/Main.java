import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] arr;
    static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                int firstSum = arr[i] + arr[j];
                findClosestPair(i, j, firstSum);
            }
        }

        System.out.println(minDiff);
    }

    static void findClosestPair(int idx1, int idx2, int firstSum) {
        int left = 0, right = N - 1;

        while (left < right) {
            if (left == idx1 || left == idx2) {
                left++;
                continue;
            }
            if (right == idx1 || right == idx2) {
                right--;
                continue;
            }

            int secondSum = arr[left] + arr[right];
            minDiff = Math.min(minDiff, Math.abs(firstSum - secondSum));

            if (secondSum < firstSum) {
                left++;
            } else {
                right--;
            }
        }
    }
}
