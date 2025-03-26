    import java.util.*;
    import java.io.*;

    public class Main {
        static int P, M;
        static class Player implements Comparable<Player> {
            int level;
            String id;
            public Player(int level, String id) {
                this.level = level;
                this.id = id;
            }

            @Override
            public int compareTo(Player o) {
                return this.id.compareTo(o.id);
            }
        }

        static List<List<Player>> rooms = new ArrayList<>();
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            P = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            for (int i = 0; i < P; i++) {
                st = new StringTokenizer(br.readLine());
                int level = Integer.parseInt(st.nextToken());
                String id = st.nextToken();

                boolean assigned = false;
                for (List<Player> room : rooms) {
                    if (room.size() < M && Math.abs(room.get(0).level - level) <= 10) {
                        room.add(new Player(level, id));
                        assigned = true;
                        break;
                    }
                }

                if (!assigned) {
                    List<Player> room = new ArrayList<>();
                    room.add(new Player(level, id));
                    rooms.add(room);
                }
            }

            StringBuilder sb = new StringBuilder();
            for (List<Player> room : rooms) {
                Collections.sort(room);
                sb.append(room.size() == M ? "Started!" : "Waiting!").append("\n");
                for (Player player : room) {
                    sb.append(player.level + " " + player.id).append("\n");
                }
            }

            System.out.println(sb.toString());
        }
    }

