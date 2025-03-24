import java.util.*;
import java.io.*;

public class Main {
    static int N, totalLength;
    static int[] colors;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        totalLength = (int) Math.pow(N, 2);
        colors = new int[totalLength];

        for (int i = 0; i < totalLength; i++) {
            colors[i] = (((i - 1) % N) + 1);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= totalLength; i++) {
            sb.append(((i - 1) % N) + 1);
        }

        List<int[]> list = new ArrayList<>();
        int size = 1;
        for (int i = 0; i < totalLength; i++) {
            sb.substring(i, size);
        }
    }

}
