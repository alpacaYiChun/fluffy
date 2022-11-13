import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class T1369C {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testcases = scanner.nextInt();
        for(int t=0;t<testcases;t++) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            long[] a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextLong();
            }
            long[] w = new long[k];
            for (int i = 0; i < k; i++) {
                w[i] = scanner.nextLong();
            }
            msort(a, 0, a.length-1, new long[n]);
            msort(w, 0, k-1, new long[k]);
            Arrays.sort(a);
            Arrays.sort(w);
            int smallEnd = 0;
            int bigEnd = a.length - 1;
            long ret = 0;
            int e = 0;
            for(;e<w.length&&w[e]==1;e++) {
                ret+=a[bigEnd] * 2;
                bigEnd--;
            }
            for (int i = w.length-1; i >= e; i--) {
                long need = w[i];
                ret += a[smallEnd] + a[bigEnd];
                smallEnd++;
                bigEnd--;
                for (int h = 0; h < need - 2; h++) {
                    smallEnd++;
                }
                //System.out.println(need + "," + smallEnd + "," + bigEnd);
            }
            System.out.println(ret);
        }
    }

    private static void msort(long[] a, int from, int to, long[] help) {
        if(from==to) {
            return;
        }
        int mid = (from+to)/2;
        msort(a, from, mid, help);
        msort(a, mid+1, to, help);
        int i = from;
        int j = mid+1;
        int k = 0;
        while(i<=mid && j<=to) {
            if(a[i]<a[j]) {
                help[k++] = a[i];
                ++i;
            } else {
                help[k++] = a[j];
                ++j;
            }
        }
        while(i<=mid) {
            help[k++] = a[i++];
        }
        while(j<=to) {
            help[k++] = a[j++];
        }
        for(int x=from,y=0;y<k;y++,x++) {
            a[x]=help[y];
        }
    }
}
