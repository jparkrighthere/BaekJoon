import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] answer = new int[N];
        Stack<Integer> stack = new Stack<>();
        Arrays.fill(answer, -1);

        for (int i = 0; i < N; i++) {
            while (!stack.empty() && arr[stack.peek()] < arr[i]){ // 비어있지 않고 작은 idx의 숫자보다 큰 idx의 숫자가 클 때
                answer[stack.pop()] = arr[i];
            }
            stack.push(i);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N - 1; i++) {
            sb.append(answer[i] + " ");
        }
        sb.append(answer[N-1]);
        System.out.println(sb);
    }
}
