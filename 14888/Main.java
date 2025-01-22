import java.util.*;
import java.io.*;

public class Main {
    static class Meeting implements Comparable<Meeting> {
        int start, end;
        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting o) {
            if (this.end == o.end) return Integer.compare(this.start, o.start);

            return Integer.compare(this.end, o.end);
        }
    }

    static int N;
    static List<Meeting> meetings = new ArrayList<>();
    static boolean[] visited = new boolean[N];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            meetings.add(new Meeting(start, end));
        }

        Collections.sort(meetings);

        int lastEnd = 0;
        int count = 0;
        for (Meeting meeting : meetings) {
            if (meeting.start >= lastEnd) {
                count++;
                lastEnd = meeting.end;
            }
        }

        System.out.println(count);
    }
}
