import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class C2808 {
    public int minimumSeconds(List<Integer> nums) {
        int prev = -1;
        int from = -1;
        var queue = new LinkedList<int[]>();
        int i = 0;
        for(var num : nums) {
            if(num != prev) {
                if(prev != -1) {
                    queue.add(new int[]{prev, from, i-1});
                }
                prev = num;
                from = i;
            }
            i++;
        }
        queue.add(new int[]{prev, from, nums.size() - 1});
        if(queue.size()>1) {
            int head = queue.peek()[0];
            int headCount = queue.peek()[2];
            int tail = queue.getLast()[0];
            int tailCount = queue.getLast()[1];
            if(head == tail) {
                queue.poll();
                queue.removeLast();
                queue.add(new int[]{head, tailCount, headCount});
            }
        }
        var map = new HashMap<Integer, ArrayList<int[]>>();
        for(var elem: queue) {
            if(!map.containsKey(elem[0])) {
                map.put(elem[0], new ArrayList());
            }
            map.get(elem[0]).add(elem);
        }
        int ret = Integer.MAX_VALUE;
        for(var kv : map.entrySet()) {
            var list = kv.getValue();
            if(list.size() == 1) {
                var s = list.get(0);
                int gap = 0;
                if(s[2] >= s[1]) {
                    gap = s[2] - s[1] + 1;
                } else {
                    gap = s[2] + nums.size() - s[1] + 1;
                }
                gap = nums.size() - gap;
                gap = (gap / 2) + (gap & 1);
                if(gap < ret) {
                    ret = gap;
                }
                continue;
            }
            int accu = 0;
            for(int k=0;k<list.size() - 1;k++) {
                var left = list.get(k);
                var right = list.get(k+1);
                var gap = right[1] - left[2] - 1;
                accu = Math.max(accu, (gap / 2) + (gap & 1));
            }
            var last = list.get(list.size()-1);
            var first = list.get(0);
            var lastGap = 0;
            if(first[1] >= last[2]) {
                lastGap = first[1] - last[2] - 1;
            } else {
                lastGap = first[1] + nums.size() - last[2] - 1;
            }
            accu = Math.max(accu, (lastGap / 2) + (lastGap & 1));
            if(accu < ret) {
                ret = accu;
            }
        }
        return ret;
    }
}
