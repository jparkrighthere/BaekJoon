import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static List<int[]> list;
    static int ans = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int durability = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list.add(new int[]{durability, weight});
        }
        backtrack(0, 0);
        System.out.println(ans);
    }

    static void backtrack(int idx, int count) {
        if (idx == N) {
            if (count > ans) {
                ans = count;
            }
            return;
        }
        // 손으로 든 계란이 이미 깨졌거나 모든 계란이 이미 다 깨져 있다면
        if (list.get(idx)[0] <= 0 || count == N - 1) {
            backtrack(idx + 1, count);
            return;
        }
        
        int temp = count;
        for (int i = 0; i < N; i++) {
            // 자기 자신이면
            if (i == idx) continue;
            // 이미 깨져있으면
            if (list.get(i)[0] <= 0) continue;
            hitEggs(idx, i);
            if (list.get(i)[0] <= 0) {
                count++;
            }
            if (list.get(idx)[0] <= 0) {
                count++;
            }
            backtrack(idx + 1, count);
            undo(idx, i);
            count = temp;
        }
    }

    static void hitEggs(int handIdx, int targetIdx) {
        list.get(handIdx)[0] = list.get(handIdx)[0] - list.get(targetIdx)[1];
        list.get(targetIdx)[0] = list.get(targetIdx)[0] - list.get(handIdx)[1];
    }

    static void undo(int handIdx, int targetIdx) {
        list.get(handIdx)[0] = list.get(handIdx)[0] + list.get(targetIdx)[1];
        list.get(targetIdx)[0] = list.get(targetIdx)[0] + list.get(handIdx)[1];
    }
}
