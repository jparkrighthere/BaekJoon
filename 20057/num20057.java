import java.io.*;
import java.util.*;

public class num20057 {
    static int N;
	static int map[][];
	static int res; // 밖으로 나간 모래 양
    static int[] dx = {-1, 0, 1, 0}; // 왼쪽, 아래, 오른쪽, 위
    static int[] dy = {0, 1, 0, -1}; // 왼쪽, 아래, 오른쪽, 위
    static int[][] moveX = {
        {1, 1, 0, 0, -2, 0, 0, -1, -1},
        {-1, 1, -2, 2, 0, -1, 1, -1, 1},
        {-1, -1, 0, 0, 2, 0, 0, 1, 1},
        {1, -1, 2, -2, 0, 1, -1, 1, -1}
    };
    static int[][] moveY = {
        {-1, 1, -2, 2, 0, -1, 1, -1, 1},
        {-1, -1, 0, 0, 2, 0, 0, 1, 1},
        {1, -1, 2, -2, 0, 1, -1, 1, -1},
        {1, 1, 0, 0, -2, 0, 0, -1, -1}
    };
    static int[] rate = {1, 1, 2, 2, 5, 7, 7, 10, 10};

    public static int solve(int cx, int cy) { // 토네이도 돌리는 함수
        int ret = 0;
        int dir = -1;
        boolean visited[][] = new boolean[N][N];
        for (boolean[] row : visited) {
            Arrays.fill(row, false);
        }

        while ((cx != 0 || cy != 0)) {
            int nd = (dir + 1) % 4;
            int ny = cy + dy[nd];
            int nx = cx + dx[nd];

            if (visited[ny][nx] == true) {
                nd = dir;
                ny = cy + dy[nd];
                nx = cx + dx[nd];
            }
            ret += move(nx, ny, nd);
            visited[cy][cx] = true;
            cy = ny;
            cx = nx;
            dir = nd;
        }

        return ret;
    }

    public static int move(int cx, int cy, int dir) { // 모래 이동 함수
        int ret = 0;
        int sand =  map[cy][cx];
        int sum = 0;

        for (int i = 0; i < 9; i++) {
            int ny = cy + moveY[dir][i];
            int nx = cx + moveX[dir][i];
            int moveSand = (int)(sand * rate[i] / 100);
            sum += moveSand;

            if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
                ret += moveSand;
                continue;
            } 
            else {
                map[ny][nx] += moveSand;
            }
        }
        // 여기가 알파라고 마지막에 하는 부분인데 사실 이해가 완벽하게 잘 안됌
        int ny = cy + dy[dir];
        int nx = cx + dx[dir];
        int a = sand - sum;
        if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
            ret += a;
        }
        else {
            map[ny][nx] += a;
        }
        map[cy][cx] = 0;

        return ret;
    }   

    public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
            }
		}

        res = solve(N / 2, N / 2);
        bw.write(String.valueOf(res));
        bw.flush();
	}
}