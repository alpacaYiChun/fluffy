import java.util.Arrays;
import java.util.Scanner;

public class T1133C {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] val = new int[n];
        int[] help = new int[n];
        for(int i=0;i<n;i++) {
            val[i] = scanner.nextInt();
        }
        msort(val, 0, n-1, help);
        //Arrays.sort(val);
        int i = 0;
        int j = 0;
        int max = 0;
        while(j<n) {
            if(val[j]-val[i]<=5) {
                ++j;
            } else {
                int len = j-i;
                if(len>max) {
                    max = len;
                }
                while(val[j]-val[i]>5) {
                    ++i;
                }
            }
        }
        int last = j-i;
        if(last>max) {
            max = last;
        }

        System.out.println(max);
    }

    private static void msort(int[] a, int from, int to, int[] help) {
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
