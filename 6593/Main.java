import java.util.*;
import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[] dy= {-1,1,0,0,0,0};
	static int[] dx = {0,0,-1,1,0,0};
	static int[] dz = {0,0,0,0,1,-1};
    static int L, R, C;
    static char[][][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            
            if (L == 0 && R == 0 && C == 0) {
                System.out.println(sb.toString());
                return;
            }

            map = new char[L][R][C];
            int sy = 0, sx = 0, sz = 0;

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String line = br.readLine();
                    for (int k = 0; k < C; k++) {
                        map[i][j][k] = line.charAt(k);
                        if (map[i][j][k] == 'S') {
                            sz = i;
                            sy = j;
                            sx = k;
                            map[i][j][k] = '.';
                        }
                    }
                }
                br.readLine();
            }
            bfs(sz, sy, sx);
        }
    }

    static void bfs(int sz, int sy, int sx) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[L][R][C];
        q.add(new int[]{sz, sy, sx, 0});
        visited[sz][sy][sx] = true;

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int cz = pos[0];
            int cy = pos[1];
            int cx = pos[2];
            int cnt = pos[3];

            if (map[cz][cy][cx] == 'E') {
                sb.append("Escaped in ").append(cnt).append(" minute(s).").append("\n");
                return;
            }

            for (int i = 0; i < 6; i++) {
                int nz = cz + dz[i];
                int ny = cy + dy[i];
                int nx = cx + dx[i];

                if (nz >= 0 && nz < L && ny >= 0 && ny < R && nx >= 0 && nx < C && (map[nz][ny][nx] == '.' || map[nz][ny][nx] == 'E') && !visited[nz][ny][nx]) {
                    q.add(new int[]{nz, ny, nx, cnt + 1});
                    visited[nz][ny][nx] = true;
                }
            }
        }
        sb.append("Trapped!").append("\n");
    }
}
