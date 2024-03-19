import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] map;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[] loc;
    static int size, time;
    static int[][] dist;
    static int minY, minX, minDist;
    static int eat = 0;

    static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(loc);

        while(!q.isEmpty()) {
            int[] xy = q.poll();
            int cy = xy[0];
            int cx = xy[1];

            for (int i = 0; i < 4; i++) {
                int ny = cy + dy[i];
                int nx = cx + dx[i];

                if (isValid(ny, nx) && canMove(ny, nx) && dist[ny][nx] == 0) {
                    dist[ny][nx] = dist[cy][cx] + 1;

                    if (isEdible(ny, nx)) {
                        if (minDist > dist[ny][nx]) {
                            minDist = dist[ny][nx];
                            minY = ny;
                            minX = nx;
                        }
                        else if (minDist == dist[ny][nx]) {
                            if (minY == ny) {
                                if (minX > nx) {
                                    minY = ny;
                                    minX = nx;
                                }
                            }
                            else if (minY > ny){
                                minY = ny;
                                minX = nx;
                            }
                        }   
                    }
                    q.add(new int[] {ny, nx});
                }
            }
        }
    }

    static boolean isValid(int y, int x) { // in map size?
        return (x >= 0 && x < N && y >= 0 && y < N);
    }

    static boolean isEdible(int y, int x) { // can eat the fish?
        return (map[y][x] != 0 && map[y][x] < size);
    }

    static boolean canMove(int y, int x) { // can move to the loc?
        return map[y][x] <= size;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        loc = new int[2];
        map = new int[N][N];
        for (int i = 0; i< N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    loc[0] = i;
                    loc[1] = j;
                    map[i][j] = 0;
                }
            }
        }
        size = 2;
        time = 0;

        while (true) {
            dist = new int[N][N];
            minY = Integer.MAX_VALUE;
            minX = Integer.MAX_VALUE;
            minDist = Integer.MAX_VALUE;

            bfs();

            if (minY < Integer.MAX_VALUE && minX < Integer.MAX_VALUE) {
                eat++;
                map[minY][minX] = 0;
                loc[0] = minY;
                loc[1] = minX;
                time += dist[minY][minX];
                if (size == eat) {
                    size++;
                    eat = 0;
                }
            }
            else {
                break;
            }
        }

        System.out.println(time);
    }
}
