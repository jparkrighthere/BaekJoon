import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long num = Integer.parseInt(st.nextToken());
        long sum = 0;

        for (long i = 1; i < num; i++) {
            sum += num * i + i;
        }
        System.out.println(sum);
    }
}
