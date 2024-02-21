import java.util.*;
import java.io.*;

public class Main {
    static int[][] gear;
    static int score = 0;
    static int[] cmd;

    static void checkDir(int index) {
        for (int i = index - 1; i >= 0; i--) {
            if (gear[i][2] != gear[i+1][6]) {
                cmd[i] = cmd[i+1] * -1;
            }
            else {
                break;
            }
        }

        for (int i = index + 1; i < 4; i++) {
            if (gear[i][6] != gear[i-1][2]) {
                cmd[i] = cmd[i-1] * -1;
            }
            else {
                break;
            }
        }
    }

    static void rotateGear() {
        int temp = 0;

        for (int i = 0; i < 4; i++) {
            if (cmd[i] == 1) {
                temp = gear[i][7];
                for (int j = 7; j > 0; j--) {
                    gear[i][j] = gear[i][j-1];
                }
                gear[i][0] = temp;
            }
            
            if (cmd[i] == -1) {
                temp = gear[i][0];
                for (int j = 0; j < 7; j++) {
                    gear[i][j] = gear[i][j+1];
                }
                gear[i][7] = temp;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        gear = new int[4][8];
        int num;

        for (int i = 0; i < 4; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                gear[i][j] = s.charAt(j) - '0';
            }
        }

        num = Integer.parseInt(br.readLine());

        while (num --> 0) {
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            cmd = new int[4];
            cmd[index] = dir;
            checkDir(index);
            rotateGear();
        }

        // System.out.println(gear[0][0]);
        // System.out.println(gear[1][0]);
        // System.out.println(gear[2][0]);
        // System.out.println(gear[3][0]);

        if (gear[0][0] == 1) score+=1;
        if (gear[1][0] == 1) score+=2;
        if (gear[2][0] == 1) score+=4;
        if (gear[3][0] == 1) score+=8;

        System.out.println(score);
    }
    
}
