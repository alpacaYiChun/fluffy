import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class T1121B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] all = new int[n];
        for(int i=0;i<n;i++) {
            all[i] = scanner.nextInt();
        }
        HashMap<Integer,HashSet<Integer>> h = new HashMap<Integer, HashSet<Integer>>();
        for(int i=0;i<n-1;i++) {
            for(int j=i+1;j<n;j++) {
                int val = all[i] + all[j];
                int min = Math.min(all[i], all[j]);
                if(!h.containsKey(val)) {
                    h.put(val, new HashSet<>());
                }
                h.get(val).add(min);
            }
        }
        int ret = 0;
        for(var kvp : h.entrySet()) {
            ret = Math.max(ret, kvp.getValue().size());
        }

        System.out.println(ret);
    }
}
