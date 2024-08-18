import java.util.*;
import java.io.*;

public class Main {
    static int N, K, L;
    static int[][] map;
    static int snakeDir = 4; // 싱 = 1, 하 = 2, 좌 = 3, 우 = 4
    static Queue<int[]> queue;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        queue = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            map[y][x] = 2;
        }

        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int turn = 0;
            String dir = st.nextToken();
            if (dir.equals("L")) {
                turn = -1;
            }
            else if (dir.equals("D")) {
                turn = 1;
            }
            queue.add(new int[]{time, turn});
        }

        int t = 0;
        int[] pos = new int[] {0, 0};
        map[0][0] = 1;
        Queue<int[]> body = new LinkedList<>();
        body.add(pos);
        boolean check = true;

        while (check) {
            //시간 증가
            t++;
            
            // 현재 위치
            int y = pos[0];
            int x = pos[1];

            // 머리를 다음칸에 위치
            if (snakeDir == 1) y-= 1;
            else if (snakeDir == 2) y+= 1;
            else if (snakeDir == 3) x-= 1;
            else if (snakeDir == 4) x+= 1;

            // 벽을 부딪히면
            if (y >= N || y < 0 || x >= N || x < 0) break;
            // 자기 자신과 부딪히면
            if (map[y][x] == 1) break;

            // 사과가 있으면
            if (map[y][x] == 2) {
                map[y][x] = 1;
                body.add(new int[]{y,x});
            }
            //없으면
            else if (map[y][x] == 0) {
                map[y][x] = 1;
                if (!body.isEmpty()) {
                    int[] tail = body.poll();
                    map[tail[0]][tail[1]] = 0;
                }
                body.add(new int[]{y, x});
            }

            // X초가 끝난 뒤에
            if (!queue.isEmpty()) {
                int[] arr = queue.peek();
                int time = arr[0];
                int turn = arr[1];
                if (t == time) {
                    if (turn == -1) { // 좌회전
                        if (snakeDir == 1) snakeDir = 3;
                        else if (snakeDir == 2) snakeDir = 4;
                        else if (snakeDir == 3) snakeDir = 2;
                        else if (snakeDir == 4) snakeDir = 1;
                    }
                    else if (turn == 1) { // 우회전
                        if (snakeDir == 1) snakeDir = 4;
                        else if (snakeDir == 2) snakeDir = 3;
                        else if (snakeDir == 3) snakeDir = 1;
                        else if (snakeDir == 4) snakeDir = 2;
                    }
                    queue.poll();
                }
            }
            pos = new int[]{y, x};
        }
        System.out.println(t);
    }
}
