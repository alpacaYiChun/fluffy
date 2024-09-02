import java.util.*;

public class C2736 {
    public static class Item{
        int val1;
        int val2;
        public Item(int val1, int val2) {
            this.val1 = val1;
            this.val2 = val2;
        }
    }

    public static class Q {
        int x;
        int y;
        int index;
        public Q(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }
    }

    public static class Fenwick {
        private int[] tree;
        private int n;

        public Fenwick(int n) {
            this.tree = new int[n + 1];
            this.n = n;
        }

        public int sum(int till) {
            int ret = 0;
            int index = till;
            while(index != 0) {
                ret = Math.max(tree[index], ret);
                index = downgrade(index);
            }
            return ret;
        }

        public void insert(int i, int val) {
            int index = i;
            while(index <= n) {
                if(tree[index] >= val) {
                    break;
                }
                tree[index] = val;
                index = parent(index);
            }
        }

        private static int parent(int val) {
            return val + last(val);
        }
        private static int downgrade(int val) {
            return val & (val - 1);
        }
        private static int last(int val) {
            return val ^ (val & (val - 1));
        }
    }
    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        Item[] list = new Item[n];
        for(int i=0;i<n;i++) {
            list[i] = new Item(nums1[i], nums2[i]);
        }
        Arrays.sort(list, (a, b) -> b.val1 - a.val1);

        Integer[] patch = new Integer[n];
        for(int i=0;i<n;i++) {
            patch[i] = nums2[i];
        }
        Arrays.sort(patch, (a, b) -> b - a);
        int n2 = 0;
        int prev = -1;
        for(int i=0;i<n;i++) {
            if(patch[i] != prev) {
                patch[n2++] = patch[i];
                prev = patch[i];
            }
        }
        HashMap<Integer, Integer> id2 = new HashMap<>();
        for(int i=0;i<n2;i++) {
            id2.put(patch[i], i + 1);
        }

        var q = new ArrayList<Q>(queries.length);
        for(int i=0;i<queries.length;i++) {
            q.add(new Q(queries[i][0], queries[i][1], i));
        }
        Collections.sort(q, (a, b) -> b.x - a.x);

        Fenwick fenwick = new Fenwick(n2);

        int[] ret = new int[queries.length];

        int till = -1;
        for(Q query : q) {
            int kdx = query.index;

            int t = till + 1;
            while(t < n && list[t].val1 >= query.x) {
                int i2 = id2.get(list[t].val2);
                fenwick.insert(i2, list[t].val1 + list[t].val2);
                t++;
            }

            till = t - 1;
            if(till == -1) {
                ret[kdx] = -1;
                continue;
            }

            int j2 = lastLarger(patch, n2, query.y) + 1;
            if(j2==0) {
                ret[kdx] = -1;
                continue;
            }

            int ptd = fenwick.sum(j2);
            ret[kdx] = ptd == 0 ? -1: ptd;
        }

        return ret;
    }

    private static int lastLarger(Integer[] ar, int n2, int target) {
        int from = 0;
        int to = n2 - 1;
        int ret = -1;
        while(from != to) {
            int mid = (from + to) >> 1;
            if(ar[mid] >= target) {
                ret = mid;
                from = mid + 1;
            } else {
                to = mid;
            }
        }
        if(ar[from] >= target) {
            ret = from;
        }
        return ret;
    }
}
