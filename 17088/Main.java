    import java.util.*;
    import java.io.*;

    public class Main {
        static int N;
        static int[] arr;
        static int min = Integer.MAX_VALUE;

        static void bruteforce(int idx, int cnt, int prev, int diff) {
            if (idx == arr.length) {
                if (cnt < min) {
                    min = cnt;
                }
                return;
            }

            if (prev - (arr[idx] + 1) == diff) {
                bruteforce(idx + 1, cnt + 1, arr[idx] + 1, diff);
            }

            if (prev - (arr[idx] - 1) == diff) {
                bruteforce(idx + 1, cnt + 1, arr[idx] - 1, diff);
            }

            if (prev - (arr[idx]) == diff) {
                bruteforce(idx + 1, cnt, arr[idx], diff);
            }
        }

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            arr = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            if (N > 1) {
                bruteforce(2, 2, arr[1] - 1, (arr[0] - 1) - (arr[1] - 1));
                bruteforce(2, 1, arr[1], (arr[0] - 1) - arr[1]);
                bruteforce(2, 2, arr[1] + 1, (arr[0] - 1) - (arr[1] + 1));
        
                bruteforce(2, 1, arr[1] - 1, arr[0] - (arr[1] - 1));
                bruteforce(2, 0, arr[1], arr[0] - arr[1]);
                bruteforce(2, 1, arr[1] + 1, arr[0] - (arr[1] + 1));
        
                bruteforce(2, 2, arr[1] - 1, (arr[0] + 1) - (arr[1] - 1));
                bruteforce(2, 1, arr[1], (arr[0] + 1) - arr[1]);
                bruteforce(2, 2, arr[1] + 1, (arr[0] + 1) - (arr[1] + 1));
        
            } else {
                System.out.println(0);
                return;
            }

            System.out.println(min == Integer.MAX_VALUE ? -1 : min);
        }
    }
