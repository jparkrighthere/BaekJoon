import java.util.*;
import java.io.*;


public class Main {
    static int N, R, C;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        recursion(0, 0, (int)Math.pow(2, N));
    }

    static void recursion(int r, int c, int size) {
        if (size == 1) {
            System.out.println(answer);
            return;
        }

        int newSize = size / 2;

        if (R < newSize + r && C < newSize + c) {
            recursion(r, c, newSize);
        }

        if (R < newSize + r && newSize + c <= C) {
            answer += (size * size) / 4;
            recursion(r, c + newSize, newSize);
        }

        if (newSize + r <= R && C < newSize + c) {
            answer += (size * size) / 4 * 2;
            recursion(r + newSize, c, newSize);
        }

        if (newSize + r <= R && newSize + c <= C) {
            answer += (size * size) / 4 * 3;
            recursion(r + newSize, c + newSize, newSize);
        }
    }
}