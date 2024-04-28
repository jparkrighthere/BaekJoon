import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num1 = Integer.parseInt(st.nextToken());
        int num2 = Integer.parseInt(st.nextToken());
        List<Integer> list = new ArrayList<>();

        outerloop:
        for (int i = 1; i <= num2; i++) {
            for (int j = 1; j <= i; j++) {
                list.add(i);
                if (list.size() == num2) {
                    break outerloop;
                }
            }
        }

        int sum = 0;
        for (int i = num1 - 1; i <= num2 - 1; i++) {
            sum += list.get(i);
        }
        System.out.println(sum);
    }
}
