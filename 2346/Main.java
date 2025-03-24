import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static Deque<int[]> dq = new ArrayDeque<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            dq.add(new int[]{num, i + 1});
        }

        StringBuilder sb = new StringBuilder();
        while (!dq.isEmpty()) {
            int[] info = dq.pollFirst();
            int num = info[0];
            int index = info[1];
            sb.append(index).append(" ");

            if (dq.isEmpty()) break;

            if (num > 0) {
                for (int i = 0; i < num - 1; i++) {
                    dq.addLast(dq.pollFirst());
                }
            }
            else {
                for (int i = 0; i < -num; i++) {
                    dq.addFirst(dq.pollLast());
                }
            }
        }

        System.out.println(sb.toString());
    }
}
