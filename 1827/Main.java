import java.util.*;
import java.io.*;

public class Main {
    static int N,K;
    static String num;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        num = br.readLine();
        char[] arr = num.toCharArray();

        Deque<Character> dq = new ArrayDeque<>();

        for (int i = 0; i < arr.length; i++) {
            while (K > 0 && !dq.isEmpty() && arr[i] > dq.getLast()) {
                dq.removeLast();
                K--;
            }
            dq.addLast(arr[i]);
        }
        StringBuilder sb = new StringBuilder();
        while (dq.size() > K) {
            sb.append(dq.removeFirst());
        }
        System.out.println(sb.toString());
    }
}
