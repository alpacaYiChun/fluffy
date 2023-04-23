import java.util.Arrays;
import java.util.HashMap;

public class C2426 {
    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        int n = nums1.length;
        int[] s = new int[n];
        int[] sorted = new int[n];
        for(int i=0;i<n;i++) {
            s[i] = nums1[i] - nums2[i];
            sorted[i] = s[i];
        }

        Arrays.sort(sorted);
        int place = 0;
        int prev = 0;
        boolean start = false;
        for(int i=0;i<n;i++) {
            if(!start || prev != sorted[i]) {
                sorted[place] = sorted[i];
                place++;
            }
        }
        var index = new HashMap<Integer, Integer>();
        for(int i=0;i<place;i++) {
            index.put(sorted[i], i);
        }

        long[] fenwick = new long[place + 1];
        for(int i=0;i<n;i++) {
            var val = s[i];
            // [1, place]
            var idx = index.get(val) + 1;
            updateFenwick(place, fenwick, idx, 1);
        }

        long ret = 0;

        for(int i=n-1;i>=0;i--) {
            int idx = index.get(s[i]) + 1;
            updateFenwick(place, fenwick, idx, -1);
            int target = diff + s[i];
            int till = maxLowerIndex(place, sorted, target);
            if(till != -1) {
                ret += sumFenwick(fenwick, till);
            }
        }

        return ret;
    }

    private static int maxLowerIndex(int n, int[] sorted, int target) {
        int from = 0;
        int to = n-1;
        int ret = -1;
        while(from != to) {
            int mid = (from + to) >> 1;
            if(sorted[mid] <= target) {
                ret = mid;
                from = mid + 1;
            } else {
                to = mid;
            }
        }
        if(sorted[from] <= target) {
            ret = from;
        }

        if(ret != -1) {
            ret++;
        }

        return ret;
    }

    private static void updateFenwick(int n, long[] fenwick, int index, int delta) {
        int now = index;
        while(now <= n) {
            fenwick[now] += delta;
            now = parent(now);
        }
    }

    private static long sumFenwick(long[] fenwick, int i) {
        int now = i;
        long sum = 0;
        while(now != 0) {
            sum += fenwick[now];
            var lastBit = lastBit(now);
            now ^= lastBit;
        }
        return sum;
    }

    private static int parent(int i) {
        int lastBit = lastBit(i);
        return i + lastBit;
    }

    private static int lastBit(int i) {
        int san = i & (i-1);
        return i^san;
    }
}
