import java.util.Arrays;
import java.util.Scanner;

public class T1380C {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testcases = scanner.nextInt();
        for (int t = 0; t < testcases; t++) {
            int n = scanner.nextInt();
            int x = scanner.nextInt();
            int[] all = new int[n];
            for(int i=0;i<n;i++) {
                all[i] = scanner.nextInt();
            }
            Arrays.sort(all);
            int[] op = new int[n+1];
            op[n] = 0;
            for(int i=n-1;i>=0;i--) {
                int min = all[i];
                int need = x / min;
                if(x%min!=0) {
                    ++need;
                }
                int next = i + need;
                if(next<=n) {
                    op[i] = op[next] + 1;
                }
            }
            int max = 0;
            for(int i=0;i<n;i++) {
                if(op[i] > max) {
                    max = op[i];
                }
            }
            System.out.println(max);
        }
    }
}
