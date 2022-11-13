import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class T1711B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testcases = scanner.nextInt();
        for(int t = 0; t<testcases;t++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[] u = new int[n];
            for(int i=0;i<n;i++) {
                u[i] = scanner.nextInt();
            }
            HashMap<Integer, List<Integer>> f = new HashMap<Integer, List<Integer>>();
            for(int i=0;i<m;i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                if(!f.containsKey(a)) {
                    f.put(a, new ArrayList<>());
                }
                if(!f.containsKey(b)) {
                    f.put(b, new ArrayList<>());
                }
                f.get(a).add(b);
                f.get(b).add(a);
            }

            if(m % 2 == 0) {
                System.out.println(0);
                continue;
            }

            int single = Integer.MAX_VALUE;
            for(int i=1;i<=n;i++) {
                if(f.containsKey(i)) {
                    int val = f.get(i).size();
                    if(val % 2 == 1) {
                        single = Math.min(single, u[i-1]);
                    } else {
                        for(Integer j : f.get(i)) {
                            int pval = f.get(j).size();
                            if(pval % 2 == 0) {
                                int test = u[i-1] + u[j-1];
                                single = Math.min(single, test);
                            }
                        }
                    }
                }
            }

            System.out.println(single);
        }
    }
}
