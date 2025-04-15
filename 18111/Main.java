import java.util.*;
import java.io.*;

public class Main {
    static int N, M, B;
    static Map<Integer, Integer> map = new HashMap<>();
    static int minTime = Integer.MAX_VALUE;
    static int resultHeight = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        int minH = 256;
        int maxH = 0;

        for (int i = 0; i < N ; i++) {  
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int val = Integer.parseInt(st.nextToken());
                map.put(val, map.getOrDefault(val, 0) + 1);
                minH = Math.min(minH, val);
                maxH = Math.max(maxH, val);
            }
        }

        for (int target = minH; target <= maxH; target++) {
            int cost = adjust(target);
            if (cost < minTime || (cost == minTime && resultHeight < target)) {
                minTime = cost;
                resultHeight = target;
            }
        }

        System.out.println(minTime + " " + resultHeight);
    }

    static int adjust(int target) {
        int time = 0;
        int inventory = B;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int height = entry.getKey();
            int count = entry.getValue();
            int diff = height - target;

            if (diff > 0) {
                // 블록을 캐야 함
                time += diff * 2 * count;
                inventory += diff * count;
            } else if (diff < 0) {
                // 블록을 쌓아야 함
                diff = -diff;
                time += diff * count;
                inventory -= diff * count;
            }
        }

        if (inventory < 0) return Integer.MAX_VALUE;

        return time;
    }
}
