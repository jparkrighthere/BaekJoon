import java.util.*;
import java.io.*;

public class Main {
    static int T, N, M;
    static int[] A, B;
    static List<Integer> subA, subB;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        B = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        
        subA = new ArrayList<>();
        subB = new ArrayList<>();

        int sum;
        for (int i = 0; i < N; i++) {
            sum = 0;
            for (int j = i; j < N; j++) {
                sum += A[j];
                subA.add(sum);
            }
        }

        for (int i = 0; i < M; i++) {
            sum = 0;
            for (int j = i; j < M; j++) {
                sum += B[j];
                subB.add(sum);
            }
        }

        Collections.sort(subA);
        Collections.sort(subB, Collections.reverseOrder());

        int ptA = 0;
        int ptB = 0;
        long equalcnt = 0;

        while (ptA < subA.size() && ptB < subB.size()) {
            int add = subA.get(ptA) + subB.get(ptB);

            if (add == T) {
                long a = subA.get(ptA);
                long b = subB.get(ptB);
                long cntA = 0;
                long cntB = 0;

                while (ptA < subA.size() && subA.get(ptA) == a) {
                    cntA++;
                    ptA++;
                }

                while (ptB < subB.size() && subB.get(ptB) == b) {
                    cntB++;
                    ptB++;
                }

                equalcnt += cntA*cntB;
            }

            else if (add > T) ptB++;

            else if (add < T) ptA++;
        }
        System.out.println(equalcnt);
    }
}
