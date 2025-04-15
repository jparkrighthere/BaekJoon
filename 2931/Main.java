import java.util.*;
import java.io.*;

public class Main {
    static int R, C;
    static char[][] map;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static Map<Character, int[]> dir = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        dir.put('|', new int[]{0, 1});
        dir.put('-', new int[]{2, 3});
        dir.put('+', new int[]{0, 1, 2, 3});
        dir.put('1', new int[]{1, 3});
        dir.put('2', new int[]{0, 3});
        dir.put('3', new int[]{0, 2});
        dir.put('4', new int[]{1, 2});

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                if (map[y][x] != '.') continue;

                List<Integer> connectedDirs = new ArrayList<>();

                for (int d = 0; d < 4; d++) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    if (ny < 0 || ny >= R || nx < 0 || nx >= C) continue;

                    // .을 기준으로 상하좌우를 돌아보고 파이프를 pick
                    char pipe = map[ny][nx];
                    // pick한 파이프가 반대 방향에 있는 파이프랑 연결되어 있는지 확인
                    if (isConnected(pipe, oppositeDir(d))) {
                        connectedDirs.add(d);
                    }
                }

                if (connectedDirs.size() >= 2) {
                    System.out.println((y + 1) + " " + (x + 1) + " " + getPipeShape(connectedDirs));
                    return;
                }
            }
        }
    }

    static int oppositeDir(int d) {
        return (d % 2 == 0) ? d + 1 : d - 1;
    }

    static boolean isConnected(char pipe, int incomingDir) {
        if (!dir.containsKey(pipe)) return false;
        for (int d : dir.get(pipe)) {
            if (d == incomingDir) return true;
        }
        return false;
    }

    static char getPipeShape(List<Integer> dirs) {
        Collections.sort(dirs);
        if (dirs.size() == 4) return '+';
        if (dirs.contains(0) && dirs.contains(1)) return '|';
        if (dirs.contains(2) && dirs.contains(3)) return '-';
        if (dirs.contains(1) && dirs.contains(3)) return '1';
        if (dirs.contains(0) && dirs.contains(3)) return '2';
        if (dirs.contains(0) && dirs.contains(2)) return '3';
        if (dirs.contains(1) && dirs.contains(2)) return '4';
        return '?';
    }
}
