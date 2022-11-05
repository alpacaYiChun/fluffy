import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class T1547C {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testcases = scanner.nextInt();
        scanner.nextLine();
        for(int t=0;t<testcases;t++) {
            int k = scanner.nextInt();
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[] a = new int[n];
            int[] b = new int[m];
            for(int i=0;i<n;i++) {
                a[i] = scanner.nextInt();
            }
            for(int i=0;i<m;i++) {
                b[i] = scanner.nextInt();
            }
            scanner.nextLine();
            int i = 0;
            int j = 0;
            int have = k;
            boolean fail = false;
            List<Integer> order = new ArrayList<>();
            while(i<n && j<m) {
                boolean progress = false;
                if(a[i] == 0) {
                    have++;
                }
                if(a[i] == 0 || a[i] <= have) {
                    order.add(a[i]);
                    i++;
                    progress = true;
                }

                if(b[j] == 0) {
                    have++;
                }
                if(b[j] == 0 || b[j] <= have) {
                    order.add(b[j]);
                    j++;
                    progress = true;
                }
                if(!progress) {
                    System.out.println(-1);
                    fail = true;
                    break;
                }
            }
            if(!fail) {
                while (i < n && !fail) {
                    if (a[i] == 0) {
                        have++;
                        order.add(a[i]);
                    } else if (a[i] > have) {
                        System.out.println(-1);
                        fail = true;
                        break;
                    } else {
                        order.add(a[i]);
                    }
                    ++i;
                }
                while (j < m && !fail) {
                    if (b[j] == 0) {
                        have++;
                        order.add(b[j]);
                    } else if (b[j] > have) {
                        System.out.println(-1);
                        fail = true;
                        break;
                    } else {
                        order.add(b[j]);
                    }
                    ++j;
                }
            }

            if(!fail) {
                StringBuilder sb = new StringBuilder();
                for(int x=0;x<order.size();x++) {
                    if(x==0) {
                        sb.append(order.get(x));
                    } else {
                        sb.append(' ');
                        sb.append(order.get(x));
                    }
                }
                System.out.println(sb.toString());
            }
        }
    }
}
