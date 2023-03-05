import java.util.*;

public class C2581 {
    public static class S {
        int id;
        int correct = 0;

        int wrong = 0;

        public S(int id, int correct, int wrong) {
            this.id = id;
            this.correct = correct;
            this.wrong = wrong;
        }
    }
    public int rootCount(int[][] edges, int[][] guesses, int k) {
        int maxError = guesses.length - k;

        HashMap<Integer, HashSet<Integer>> e = new HashMap<Integer, HashSet<Integer>>();
        for(int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            if(!e.containsKey(a)) {
                e.put(a, new HashSet<>());
            }
            if(!e.containsKey(b)) {
                e.put(b, new HashSet<>());
            }
            e.get(a).add(b);
            e.get(b).add(a);
        }
        HashMap<Integer, List<Integer>> forward = new HashMap<>();
        for(int[] guess : guesses) {
            int a = guess[0];
            int b = guess[1];
            if(!forward.containsKey(a)) {
                forward.put(a, new ArrayList<>());
            }
            forward.get(a).add(b);
        }

        HashSet<Integer> already = new HashSet<>();
        LinkedList<S> queue = new LinkedList<>();
        S init = new S(0, 0, 0);
        queue.add(init);
        already.add(0);
        int global = 0;
        HashMap<Integer, S> sour = new HashMap<>();
        sour.put(0, init);
        while(!queue.isEmpty()) {
            S fetch = queue.poll();
            for(Integer link : e.get(fetch.id)) {
                if(already.contains(link)) {
                    continue;
                }

                int parent = fetch.id;
                int now = link;
                int addWrong = 0;
                int addCorrect = 0;
                if(forward.containsKey(now) && forward.get(now).contains(parent)) {
                    global++;
                    addWrong = 1;
                }
                if(forward.containsKey(parent) && forward.get(parent).contains(now)) {
                    addCorrect = 1;
                }
                S create = new S(link, fetch.correct + addCorrect, fetch.wrong + addWrong);
                queue.add(create);
                already.add(link);
                sour.put(link, create);
            }
        }

        int ret = 0;
        for(Map.Entry<Integer, S> entry : sour.entrySet()) {
            int wrong = global + entry.getValue().correct - entry.getValue().wrong;
            if(wrong <= maxError) {
                ret++;
            }
        }

        return ret;
    }
}
