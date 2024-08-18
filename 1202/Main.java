import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static class Gem implements Comparable<Gem> {
        int m;
        int v;
        public Gem(int m, int v) {
            this.m = m;
            this.v = v;
        }
        @Override
        public int compareTo(Gem g) {
            if (this.m == g.m) { // 무게가 같을 때, 가치 내림차순
                return g.v - this.v;
            }
            return this.m - g.m; // 무게 오름차순
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Gem[] gems = new Gem[N];
        int[] bags = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            gems[i] = new Gem(M,V);
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            bags[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(bags);
        Arrays.sort(gems);
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        long ans = 0;

        for (int i = 0, j = 0; i < K; i++) {
            while (j < N) {
                // 보석의 무게가 작거나 같을때끼지 담기
                if (bags[i] < gems[j].m) {
                    break;
                }
                pq.add(gems[j++].v);
            }
            if (!pq.isEmpty()) {
                ans += pq.poll();
            }
        }

        System.out.println(ans);
    }
    
}
