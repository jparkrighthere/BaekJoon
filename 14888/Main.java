import java.util.*;
import java.io.*;

public class Main {
    static int[] oper; // +, -, *, /
    static int[] num;
    static int count;
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    static void dfs(int val, int idx) {

        if (idx == count) {
            max = Math.max(max, val);
            min = Math.min(min, val);
            return;
        }

        for (int i = 0; i < 4; ++i) {
            if (oper[i] > 0) {
                oper[i]--;

                if (i == 0) dfs(val + num[idx], idx + 1);

                if (i == 1) dfs(val - num[idx], idx + 1);

                if (i == 2) dfs(val * num[idx], idx + 1);
                
                if (i == 3) dfs(val / num[idx], idx + 1);

                oper[i]++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        oper = new int[4];
        count = Integer.parseInt(st.nextToken());
        num = new int[count];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < count; ++i) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; ++i) {
            oper[i] = Integer.parseInt(st.nextToken());
        }

        dfs(num[0], 1);
        System.out.println(max);
        System.out.println(min);
    }
}
