import java.util.Scanner;

public class T642A {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testcases = scanner.nextInt();
        for(int i=0;i<testcases;i++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            System.out.println(diff(m,n));
        }
    }
    public static int diff(int m, int n) {
        if(n<2) {
            return 0;
        }
        if(n==2) {
            return m;
        }
        return m*2;
    }
}
