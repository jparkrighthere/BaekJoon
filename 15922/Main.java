import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int left, right;
    static int length = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int left = Integer.parseInt(st.nextToken());
        int right = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if ((left <= a && a <= right) || (left <= b && b <= right)) {
                if (left > a) left = a;
                if (right < b) right = b;
            }
            else {
                int sum;
                if (left < 0 && right >= 0) sum = Math.abs(left) + right;
                else sum = right - left;

                length += sum;
                left = a;
                right = b;
            }
        }
        int sum;
        if (left < 0 && right >= 0) sum = Math.abs(left) + right;
        else sum = right - left;

        length += sum;

        System.out.println(length);
    }
}
