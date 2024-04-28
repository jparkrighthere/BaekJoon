import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();
        int nameLength = name.length();
        int L = nameLength - name.replaceAll("L", "").length();
        int O = nameLength - name.replaceAll("O", "").length();
        int V = nameLength - name.replaceAll("V", "").length();
        int E = nameLength - name.replaceAll("E", "").length();
        int max = Integer.MIN_VALUE;
        String ret = "";

        int teamNum = Integer.parseInt(br.readLine());
        for (int i = 0; i < teamNum; i++) {
            String teamName = br.readLine();
            int teamLength = teamName.length();
            int Lsum = teamLength - teamName.replaceAll("L", "").length() + L;
            int Osum = teamLength - teamName.replaceAll("O", "").length() + O;
            int Vsum = teamLength - teamName.replaceAll("V", "").length() + V;
            int Esum = teamLength - teamName.replaceAll("E", "").length() + E;

            int result = ((Lsum + Osum) * (Lsum + Vsum) * (Lsum + Esum) * (Osum + Vsum) * (Osum + Esum) * (Vsum + Esum)) % 100;
            if (max < result) {
                max = result;
                ret = teamName;
            }
            if (max == result && teamName.compareTo(ret) < 0) {
                ret = teamName;
            }
        }


        System.out.println(ret);
    }
}
