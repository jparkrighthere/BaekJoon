import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] startTimes;
    static int[] endTimes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        startTimes = new int[N];
        endTimes = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            startTimes[i] = Integer.parseInt(st.nextToken());
            endTimes[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        int minRooms = 0;
        int ongoingMeetings = 0;
        int startPointer = 0;
        int endPointer = 0;

        while (startPointer < N) {
            if (startTimes[startPointer] < endTimes[endPointer]) {
                // 새로운 회의 시작 -> 회의실 하나 더 필요
                ongoingMeetings++;
                startPointer++;
            } else {
                // 회의 종료 -> 회의실 하나 줄어듦
                ongoingMeetings--;
                endPointer++;
            }

            // 회의실의 최대 수 업데이트
            minRooms = Math.max(minRooms, ongoingMeetings);
        }

        System.out.println(minRooms);
    }
}
