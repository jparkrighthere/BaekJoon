import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][5];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] students = new int[N];

        for (int student = 0; student < N; student++) {
            Set<Integer> set = new HashSet<>();
            for (int year = 0; year < 5; year++) {
                int currentClass = map[student][year];
                for (int friend = 0; friend < N; friend++) {
                    if (student != friend && map[friend][year] == currentClass) {
                        set.add(friend);
                    }
                }
            }
            students[student] = set.size();
        }

        int max = students[0];
        int idx = 1;

        for (int i = 1; i < N; i++) {
            if (max < students[i]) {
                max = students[i];
                idx = i + 1;
            }
        }

        System.out.println(idx);
    }
}
