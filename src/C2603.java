import java.util.*;

public class C2603 {
    public int findShortestCycle(int n, int[][] edges) {
        var e = new HashMap<Integer, List<Integer>>();
        for(int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            if(!e.containsKey(a)) {
                e.put(a, new ArrayList<>());
            }
            if(!e.containsKey(b)) {
                e.put(b, new ArrayList<>());
            }
            e.get(a).add(b);
            e.get(b).add(a);
        }
        for(Map.Entry<Integer, List<Integer>> entry : e.entrySet()) {
            var list = entry.getValue();
            Collections.sort(list);
        }

        int min = -1;

        var queue =new LinkedList<Integer>();
        var dq = new LinkedList<Integer>();
        var already = new HashSet<Integer>();
        for(int[] edge : edges) {
            already.clear();
            queue.clear();
            dq.clear();
            int a = edge[0];
            int b = edge[1];
            queue.add(a);
            dq.add(0);
            already.add(a);
            int ans = -1;
            while(!queue.isEmpty() && ans == -1) {
                var fetch = queue.poll();
                var d = dq.poll();
                if(e.containsKey(fetch)) {
                    for(Integer link : e.get(fetch)) {
                        if((fetch==a&&link==b)||(fetch==b&&link==a)) {
                            continue;
                        }
                        if(already.contains(link)) {
                            continue;
                        }
                        if(link == b) {
                            ans = d+1;
                            break;
                        }
                        dq.add(d+1);
                        queue.add(link);
                        already.add(link);
                    }
                }
            }
            if(ans!=-1) {
                if(min == -1 || ans+1 < min) {
                    min = ans+1;
                }
            }
        }

        return min;
    }
}
