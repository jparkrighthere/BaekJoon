import java.util.*;
import java.io.*;

public class Main {
    static int N, K, S;
    static class Pos implements Comparable<Pos> {
        int dist, weight;
        public Pos(int dist, int weight) {
            this.dist = dist;
            this.weight = weight;
        }
        @Override
        public int compareTo(Pos o) {
            return o.dist - this.dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        PriorityQueue<Pos> left = new PriorityQueue<>();
        PriorityQueue<Pos> right = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int dist = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            if (dist < S) left.add(new Pos(S - dist, weight));
            else right.add(new Pos(dist - S, weight));
        }
        int answer = 0;
        answer += busMove(left);
        answer += busMove(right);

        System.out.println(answer);
    }

    static int busMove(PriorityQueue<Pos> queue) {
        int move = 0;
        while (!queue.isEmpty()) {
            Pos pos = queue.poll();
            int dist = pos.dist;
            int weight = pos.weight;
            int count = 1;

            if (weight > K) {
                count = weight / K;
                weight = weight % K;
                move += count * dist * 2;
            }

            if (weight > 0) {
                while (weight < K && !queue.isEmpty()) {
                    Pos next = queue.poll();
                    if (weight + next.weight <= K) {
                        weight += next.weight;
                    }
                    else {
                        int leftover = weight + next.weight - K;
                        queue.add(new Pos(next.dist, leftover));
                        weight = K;
                    }
                }
                move += dist * 2;
            }
        }
        return move;
    }
}