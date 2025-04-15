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

        int left = 0;
        int right = arr.length - 1;
        int min = Integer.MAX_VALUE;
        int i = -1;
        int j = -1;

        while (left < right) {
            int sum = Math.abs(arr[left] + arr[right]);

            if (sum <= min) {
                min = sum;
                i = arr[left];
                j = arr[right];
            }

            if (arr[left] + arr[right] == 0) break;
            if (arr[left] + arr[right] < 0) left++;
            else if (arr[left] + arr[right] > 0) right--;
        }

        System.out.println(i + " " + j);
    }
}

