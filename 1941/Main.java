import java.util.*;
import java.io.*;

public class Main {
    static char[][] map;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int answer = 0;
    static boolean[] visited = new boolean[25];
    static int[]selected=new int[7];

    static void dfs(int depth,int start) {
		if(depth==7){
			if(check())answer++;
			return;
		}
		for(int i=start;i<25;i++) {
			if(!visited[i]) {	
				visited[i]=true;
				selected[depth]=i;
				dfs(depth+1,i+1);
				visited[i]=false;
			}
		}
	}

    static boolean check() {
		int Y=0;
		for(int i:selected) {
			if(map[i/5][i%5]=='Y')Y++;
		}
		if(Y>3)return false;
		ArrayList<Integer>temp = new ArrayList<>();
		for(int a:selected)temp.add(a);
		
		Queue<Integer> q= new LinkedList<>();
		q.offer(selected[0]);
		while(!q.isEmpty()) {
			int i = q.poll();
			for(int d=0;d<4;d++) {
				int nx=i/5+dx[d];
				int ny=i%5+dy[d];
				if(nx>=0&&nx<5&&ny>=0&&ny<5){
					if(temp.contains(nx*5+ny)) {
						temp.remove(Integer.valueOf(nx*5+ny));
						q.offer(nx*5+ny);
					}
				}
			}
		}
		if(!temp.isEmpty())return false;
		return true;
	}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[5][5];
        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < 5; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        dfs(0,0);
        System.out.println(answer);
    }
}
