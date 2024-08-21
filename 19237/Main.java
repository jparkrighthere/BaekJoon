import java.util.*;
import java.io.*;

public class Main {
    static class Shark {
        int id, y, x, dir;
        int[][] priority;

        Shark(int id, int y, int x) {
            this.id = id;
            this.y = y;
            this.x = x;
            this.priority = new int[4][4];
        }
    }

    static int N, M, K, time;
    static int[][][] map;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static Shark[] sharks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        sharks = new Shark[M];
        map = new int[N][N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j][0] = Integer.parseInt(st.nextToken());
                if (map[i][j][0] != 0) {
                    sharks[map[i][j][0] - 1] = new Shark(map[i][j][0], i, j);
                    map[i][j][1] = map[i][j][0];
                    map[i][j][2] = K;
                }
            }
        }
    
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            sharks[i].dir = Integer.parseInt(st.nextToken()) - 1;
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    sharks[i].priority[j][k] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }
        time = 0;
        solve();
        System.out.println(time <= 1000 ? time : -1);
    }

    static void solve() {
        int kill = 0;
        while (time <= 1000) {
            time++;
            int[][][] newMap = new int[N][N][3];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    newMap[i][j][0] = map[i][j][0];
                    newMap[i][j][2] = map[i][j][2];
                    if (newMap[i][j][2] > 0) {
                        newMap[i][j][2]--;
                    }
                    if (newMap[i][j][2] > 0) {
                        newMap[i][j][1] = map[i][j][1];
                    }
                }
            }

            for (int i = 0; i < M; i++) {
                Shark shark = sharks[i];
                int cy = shark.y;
                int cx = shark.x;
                int cd = shark.dir;
                if (cy == -1) {
                    continue;
                }
                boolean check = false;
                for (int j = 0; j < 4; j++) {
                    int nd = shark.priority[cd][j];
                    int ny = cy + dy[nd];
                    int nx = cx + dx[nd];
                    if (ny < 0 || ny >= N || nx < 0 || nx >= N || map[ny][nx][2] != 0) {
                        continue;
                    }

                    check = true;
                    newMap[cy][cx][0] = 0;
                    if (newMap[ny][nx][0] == 0) {
                        newMap[ny][nx][0] = shark.id;
                        newMap[ny][nx][1] = shark.id;
                        newMap[ny][nx][2] = K;

                        shark.y = ny;
                        shark.x = nx;
                        shark.dir = nd;
                    } 
                    else {
                        kill++;
                        shark.y = -1;
                    }
                    break;
                }
                if (!check) {
                    // 이전에 움직이지 못했다면 내 냄새가 있는 방향으로 돌아가기
                    for (int j = 0; j < 4; j++) {
                        int nd = shark.priority[cd][j];
                        int ny = cy + dy[nd];
                        int nx = cx + dx[nd];
                        if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
                            continue;
                        }

                        if (map[ny][nx][1] != shark.id && map[ny][nx][2] != 0) {
                            continue;
                        }

                        newMap[cy][cx][0] = 0;
                        if (newMap[ny][nx][0] == 0) {
                            newMap[ny][nx][0] = shark.id;
                            newMap[ny][nx][1] = shark.id;
                            newMap[ny][nx][2] = K;
    
                            shark.y = ny;
                            shark.x = nx;
                            shark.dir = nd;
                        } 
                        else {
                            kill++;
                            shark.y = -1;
                        }
                        break;
                    }
                }
            }
            if (kill == M - 1) {
                break;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j][0] = newMap[i][j][0];
                    map[i][j][1] = newMap[i][j][1];
                    map[i][j][2] = newMap[i][j][2];
                }
            }

        }
    }
}
