import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dist = y - x;

            int val = (int)Math.sqrt(dist);
            if (val == Math.sqrt(dist)) {
                System.out.println(val * 2 - 1);
            }
            else if (dist <= val * val + val) {
                System.out.println(val * 2);
            }
            else System.out.println(val * 2 + 1);
        }
    }
}

