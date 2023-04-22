import java.util.*;

public class C2508 {
    public boolean isPossible(int n, List<List<Integer>> edges) {
        var e = new HashMap<Integer, HashSet<Integer>>();
        for(var edge : edges) {
            int from = edge.get(0);
            int to = edge.get(1);
            if(!e.containsKey(from)) {
                e.put(from, new HashSet<>());
            }
            if(!e.containsKey(to)) {
                e.put(to, new HashSet<>());
            }
            e.get(from).add(to);
            e.get(to).add(from);
        }
        var badGuys = new ArrayList<Integer>();
        for(var entry : e.entrySet()) {
            if(entry.getValue().size() % 2 == 1) {
                badGuys.add(entry.getKey());
            }
        }
        if(badGuys.size() == 0) {
            return true;
        }
        if(badGuys.size()>4) {
            return false;
        }
        if(badGuys.size() % 2 == 1) {
            return false;
        }
        if(badGuys.size() == 4) {
            int a1 = badGuys.get(0);
            int a2 = badGuys.get(1);
            int a3 = badGuys.get(2);
            int a4 = badGuys.get(3);
            return (!directConnected(e, a1, a2) && !directConnected(e, a3, a4))
                    || (!directConnected(e, a1, a3) && !directConnected(e, a2, a4))
                    || (!directConnected(e, a1, a4) && !directConnected(e, a2, a3));
        }
        int b1 = badGuys.get(0);
        int b2 = badGuys.get(1);
        return !directConnected(e, b1, b2) || around(e, b1, b2, n);
    }

    private static boolean directConnected(HashMap<Integer,HashSet<Integer>> e, int i, int j) {
        return e.containsKey(i) && e.get(i).contains(j);
    }

    private static boolean around(HashMap<Integer, HashSet<Integer>> e, int i, int j, int n) {
        for(int k=1;k<=n;k++) {
            if(k!=i&&k!=j&&!directConnected(e, i, k) && !directConnected(e, j, k)) {
                return true;
            }
        }
        return false;
    }
}
