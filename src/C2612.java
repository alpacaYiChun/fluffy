import java.util.HashSet;
import java.util.LinkedList;

public class C2612 {
    public int[] minReverseOperations(int n, int p, int[] banned, int k) {
        var ban = new HashSet<Integer>();
        for(int i=0;i<banned.length;i++) {
            ban.add(banned[i]);
        }

        int[] ret = new int[n];
        for(int i=0;i<n;i++) {
            ret[i] = -1;
        }
        ret[p] = 0;

        int[] rightGo = new int[n];
        for(int i=0;i<n;i++) {
            rightGo[i] = -1;
        }
        rightGo[p] = p;

        var queue = new LinkedList<Integer>();
        var dist = new LinkedList<Integer>();
        queue.add(p);
        dist.add(0);
        while(!queue.isEmpty()) {
            var fetch = queue.poll();
            var d = dist.poll();
            var A = Math.max(0, fetch - k + 1);
            var AEnd = A + k -1;
            var AGo = A + AEnd - fetch;
            var B = Math.min(n - 1, fetch + k - 1);
            var BEnd = B - k + 1;
            var BGo = B + BEnd - fetch;

            var go = AGo;
            while(go <= BGo) {
                // Already visited
                if(rightGo[go] != -1) {
                    var next = rightGo[go] + 2;
                    rightGo[go] = Math.max(BGo, rightGo[go]);
                    go = next;
                    continue;
                }
                // Not visited, not banned, queue it
                if(!ban.contains(go)) {
                    queue.add(go);
                    dist.add(d+1);
                    ret[go] = d+1;
                }

                // maintain newly discovered places
                rightGo[go] = BGo;

                // move forward
                go += 2;
            }
        }

        return ret;
    }
}
