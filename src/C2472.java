import java.util.ArrayList;
import java.util.List;

public class C2472 {
    public int maxPalindromes(String s, int k) {
        char[] ar = s.toCharArray();
        int n = ar.length;
        List<List<Integer>> close = new ArrayList<>();
        for(int i=0;i<2;i++) {
            close.add(new ArrayList<>());
        }
        int flag = 0;
        int last = 1;
        close.get(flag).add(n-1);
        int[] op = new int[n];
        if(k==1) {
            op[n-1] = 1;
        }
        for(int i=n-2;i>=0;i--) {
            flag = 1-flag;
            last = 1-flag;
            close.get(flag).clear();
            close.get(flag).add(i);
            if(ar[i]==ar[i+1]) {
                close.get(flag).add(i+1);
            }
            for(Integer p : close.get(last)) {
                if(p<n-1&&ar[i]==ar[p+1]) {
                    close.get(flag).add(p+1);
                }
            }
            int max = 0;
            for(Integer p : close.get(flag)) {
                int len = p-i+1;
                if(len>=k) {
                    if(p<n-1) {
                        max = op[p+1] + 1;
                    } else {
                        max = 1;
                    }
                    break;
                }
            }
            if(op[i+1]>max) {
                max = op[i+1];
            }
            op[i] = max;
        }
        return op[0];
    }
}
