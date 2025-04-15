import java.util.*;
import java.io.*;

public class Main {
    static boolean solved = false;
    static int[][] map = new int[9][9];
    static List<Integer> list = new ArrayList<>();
    static boolean[][] rowVisited = new boolean[9][9];
    static boolean[][] colVisited = new boolean[9][9];
    static boolean[][] boxVisited = new boolean[9][9];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            StringTokenizer st= new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) list.add(i * 10 + j);
                else {
                    rowVisited[i][map[i][j] - 1] = true;
                    colVisited[j][map[i][j] - 1] = true;
                    boxVisited[(i / 3) * 3 + (j / 3)][map[i][j] - 1] = true;
                }
            }
        }

        backtrack(0);
    }

    static void backtrack(int depth) {
        if (solved) return;

        if (depth == list.size()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }

            System.out.println(sb.toString());
            solved = true;
            return;
        }

        int y = list.get(depth) / 10;
        int x = list.get(depth) % 10;
        int box = (y / 3) * 3 + (x / 3);

        for (int val = 1; val <= 9; val++) {
            int idx = val - 1;
            if (!rowVisited[y][idx] && !colVisited[x][idx] && !boxVisited[box][idx]) {
                map[y][x] = val;
                rowVisited[y][idx] = colVisited[x][idx] = boxVisited[box][idx] = true;

                backtrack(depth + 1);

                map[y][x] = 0;
                rowVisited[y][idx] = colVisited[x][idx] = boxVisited[box][idx] = false;
            }
        }
    }
}
