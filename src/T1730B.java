import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class T1730B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long[][] xd = new long[100000][2];

        long[] left = new long[100000];
        long[] right = new long[100000];

        int testcase = scanner.nextInt();
        for(int t=0;t<testcase;t++) {
            int n = scanner.nextInt();
            for(int i=0;i<n;i++) {
                xd[i][0] = scanner.nextLong();
            }
            for(int i=0;i<n;i++) {
                xd[i][1] = scanner.nextLong();
            }

            if(n == 1) {
                System.out.println(xd[0][0]);
                continue;
            }

            Arrays.sort(xd, 0, n, (a, b) -> {
                if(a[0] < b[0]) {
                    return -1;
                }
                if(a[0] > b[0]) {
                    return 1;
                }
                return 0;
            });

            for(int i=0;i<n;i++) {
                right[i] = xd[i][0] + xd[i][1];
                left[i] = xd[i][1] - xd[i][0];
            }

            for(int i=1;i<n;i++) {
                left[i] = Math.max(left[i], left[i-1]);
            }
            for(int i=n-2;i>=0;i--) {
                right[i] = Math.max(right[i], right[i+1]);
            }

            double min = Double.MAX_VALUE;
            double minI = -1;

            for(int i=0;i<n-1;i++) {
                long l = xd[i][0];
                long r = xd[i+1][0];
                long ml = left[i];
                double mr = right[i+1];
                double ideal = (mr - ml) / 2;

                if(ideal < l) {
                    ideal = l;
                } else if(ideal > r) {
                    ideal = r;
                }
                double cost = Math.max(ideal + ml, mr - ideal);
                if(cost < min) {
                    min = cost;
                    minI = ideal;
                }
            }

            System.out.println(minI);
        }
    }
}
