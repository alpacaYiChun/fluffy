import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class T1753A {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testcases = scanner.nextInt();
        for(int t=0;t<testcases;t++) {
            int n = scanner.nextInt();
            int sum = 0;
            int[] a = new int[n];
            for(int i=0;i<n;i++) {
                a[i] = scanner.nextInt();
                sum += a[i];
            }
            if(Math.abs(sum) % 2 == 1) {
                System.out.println(-1);
                continue;
            }
            if (sum == 0) {
                System.out.println(n);
                for(int i=1;i<=n;i++) {
                    System.out.println(i + " " + i);
                }
                continue;
            }
            int need = Math.abs(sum) / 2;
            int sign = sum  / Math.abs(sum);

            var pivots = pivot(a, sign, need);
            if(pivots.size() < need) {
                System.out.println(-1);
                continue;
            }
            System.out.println(n-need);
            int p = 0;
            for(int i = 0; i<n;i++) {
                if(p<pivots.size() && i==pivots.get(p)-1) {
                    System.out.println((i+1)+" "+(i+2));
                    ++p;
                    ++i;
                } else {
                    System.out.println((i+1) + " " + (i+1));
                }
            }
        }
    }

    private static List<Integer> pivot(int[] input, int sign, int need) {
        List<Integer> pivots = new ArrayList<>();
        int got = 0;
        int n = input.length;
        for(int i=0;i<n && got<need; i++) {
            if(input[i] == sign) {
                int start = i;
                int end = i;
                while(end<n&&input[end]==sign) {
                    ++end;
                }
                int k = start;
                if(start == 0) {
                    k++;
                }
                while(k<end && got < need) {
                    pivots.add(k);
                    k+=2;
                    ++got;
                }

                i = end - 1;
            }
        }
        return pivots;
    }
}
