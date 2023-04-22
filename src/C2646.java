import java.util.*;

public class C2646 {
    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        var e = new HashMap<Integer, List<Integer>>();
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            if (!e.containsKey(a)) {
                e.put(a, new ArrayList<Integer>());
            }
            if (!e.containsKey(b)) {
                e.put(b, new ArrayList<Integer>());
            }
            e.get(a).add(b);
            e.get(b).add(a);
        }

        int[] parent = new int[n];
        int[] order = new int[n];
        int[] seq = new int[n];
        parent[0] = -1;
        Latch latch = new Latch();
        dfs(0, e, latch, order, parent, seq);

        int[] hit = new int[n];
        for(int[] trip : trips) {
            int a = trip[0];
            int b = trip[1];

            if (a == b) {
                hit[a]++;
               continue;
            }

            if(order[a] > order[b]) {
                a^=b;
                b^=a;
                a^=b;
            }

            int lca = b;
            while(order[lca] > order[a]) {
                lca = parent[lca];
            }

            int left = a;
            while(left != lca) {
                hit[left]++;
                left = parent[left];
            }
            int right = b;
            while(right != lca) {
                hit[right]++;
                right = parent[right];
            }

            hit[lca]++;
        }

        int[] op = new int[n];
        for(int i=n-1;i>=0;i--) {
            int node = seq[i];
            int originalPrice = price[node];
            int reducedPrice = originalPrice / 2;
            int profitHere = (originalPrice - reducedPrice) * hit[node];
            int childrenProfit = 0;
            int grandChildrenProfit = 0;
            if(e.containsKey(node)) {
                for (Integer c : e.get(node)) {
                    if(c == parent[node]) {
                        continue;
                    }
                    childrenProfit += op[c];
                    for(Integer cc : e.get(c)) {
                        if(cc == node) {
                            continue;
                        }
                        grandChildrenProfit += op[cc];
                    }
                }
            }
            op[node] =  Math.max(profitHere + grandChildrenProfit, childrenProfit);
        }

        int tmd = 0;
        for(int i=0;i<n;i++) {
            tmd += price[i] * hit[i];
        }

        return tmd - op[0];
    }

    private static void dfs(int root, HashMap<Integer, List<Integer>> e, Latch latch,
                            int[] order, int[] parent, int[] seq) {
        int nowOrder = latch.get();
        seq[nowOrder] = root;
        order[root] = nowOrder;
        latch.inc();

        if(e.containsKey(root)) {
            for(Integer link : e.get(root)) {
                if(link == parent[root]) {
                    continue;
                }
                parent[link] = root;
                dfs(link, e, latch, order, parent, seq);
            }
        }
    }
    private static class Latch {
        private int value = 0;
        public void inc() {
            ++value;
        }
        public int get() {
            return value;
        }
    }
}
