import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<int[]> flowers = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sMonth = Integer.parseInt(st.nextToken());
            int sDay = Integer.parseInt(st.nextToken());
            int eMonth = Integer.parseInt(st.nextToken());
            int eDay = Integer.parseInt(st.nextToken());

            int startDate = sMonth * 100 + sDay;
            int endDate = eMonth * 100 + eDay;

            flowers.add(new int[]{startDate, endDate});
        }

        // 시작일을 기준으로 오름차순으로 정렬
        flowers.sort((a,b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        int lastEnd = 301;
        int maxEnd = 0;
        int index = 0;
        int count = 0;

        while (lastEnd <= 1130) {
            boolean found = false;
            while (index < flowers.size()) {
                int startDate = flowers.get(index)[0];
                int endDate = flowers.get(index)[1];

                if (startDate > lastEnd) break;

                if (endDate > maxEnd) {
                    maxEnd = endDate;
                    found = true;
                }
                index++;
            }

            if (!found) {
                count = 0;
                break;
            }

            lastEnd = maxEnd;
            count++;
        }

        System.out.println(count);
    }
}
