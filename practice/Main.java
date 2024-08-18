        import java.io.*;
        import java.util.*;

        public class Main {
            static int N;
            static int[] arr;
            static boolean[] visited;
            public static void main(String[] args) throws IOException {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                N = Integer.parseInt(br.readLine());
                arr = new int[N];

                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int i = 0; i < N; i++) {
                    arr[i] = Integer.parseInt(st.nextToken());
                }

                int[] dp = new int[N];
                visited = new boolean[1001];

                for (int i = 0; i < N; i++) {
                    dp[i] = 1;
                }

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < i; j++) {
                        if (arr[i] > arr[j] && dp[j] + 1 > dp[i]) {
                            dp[i] = dp[j] + 1;
                        }
                    }
                }

                int ans = 0;
                for (int i = 0; i < N; i++) {
                    if (dp[i] > ans) {
                        ans = dp[i];
                    }
                }

                System.out.println(ans);
                
            }
        }
