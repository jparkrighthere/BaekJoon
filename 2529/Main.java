import java.util.*;
import java.io.*;

public class Main {
    static int K;
    static char[] arr;
    static List<String> list;
    static boolean[] visited;

    static void dfs(int depth, StringBuilder sb) {
        if (depth == K + 1) {
            String num = sb.toString();
            list.add(num);
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (!visited[i] && (depth == 0 || check(arr[depth - 1], Character.getNumericValue(sb.charAt(depth - 1)), i))) {
                visited[i] = true;
                sb.append(i);
                dfs(depth + 1, sb);
                sb.deleteCharAt(sb.length() - 1);
                visited[i] = false;
            }
        }
    }

    static boolean check(char ch, int a, int b) {
        return ch == '<' ? a < b : a > b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        arr = new char[K];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        list = new ArrayList<>();
        visited = new boolean[10];
        dfs(0, new StringBuilder());
        Collections.sort(list);
        System.out.println(list.get(list.size() - 1));
        System.out.println(list.get(0));
    }
}
