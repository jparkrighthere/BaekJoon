import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int limit = 10000;
        boolean[] check = new boolean[limit + 1];
        for (int i = 1; i <= limit; i++) {
            int n = i;
            int sum = n;
            while (n > 0) {
                sum += n % 10;
                n /= 10;
            }
            if (sum <= limit) check[sum] = true;
        }

        for (int i = 1; i <= limit; i++) {
            if (!check[i]) {
                System.out.println(i);
            }
        }
    }
}