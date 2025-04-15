import java.util.*;
import java.io.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[N + 1];

        PriorityQueue<int[]> tasks = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int noodles = Integer.parseInt(st.nextToken());
            tasks.add(new int[]{deadline, noodles});
        }

        PriorityQueue<Integer> select = new PriorityQueue<>();
        
        while(!tasks.isEmpty()) {
            int[] task = tasks.poll();
            int deadline = task[0];
            int noodles = task[1];

            if (select.size() < deadline) {
                select.add(noodles);
            }
            else {
                if (select.peek() < noodles) {
                    select.poll();
                    select.add(noodles);
                }
            }
        }

        long sum = 0;
        while (!select.isEmpty()) {
            sum += (long)select.poll();
        }

        System.out.println(sum);
    }
}
