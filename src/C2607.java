import java.util.ArrayList;
import java.util.Collections;

public class C2607 {
        public static void main(String[] args) {
            var s = new C2607();
            System.out.println(s.makeSubKSumEqual(new int[]{10, 3, 8}, 2));
        }
    public long makeSubKSumEqual(int[] arr, int k) {
        int n = arr.length;
        long ret = 0;
        boolean[] step = new boolean[n];
        for(int i=0;i<k;i++) {
            if(step[i]) {
                continue;
            }
            var list = new ArrayList<Integer>();
            for(int j=i;j<n;j=(j+k)%n) {
                if(step[j]) {
                    break;
                }
                list.add(arr[j]);
                step[j] = true;
            }
            Collections.sort(list);
            long med = list.get(list.size()/2);
            System.out.println(med);
            long lower = 0;
            int lowerCount = 0;
            long higher = 0;
            int higherCount =0;
            for(Integer p : list) {
                if(p<=med) {
                    lower += p;
                    lowerCount++;
                } else {
                    higher += p;
                    higherCount++;
                }
            }
            long diff = lowerCount * med - lower + higher - higherCount * med;
            ret += diff;
        }
        return ret;
    }
}
