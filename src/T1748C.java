import java.util.*;

public class T1748C {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testcases = scanner.nextInt();
        for(int t=0;t<testcases;t++) {
            int n = scanner.nextInt();
            long[] a = new long[n];
            List<Integer> zeros = new ArrayList<>();
            for(int i=0;i<n;i++) {
                a[i] = scanner.nextInt();
                if(a[i] == 0) {
                    zeros.add(i);
                }
            }
            zeros.add(n);

            long[] s = new long[n];
            s[0] = a[0];
            for(int i=1;i<n;i++) {
                s[i] = s[i-1] + a[i];
            }

            int ret = 0;

            for(int i=0;i<zeros.size()-1;i++) {
                int from = zeros.get(i);
                int to = zeros.get(i+1)-1;
                HashMap<Long, Integer> count = new HashMap<>();
                for(int j=from;j<=to;j++) {
                    long key = s[j];
                    int already = 0;
                    if(count.containsKey(key)) {
                        already = count.get(key);
                    }
                    count.put(key, already+1);
                }
                int max = 0;
                for(Map.Entry<Long, Integer> kvp : count.entrySet()) {
                    if(kvp.getValue() > max) {
                        max = kvp.getValue();
                    }
                }
                ret += max;
            }

            for(int i=0;i<zeros.get(0);i++) {
                if(s[i] == 0) {
                    ++ret;
                }
            }

            System.out.println(ret);
        }
    }
}
