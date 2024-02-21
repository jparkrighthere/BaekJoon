import java.io.*;
import java.util.*;

public class Main {
    static int N, M, r, c, count;
    static int d;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[][] map;
  
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        count = 1;
        solve(r, c, d);
        System.out.println(count);

        br.close(); 
    }


    static void solve(int y, int x, int dir) {
        map[y][x] = -1;
    
        for (int i = 0; i < 4; i++) {
            dir = (dir + 3) % 4;
    
            int ny = y + dy[dir];
            int nx = x + dx[dir];
    
            if (ny >= 0 && nx >= 0 && ny < N && nx < M && map[ny][nx] == 0) {
                count++;
                solve(ny, nx, dir);
                
                return;
            }   
        }
    
        int back = (dir + 2) % 4;
        int by = y + dy[back];
        int bx= x + dx[back];
    
        if (by >= 0 && by < N && bx >=0 && bx < M && map[by][bx] != 1) {
            solve(by, bx, dir);
        }
    }
}