import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int cnt = 0;
        int node = 0;
        if(m == 2){
            cnt = 1;
        }

        for(int i=1; i<n; i++){
            if(m > cnt) {
                sb.append("0").append(" ").append(i).append("\n");
                cnt++;
            }
            else{
                sb.append(node).append(" ").append(i).append("\n");
            }
            node = i;
        }

        System.out.println(sb);
    }
}