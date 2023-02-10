import java.util.Scanner;

public class T22B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        var title = scanner.nextLine();
        var sp = title.split(" ");
        int m = Integer.parseInt(sp[0]);
        int n = Integer.parseInt(sp[1]);
        char[][] pits = new char[m][n];
        for(int i=0;i<m;i++) {
            var in = scanner.nextLine();
            pits[i] = in.toCharArray();
        }
        int[][] left = new int[m][n];
        int[][] right = new int[m][n];
        for(int i=0;i<m;i++) {
            left[i][0] = pits[i][0] == '0'? 1 : 0;
            for(int j=1;j<n;j++) {
                if(pits[i][j]=='0') {
                    left[i][j] = left[i][j-1]+1;
                } else {
                    left[i][j] = 0;
                }
            }
            right[i][n-1] = pits[i][n-1] == '0' ? 1 : 0;
            for(int j=n-2;j>=0;j--) {
                if(pits[i][j]=='0') {
                    right[i][j] = right[i][j+1] + 1;
                } else {
                    right[i][j] = 0;
                }
            }
        }
        int[][] up = new int[m][n];
        int[][][] extend = new int[m][n][2];
        for(int j=0;j<n;j++) {
            up[0][j] = pits[0][j] == '0' ? 1 : 0;
            extend[0][j][0] = left[0][j];
            extend[0][j][1] = right[0][j];
        }
        for(int i=1;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(pits[i][j] == '0') {
                    up[i][j] = up[i-1][j] + 1;
                    extend[i][j][0] = pits[i-1][j]=='0'? Math.min(extend[i-1][j][0], left[i][j]) : left[i][j];
                    extend[i][j][1] = pits[i-1][j]=='0'? Math.min(extend[i-1][j][1], right[i][j]) : right[i][j];
                } else {
                    up[i][j] = 0;
                    extend[i][j][0] = 0;
                    extend[i][j][1] = 0;
                }
            }
        }

        int max = 0;

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(pits[i][j] == '1') {
                    continue;
                }
                int height = up[i][j];
                int width = extend[i][j][0] + extend[i][j][1] - 1;
                int cir = (height + width) * 2;
                if(cir > max) {
                    max = cir;
                }
            }
        }

        System.out.println(max);
    }
}
