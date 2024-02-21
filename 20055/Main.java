import java.io.*;
import java.util.*;

public class Main {
    static class Belt {
        int durability;
        boolean hasRobot;

        public Belt(int durability) {
            this.durability = durability;
            this.hasRobot = false;
        }
    }

    static int k;
    static int n;
    static LinkedList<Belt> conveyor;
    static int step = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int size = 2 * n;
        conveyor = new LinkedList<>();
		
        //내구도 입력받기
        String[] durability = bf.readLine().split(" ");
        for(int i = 0; i < size; i++){
            conveyor.add(i, new Belt(Integer.parseInt(durability[i])));              
        }

		//내구도 0인 벨트가 k개 이상일 때 단계 수행
        while(k > 0) {
            MoveConveyor();
            MoveRobot();
        }

        System.out.println(step);
    }

    static void MoveConveyor() {
        step++;
        conveyor.add(0, conveyor.removeLast());
        if (conveyor.get(n-1).hasRobot) {
            conveyor.get(n-1).hasRobot = false;
        }
    }

    static void MoveRobot() {

        for (int i = n - 1; i > 0; i--) {
            if (!conveyor.get(i).hasRobot) {
                continue;
            }

            if (conveyor.get(i+1).hasRobot || (conveyor.get(i+1).durability <= 0)) {
                continue;
            }

            conveyor.get(i).hasRobot = false;
            conveyor.get(i+1).hasRobot = true;
            conveyor.get(i+1).durability--;
            if (conveyor.get(i+1).durability <= 0) {
                k--;
            }

            if (i + 1 == n - 1 && conveyor.get(i+1).hasRobot) {
                conveyor.get(i+1).hasRobot = false;
            }
        }

        if (conveyor.get(0).durability > 0) {
            conveyor.get(0).hasRobot = true;
            conveyor.get(0).durability--;
            if (conveyor.get(0).durability <= 0) {
                k--;
            }
        }
    }
}
