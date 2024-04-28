import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String x = st.nextToken();
        String y = st.nextToken();

        StringBuilder sb = new StringBuilder(x);
        x = sb.reverse().toString();
        sb.delete(0, sb.length());
        sb.append(y);
        y = sb.reverse().toString();

        String val = String.valueOf(Integer.parseInt(x) + Integer.parseInt(y));
        sb.delete(0, sb.length());
        sb.append(val);
        val = String.valueOf(Integer.parseInt(sb.reverse().toString()));
        System.out.println(val);
    }
}
