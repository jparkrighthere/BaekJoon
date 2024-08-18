import java.util.*;
import java.io.*;

public class Main {

    static class Pair implements Comparable<Pair> {
        int num;
        int count;

        public Pair(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.count == o.count) {
                return this.num - o.num;
            }
            return this.count - o.count; 
        }
    }

    static int R, C, K;
    static int[][] A;
    static int xLength = 3, yLength = 3;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[101][101];


        for (int i = 1 ; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = -1;
        for (int time = 0; time <= 100; time++) {
            if (A[R][C] == K) {
                ans = time;
                break;
            }
            else {
                if (yLength >= xLength) {
                    for (int i = 1; i <= yLength; i++) {
                        rCalc(i);
                    }
                }
                else if (yLength < xLength) {
                    for (int i = 1; i <= xLength; i++) {
                        cCalc(i);
                    }
                }
            }
        }

        System.out.println(ans);
    }

    static void rCalc(int idx) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        Map<Integer, Integer> map =new HashMap<>();

        for (int i = 1; i <= xLength; i++) {
            if (A[idx][i] == 0) continue;
            map.put(A[idx][i], map.getOrDefault(A[idx][i], 0) + 1);
        }

        map.forEach((k, v) -> pq.add(new Pair(k, v)));

        int i = 1;
        while (!pq.isEmpty() && i <= 99) {
            Pair pair = pq.poll();
            A[idx][i++] = pair.num;
            A[idx][i++] = pair.count;
        }

        xLength = Math.max(xLength, i);

        while (i <= 99) {
            A[idx][i++] = 0; 
            A[idx][i++] = 0; 
        }
    }

    static void cCalc(int idx) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        Map<Integer, Integer> map =new HashMap<>();

        for (int i = 1; i <= yLength; i++) {
            if (A[i][idx] == 0) continue;
            map.put(A[i][idx], map.getOrDefault(A[i][idx], 0) + 1);
        }

        map.forEach((k, v) -> pq.add(new Pair(k, v)));

        int i = 1;
        while (!pq.isEmpty() && i <= 99) {
            Pair pair = pq.poll();
            A[i++][idx] = pair.num;
            A[i++][idx] = pair.count;
        }

        yLength = Math.max(yLength, i);

        while (i <= 99) {
            A[i++][idx] = 0;
            A[i++][idx] = 0;
        }
    }
}
