import java.util.*;

public class Gull {
    public int[] minOperationsQueries(int n, int[][] edges, int[][] queries) {
        var e = new HashMap<Integer, List<int[]>>();
        for(int[] edge:edges) {
            int from = edge[0];
            int to = edge[1];
            int len = edge[2];
            if(!e.containsKey(from)) {
                e.put(from, new ArrayList<>());
            }
            if(!e.containsKey(to)) {
                e.put(to, new ArrayList<>());
            }
            e.get(from).add(new int[]{to, len});
            e.get(to).add(new int[]{from, len});
        }
        final int[] order = new int[n];
        int[][] cnt = new int[n][27];
        var tasks = new HashMap<Integer, List<int[]>>();
        var taskList = new ArrayList<int[]>();
        for(var query:queries) {
            int a = query[0];
            int b = query[1];
            var task = new int[]{a,b,-1};
            taskList.add(task);
            if(!tasks.containsKey(a)) {
                tasks.put(a, new ArrayList<>());
            }
            if(!tasks.containsKey(b)) {
                tasks.put(b, new ArrayList<>());
            }
            tasks.get(a).add(task);
            tasks.get(b).add(task);
        }
        PriorityQueue<int[]> finished = new PriorityQueue<>((a,b)->{
            int m1 = Math.min(order[a[0]], order[a[1]]);
            int m2 = Math.min(order[b[0]], order[b[1]]);
            return m2-m1;
        });
        boolean[] step = new boolean[n];
        Stack<Integer> s = new Stack<>();
        s.push(0);
        assign(s, 0, new int[27], order, cnt, e, tasks, finished, step);
        int[] ret = new int[queries.length];
        int j = 0;
        for(var task : taskList) {
            int a = task[0];
            int b = task[1];
            int c =task[2];

            int max = 0;
            int total = 0;
            for(int i=0;i<27;i++) {
                int v = cnt[a][i] + cnt[b][i] - 2 * cnt[c][i];
                if (v > max) {
                    max = v;
                }
                total += v;
            }
            //System.out.println("a="+a+",b="+b+",common="+c+",max="+max+",total="+total);
            ret[j++] = total - max;
        }
        return ret;
    }
    public int assign(Stack<Integer> s,
                      int orderProgress,
                      int[] cnts,
                      int[] order,
                      int[][] cnt,
                      Map<Integer,List<int[]>> e,
                      Map<Integer,List<int[]>> tasks,
                      PriorityQueue<int[]> finished,
                      boolean[] step
    ) {
        var top = s.peek();
        // visit this node
        order[top] = orderProgress;
        cnt[top] = cnts;
        step[top] = true;
        if(tasks.containsKey(top)) {
            for(var task : tasks.get(top)) {
                if(step[task[0]] && step[task[1]]) {
                    finished.add(task);
                }
            }
        }
        // recursive
        int next = orderProgress + 1;
        int total = 1;
        for(var outlink : e.get(top)) {
            if(step[outlink[0]]) {
                continue;
            }
            s.push(outlink[0]);
            int[] p = new int[27];
            for(int i=0;i<27;i++) {
                p[i] = cnts[i];
            }
            //System.out.println(outlink[1]);
            p[outlink[1]]++;
            int count = assign(s,next,p,order,cnt,e,tasks,finished,step);
            s.pop();
            next += count;
            total += count;
        }
        // collect finished
        while(!finished.isEmpty()) {
            var fin = finished.peek();
            var o1 = order[fin[0]];
            var o2 = order[fin[1]];
            if(Math.min(o1, o2) >= orderProgress) {
                fin[2] = top;
                finished.poll();
            } else {
                break;
            }
        }
        return total;
    }
}
