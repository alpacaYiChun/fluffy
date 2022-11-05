import java.util.Scanner;

public class T1333B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testcases = scanner.nextInt();
        for(int t = 0; t<testcases;t++) {
            int n = scanner.nextInt();
            int[] a = new int[n];
            int[] b = new int[n];
            for(int i=0;i<n;i++) {
                a[i] = scanner.nextInt();
            }
            for(int i=0;i<n;i++) {
                b[i] = scanner.nextInt();
            }
            int inc = 0;
            int dec = 0;
            boolean fail = false;
            for(int i=0;i<n;i++) {
                int original = a[i];
                int expected = b[i];
                if(expected > original && inc==0) {
                    fail = true;
                } else if (expected < original && dec == 0) {
                    fail = true;
                }
                if(fail) {
                    System.out.println("NO");
                    break;
                }
                if(a[i] == -1) {
                    dec++;
                } else if(a[i] == 1) {
                    inc++;
                }
            }
            if(!fail) {
                System.out.println("YES");
            }
        }
    }
}
