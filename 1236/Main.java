import java.util.*;
import java.io.*;

public class Main {
    static int N,M;
    static String[][] map;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new String[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = String.valueOf(line.charAt(j));
            }
        }
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < N; i++) {
            List<String> list1 = new ArrayList<>(Arrays.asList(map[i]));
            if (!list1.contains("X")) {
                count1++;
            }
        }

        for (int j = 0; j < M; j++) {
            List<String> list2 = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                list2.add(map[i][j]);
            }
            if (!list2.contains("X")) {
                count2++;
            }
        }

        System.out.println(Math.max(count1, count2));
    }
}
