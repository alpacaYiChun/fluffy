import java.util.*;

public class C2503 {
    private static int[][] dirs = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
    public int[] maxPoints(int[][] grid, int[] queries) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int[] origin = Arrays.copyOf(queries, queries.length);

        Arrays.sort(queries);

        if(queries[queries.length-1]<=grid[0][0]) {
            return new int[queries.length];
        }

        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        int i = 0;
        while(queries[i] <= grid[0][0]) {
            ++i;
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> grid[a[0]][a[1]] - grid[b[0]][b[1]]);
        queue.add(new int[]{0, 0});
        visited[0][0] = true;

        int now = 0;

        for(;i<queries.length;i++) {
            int limit = queries[i];
            while(!queue.isEmpty()) {
                int[] top = queue.peek();
                if(grid[top[0]][top[1]] >= limit) {
                    break;
                }

                queue.poll();
                ++now;

                for(int d=0;d<4;d++) {
                    int ii = top[0] + dirs[d][0];
                    int jj = top[1] + dirs[d][1];
                    if(ii<0||jj<0||ii>=m||jj>=n||visited[ii][jj]) {
                        continue;
                    }
                    visited[ii][jj] = true;
                    queue.add(new int[]{ii,jj});
                }
            }
            map.put(limit, now);
        }

        int[] ret = new int[queries.length];
        for(int k=0;k<queries.length;k++) {
            if(map.containsKey(origin[k])) {
                ret[k] = map.get(origin[k]);
            }
        }

        return ret;
    }
}
