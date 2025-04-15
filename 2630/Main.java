import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] map;
    static int white, blue = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        partition(0, 0, N);
        System.out.println(white);
        System.out.println(blue);
    }

    static void partition(int row, int col, int size) {
        if (sameColor(row, col, size)) {
            if (map[row][col] == 0) white++;
            if (map[row][col] == 1) blue++;
            return;
        }
        int nextSize = size / 2;
        
        partition(row, col, nextSize);
        partition(row + nextSize, col, nextSize);
        partition(row, col + nextSize, nextSize);
        partition(row + nextSize, col + nextSize, nextSize);
    }

    static boolean sameColor(int row, int col, int size) {
        int color = map[row][col];

        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (color != map[i][j]) return false;
            }
        }
        return true;
    }
}