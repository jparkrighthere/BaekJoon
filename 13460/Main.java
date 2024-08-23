import java.util.*;
import java.io.*;

public class Main {
    static class Marble {
        int ry, rx, by, bx, cnt;
        
        Marble(int ry, int rx, int by, int bx, int cnt) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.cnt = cnt;
        }
    }

    static int N, M;
    static int holeY, holeX;
    static char[][] map;
    static boolean[][][][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static Marble red, blue;
    static int round = -1;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);

                if(map[i][j] == 'O') {
					holeY = i;
					holeX = j;
                }
                else if(map[i][j] == 'B') {
					blue = new Marble(0, 0, i, j, 0);
				} 
                else if(map[i][j] == 'R') {
					red = new Marble(i, j, 0, 0, 0);
				}
            }
        }
        bfs();
        System.out.println(round);
    }

    static void bfs() {
        Queue<Marble> q = new LinkedList<>();
        q.add(new Marble(red.ry, red.rx, blue.by, blue.bx, 1));
        visited[red.ry][red.rx][blue.by][blue.bx] = true;

        while (!q.isEmpty()) {
            Marble marble = q.poll();
            int cry = marble.ry;
            int crx = marble.rx;
            int cby = marble.by;
            int cbx = marble.bx;

            if(marble.cnt > 10) { 
                return;
			}

            for (int i = 0; i < 4; i++) {
                int nry = cry;
                int nby = cby;
                int nrx = crx;
                int nbx = cbx;
                
                boolean isRed = false; 
                boolean isBlue = false;

                while (map[nry + dy[i]][nrx + dx[i]] != '#') {
                    nry = nry + dy[i];
                    nrx = nrx + dx[i];

                    if (nry == holeY && nrx == holeX) {
                        isRed = true;
                        break;
                    }
                }

                while (map[nby + dy[i]][nbx + dx[i]] != '#') {
                    nby = nby + dy[i];
                    nbx = nbx + dx[i];

                    if (nby == holeY && nbx == holeX) {
                        isBlue = true;
                        break;
                    }
                }

                if(isBlue) {
                    continue;
				}

                if(isRed && !isBlue) {
					round = marble.cnt;
                    return;
				}

                if(nrx == nbx && nry == nby) {
                    if (i == 0) { // 상
                        // 레드가 블루보다 아래에 있었을 때
                        if (cry > cby) {
                            nry += 1;
                        } else {
                            nby += 1;
                        }
                    }
                    else if (i == 1) { // 하
                        // 레드가 블루보다 아래에 있었을 때
                        if (cry > cby) {
                            nby -= 1;
                        } else {
                            nry -= 1;
                        }
                    }
                    else if (i == 2) { // 좌
                        // 레드가 블루보다 오른쪽에 있었을 때
                        if (crx > cbx) {
                            nrx += 1;
                        } else {
                            nbx += 1;
                        }
                    }
                    else if (i == 3) { // 우
                        // 레드가 블루보다 오른쪽에 있었을 때
                        if (crx > cbx) {
                            nbx -= 1;
                        } else {
                            nrx -= 1;
                        }
                    }
                }

                if (!visited[nry][nrx][nby][nbx]) {
                    visited[nry][nrx][nby][nbx] = true;
                    q.add(new Marble(nry, nrx, nby, nbx, marble.cnt + 1));
                }
            }
        }
    }
}
