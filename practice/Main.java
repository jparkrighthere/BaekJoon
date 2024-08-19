import java.io.*;
import java.util.*;

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

        long count = 0;
        for(int i = 0; i < N; i++) {
            int num = arr[i];
            int low = i + 1;
            int high = arr.length - 1;

            while (low < high) {
                int sum = num + arr[low] + arr[high];

                if (sum == 0) {
                    int left = 1;
                    int right = 1;
                    if (arr[low] == arr[high]) {
                        int n = high - low + 1;
                        count += n * (n - 1) / 2;
                        break;
                    }

                    while (arr[low] == arr[low + 1]) {
                        left++;
                        low++;
                    }

                    while (arr[high] == arr[high - 1]) {
                        right++;
                        high--;
                    }
                    count += left * right;
                }

                if (sum > 0) {
                    high--;
                }
                else {
                    low++;
                }
            }
        }

        System.out.println(count);
    }
}
