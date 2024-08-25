import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int time = 0;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static int countIsland() {
        boolean[][] visited = new boolean[N][M];
        int count = 0;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    count++;
                    q.add(new int[]{i, j});
                    visited[i][j] = true;
                    while (!q.isEmpty()) {
                        int[] pos = q.poll();
                        int y = pos[0];
                        int x = pos[1];
                        for (int z = 0; z < 4; z++) {
                            int ny = y + dy[z];
                            int nx = x + dx[z];
                            if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
                                if (map[ny][nx] != 0 && !visited[ny][nx]) {
                                    q.add(new int[]{ny, nx});
                                    visited[ny][nx] = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    q.add(new int[]{i, j});
                }
            }
        }

        boolean flag = false;
        while (!q.isEmpty()) {
            int island = countIsland();
            if (island >= 2) {
                flag = true;
                break;
            }

            int[][] newMap = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    newMap[i][j] = map[i][j];
                }
            }
            int size = q.size();
            for (int z = 0; z < size; z++) {
                int[] pos = q.poll();
                int y = pos[0];
                int x = pos[1];
                int bing = map[y][x];
                int count = 0;
    
                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];
                    if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
                        if (map[ny][nx] == 0) {
                            count++;
                        }
                    }
                }
                if (bing - count <= 0) {
                    newMap[y][x] = 0;
                } else {
                    newMap[y][x] = bing - count;
                }
            }

            // update map with newMap
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    map[i][j] = newMap[i][j];
                    if (map[i][j] != 0) {
                        q.add(new int[]{i, j});
                    }
                }
            }
            time++;
        }
        System.out.println(flag ? time : 0);
    }
}