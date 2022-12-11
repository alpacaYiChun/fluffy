import java.util.Scanner;

public class T1196D {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testcases = scanner.nextInt();
        for(int t=0;t<testcases;t++) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            scanner.nextLine();
            String str = scanner.nextLine();
           // System.out.println(str);
            char[] ar = str.toCharArray();
            int[][] correct = new int[3][n];
            int last = emap(ar[n-1]);
            for(int i=0;i<3;i++) {
                correct[i][n-1] = 1;
            }
            correct[last][n-1] = 0;
            for(int j=n-2;j>=0;j--) {
                int code = emap(ar[j]);
                for(int i=0;i<3;i++) {
                    int first = 1;
                    if(code==i) {
                        first = 0;
                    }
                   int next = (i+1)%3;
                   int nextCost = correct[next][j+1];
                   if(j+k<n) {
                       int farExpect = (i+k)%3;
                       int farActual = emap(ar[j+k]);
                       if(farActual!=farExpect) {
                           --nextCost;
                       }
                   }
                   correct[i][j] = first + nextCost;
                }
            }
            int ret = k;
            for(int i=0;i<3;i++) {
                for (int j = 0; j <= n - k; j++) {
                    if(correct[i][j] < ret) {
                        ret = correct[i][j];
                    }
                }
            }
/*
            for(int i=0;i<3;i++) {
                for(int j=0;j<n;j++) {
                    System.out.print(correct[i][j] + " ");
                }
                System.out.println();
            }
*/
            System.out.println(ret);
        }
    }

    public static int emap(char c) {
        switch(c) {
            case 'R': return 0;
            case 'G': return 1;
            case 'B': return 2;
            default: throw new IllegalArgumentException("Bad char");
        }
    }
}
