import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class C2573 {
    public static class Seg {
        int from;
        int to;
        Seg left;
        Seg right;
        List<Integer> canBack = new ArrayList<Integer>();
        public Seg(int from, int to) {
            this.from = from;
            this.to = to;
        }
        public void split() {
            if(from==to) {
                return;
            }
            if(this.left!=null) {
                return;
            }
            int mid = (from+to) >> 1;
            this.left = new Seg(from, mid);
            this.right = new Seg(mid+1, to);
        }

        public void insert(int a, int b, int d) {
            if(a>to||b<from) {
                return;
            }
            if(a<=from && b>=to) {
                this.canBack.add(d);
                return;
            }
            split();
            this.left.insert(a,b,d);
            this.right.insert(a,b,d);
        }
    }

    public static HashSet<Integer> getAll(Seg root, int i) {
        var ret = new HashSet<Integer>();
        Seg now = root;
        while(now != null) {
            for(Integer d : now.canBack) {
                ret.add(i - d);
            }
            if(now.left != null) {
                if(i > now.left.to) {
                    now = now.right;
                } else {
                    now = now.left;
                }
            } else {
                now = null;
            }
        }
        return ret;
    }

    public String findTheString(int[][] lcp) {

        int n = lcp.length;

        for(int i=0;i<n;i++) {
            if(lcp[i][i] != n-i) {
                return "";
            }
        }

        for(int i=0;i<n-1;i++) {
            for(int j=i+1;j<n;j++) {
                if(lcp[i][j] != lcp[j][i]) {
                    return "";
                }
                if(j+lcp[i][j] > n) {
                    return "";
                }
            }
        }

        Seg root = new Seg(0,n-1);

        for(int i=0;i<n-1;i++) {
            for(int j=i+1;j<n;j++) {
                if(i>0 && lcp[i-1][j-1] > 1) {
                    continue;
                }
                int common = lcp[i][j];
                int d = j-i;
                root.insert(j, j+common-1, d);
            }
        }
        int[] op = new int[n];
        for(int i=0;i<n;i++) {
            op[i] = i;
        }
        for(int i=0;i<n;i++) {
            HashSet<Integer> all = getAll(root, i);
            /*
            for(Integer t : all) {
                System.out.println(i+":"+t);
            }
            */
            int minOp = i;
            for(Integer q : all) {
                if(op[q] < minOp) {
                    minOp = op[q];
                }
            }
            op[i] = minOp;
            for(Integer q : all) {
                op[q] = minOp;
            }
        }
        /*
        for(int i=0;i<n;i++) {
            System.out.print(op[i]+" ");
        }
        */
        HashMap<Integer, HashSet<Integer>> cannot = new HashMap<>();
        for(int i=0;i<n-1;i++) {
            for(int j=i+1;j<n;j++) {
                int common = lcp[i][j];
                int ii = i+common;
                int jj = j+common;
                if(ii<n&&jj<n) {
                    if(op[ii] == op[jj]) {
                        return "";
                    }
                    int gi = op[ii];
                    int gj = op[jj];
                    if(!cannot.containsKey(gi)) {
                        cannot.put(gi, new HashSet<>());
                    }
                    if(!cannot.containsKey(gj)) {
                        cannot.put(gj, new HashSet<>());
                    }
                    cannot.get(gi).add(gj);
                    cannot.get(gj).add(gi);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        HashMap<Integer, Integer> groupMap = new HashMap<>();
        for(int i=0;i<n;i++) {
            int g = op[i];
            if(groupMap.containsKey(g)) {
                sb.append((char)('a'+(groupMap.get(g))));
            } else {
                boolean[] rabid = new boolean[26];
                if(cannot.containsKey(g)) {
                    for(Integer conflict : cannot.get(g)) {
                        if(groupMap.containsKey(conflict)) {
                            rabid[groupMap.get(conflict)] = true;
                        }
                    }
                }
                boolean found = false;
                for(int k=0;k<26;k++) {
                    if(!rabid[k]) {
                        sb.append((char)('a' + k));
                        groupMap.put(g, k);
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    return "";
                }
            }
        }

        return sb.toString();
    }
}
