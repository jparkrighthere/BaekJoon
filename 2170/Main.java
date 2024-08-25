import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static List<int[]> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            list.add(new int[]{A, B});
        }

        Collections.sort(list, (a,b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        int sum = 0;
        int left = Integer.MIN_VALUE;
        int right = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            int[] interval = list.get(i);
            int start = interval[0];
            int end = interval[1];

            if (right < start) {
                sum += (right - left);
                left = start;
                right = end;
            } else {
                right = Math.max(right, end);
            }
        }
        sum += right - left;

        System.out.println(sum);
        
    }
}
