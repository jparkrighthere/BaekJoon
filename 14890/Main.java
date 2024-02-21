import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int L;
    static int[][] map;
    static int ret = 0;

    static int[][] rotate(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;
        int[][] newArr = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                newArr[j][row-1-i] = arr[i][j];
            }
        }

        return newArr;
    }

    static void countWay() {
        int i, j;
        for (i = 0; i < map.length; i++) {
            int count = 1;
            for (j = 0; j < map[0].length - 1; j++) {
                if (map[i][j] == map[i][j+1]) {
                    count++;
                }
                else if (map[i][j] + 1 == map[i][j+1] && count >= L) {
                    count = 1;
                }
                else if (map[i][j] == map[i][j+1] + 1 && count >= 0) {
                    count = (1 - L);
                }
                else {
                    break;
                }
            }
            if (j == N - 1 && count >= 0) {
                ret++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[2*N][N];

        int[][] temp = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < N; j++) {
                temp[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = temp[i][j];
            }
        }

        int[][] rotArr = rotate(temp);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[N+i][j] = rotArr[i][j];
            }
        }
        countWay();
        System.out.println(ret);
    }
}

// for (int i = 0; i < map.length; i++) {
//     for (int j = 0; j < map[0].length; j++) {
//         System.out.print(map[i][j]);
//     }
//     System.out.println();
// }
