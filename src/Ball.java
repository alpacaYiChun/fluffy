import java.sql.Array;
import java.util.*;

public class Ball {
    public long getMaxFunctionValue(List<Integer> receiver, long k) {
        k = k+1;

        int n = receiver.size();

        long[] ksum = new long[n];
        for(int i=0;i<n;i++) {
            ksum[i] = -1;
        }

        // States to be maintained
        List<List<Integer>> circles = new ArrayList<>();
        List<Long> circleSums = new ArrayList<>();
        int nextCircleIndex = 0;
        var circleMap = new HashMap<Integer, int[]>();
        var circleCums = new ArrayList<List<Long>>();
        boolean[] visited = new boolean[n];

        for(int i=0;i<n;i++) {
            if(visited[i]) {
                continue;
            }

            int now = i;
            HashSet<Integer> session = new HashSet<>();
            var list = new ArrayList<Integer>();
            while(!visited[now]) {
                list.add(now);
                session.add(now);
                visited[now] = true;
                now = receiver.get(now);
            }
            int end = now;

            if(session.contains(end)) {
                // A new six
                // Get a circle
                var circle = new ArrayList<Integer>();
                boolean start = false;
                long sum = 0;
                var cum = new ArrayList<Long>(list.size());
                int order = 0;
                for(var e : list) {
                    if(e == end) {
                        start = true;
                    }
                    if(start) {
                        circleMap.put(e, new int[] {nextCircleIndex, order});
                        circle.add(e);
                        sum += e;
                        cum.add(sum);
                        order++;
                    }
                }
                circles.add(circle);
                circleSums.add(sum);
                circleCums.add(cum);
                // Process a circle
                long rounds = k / circle.size();
                int trace = (int)(k % circle.size());
                long initWindow = sum * rounds;
                if(trace>0) {
                    initWindow += cum.get(trace-1);
                }
                int first = circle.get(0);
                ksum[first] = initWindow;
                for(int j=1;j<circle.size();j++) {
                    int pre = circle.get(j-1);
                    int here = circle.get(j);
                    int afterIndex = (int)((j + k - 1) % circle.size());
                    int after = circle.get(afterIndex);
                    initWindow -= pre;
                    initWindow += after;
                    ksum[here] = initWindow;
                }
                // Advance index
                nextCircleIndex++;
            }
        }
        var edges = new HashMap<Integer, List<Integer>>();
        for(int i=0;i<receiver.size();i++) {
            int from = receiver.get(i);
            int to = i;
            if(!edges.containsKey(from)) {
                edges.put(from, new ArrayList<>());
            }
            edges.get(from).add(to);
        }
        for(var circle:circles) {
            for(var elem : circle) {
                Stack<Integer> s = new Stack<>();
                s.push(elem);
                dfs(receiver, circleMap, circles, circleSums,circleCums,  edges, s, ksum, k);
            }
        }
        long ret = 0;
        for(int i=0;i<n;i++) {
            if(ksum[i] > ret) {
                ret = ksum[i];
            }
        }
        return ret;
    }

    public void dfs(List<Integer> rec,
                    HashMap<Integer, int[]> circleMap,
                    List<List<Integer>> circles,
                    List<Long> circleSums,
                    List<List<Long>> circleCums,
                    HashMap<Integer, List<Integer>> edges,
                    Stack<Integer> stack,
                    long[] ksum,
                    long k) {
        var top = stack.peek();
        var children = new ArrayList<Integer>();
        if (edges.containsKey(top)) {
            for (var outlink : edges.get(top)) {
                if (!circleMap.containsKey(outlink)) {
                    children.add(outlink);
                }
            }
        }
        // leaf
        if (children.isEmpty()) {
            long ribbon = 0;
            long left = k;
            for (int j = stack.size() - 1; j >= 1 && left > 0; j--) {
                ribbon += stack.get(j);
                left--;
            }
            long loop = 0;
            int last = -1;
            if (left > 0) {
                var pivot = stack.get(0);
                var circleInfo = circleMap.get(pivot);
                int pivotCircleIndex = circleInfo[0];
                int pivotCircleOrder = circleInfo[1];
                var thatCircle = circles.get(pivotCircleIndex);
                int circleSize = thatCircle.size();
                var cum = circleCums.get(pivotCircleIndex);
                long rounds = left / circleSize;
                int trace = (int) (left % circleSize);
                loop = rounds * circleSums.get(pivotCircleIndex);
                if (trace > 0) {
                    int a = pivotCircleOrder;
                    int b = (a + trace - 1) % circleSize;
                    if (a > b) {
                        var part1 = cum.get(cum.size() - 1) - (a > 0 ? cum.get(a - 1) : 0);
                        var part2 = cum.get(b);
                        loop += part1 + part2;
                    } else {
                        var inc = cum.get(b) - (a > 0 ? cum.get(a - 1) : 0);
                        loop += inc;
                    }
                    last = thatCircle.get(b);
                } else {
                    var newIndex = (pivotCircleOrder + circleSize - 1) % circleSize;
                    last = thatCircle.get(newIndex);
                }
            } else {
                last = stack.get((int) (stack.size() - k));
            }
            long window = ribbon + loop;
            var f = stack.get(stack.size() - 1);
            ksum[f] = window;

            for (int j = stack.size() - 2; j >= 1; j--) {
                var idx = stack.get(j);
                if (ksum[idx] != -1) {
                    break;
                }
                int pre = stack.get(j + 1);
                last = rec.get(last);
                window -= pre;
                window += last;
                ksum[idx] = window;
            }
        } else {
            for (var child : children) {
                stack.push(child);
                dfs(rec, circleMap, circles, circleSums, circleCums, edges, stack, ksum, k);
                stack.pop();
            }
        }
    }

}
