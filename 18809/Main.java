import java.util.*;
import java.io.*;

public class Main {
    static int N, M, G, R;
    static int[][] map;
    static List<int[]> possible;
    static int[] selected;
    static int[] selectedGreen;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        possible = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) {
                    possible.add(new int[]{i, j});
                }
            }
        }

        selected = new int[G + R];
        selectedGreen = new int[G];
        selectLand(0, 0);

        System.out.println(max);
    }

    // 먼저 G + R개의 땅 고르기
    static void selectLand(int idx, int count) {
        if (count == G + R) {
            selectGreen(0,0);
            return;
        }

        for (int i = idx; i < possible.size(); i++) {
            selected[count] = i;
            selectLand(i + 1, count + 1);
        }
    }

    // 그 중 G개의 땅 고르기
    static void selectGreen(int idx, int count) {
        if (count == G) {
            bfs();
            return;
        }

        for (int i = idx; i < G + R; i++) {
            selectedGreen[count] = i;
            selectGreen(i + 1, count + 1);
        }
    }

    // 퍼뜨리기
    static void bfs() {
        int[][] greenT = new int[N][M]; // 초록색 염료가 각 셀에 도달하는 시간을 추적
        int[][] redT = new int[N][M];   // 빨간색 염료가 각 셀에 도달하는 시간을 추적
        Queue<int[]> q = new LinkedList<>();
    
        // 선택된 셀로 큐를 초기화
        for (int i = 0; i < G + R; i++) {
            int[] soil = possible.get(selected[i]);
            boolean isGreen = false;
            for (int j = 0; j < G; j++) {
                if (selectedGreen[j] == i) {
                    isGreen = true;
                    break;
                }
            }
    
            if (isGreen) {
                greenT[soil[0]][soil[1]] = 1;
                q.add(new int[]{soil[0], soil[1], 1, 0}); // (y, x, 시간, 색깔) 여기서 색깔 0 = 초록색
            } else {
                redT[soil[0]][soil[1]] = 1;
                q.add(new int[]{soil[0], soil[1], 1, 1}); // (y, x, 시간, 색깔) 여기서 색깔 1 = 빨간색
            }
        }
    
        int count = 0;
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int cy = pos[0]; // 현재 y
            int cx = pos[1]; // 현재 x
            int ct = pos[2]; // 현재 시간
            int color = pos[3]; // 현재 색깔 (0 = 초록색, 1 = 빨간색)
    
            // 현재 위치의 초록색과 빨간색 배양액 도착 시간이 같으면 처리 건너뛰기
            if (greenT[cy][cx] == redT[cy][cx]) {
                continue;
            }
    
            for (int i = 0; i < 4; i++) {
                int ny = cy + dy[i];
                int nx = cx + dx[i];
    
                // 새 좌표가 유효한지 확인하고, 해당 위치에 배양액을 뿌릴 수 있는지 체크
                if (ny >= 0 && ny < N && nx >= 0 && nx < M && map[ny][nx] != 0) {
                    if (color == 0) { // 초록색
                        if (greenT[ny][nx] > 0) { 
                            continue; // 이미 초록색 배양액 있음
                        }
                        if (redT[ny][nx] > 0 && redT[ny][nx] <= ct) {
                            continue; // 빨간색 배양액이 같은 시간 또는 더 빠르게 도착
                        }
    
                        greenT[ny][nx] = ct + 1;
    
                        if (redT[ny][nx] == ct + 1) {
                            count++;
                            continue;
                        }
    
                        q.add(new int[]{ny, nx, ct + 1, color});
                    } else if (color == 1) { // 빨간색
                        if (redT[ny][nx] > 0) {
                            continue; // 이미 빨간색 배양액 있음
                        }
                        if (greenT[ny][nx] > 0 && greenT[ny][nx] <= ct) {
                            continue; // 초록색 베양액이 같은 시간 또는 더 빠르게 도착
                        }
    
                        redT[ny][nx] = ct + 1;
    
                        if (greenT[ny][nx] == ct + 1) {
                            count++;
                            continue;
                        }
    
                        q.add(new int[]{ny, nx, ct + 1, color});
                    }
                }
            }
        }
        max = Math.max(max, count);
    }
}
