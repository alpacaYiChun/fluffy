import java.util.PriorityQueue;

public class C2577 {
    public static class S {
        int i;
        int j;
        int dist;
        public S(int i, int j, int dist) {
            this.i = i;
            this.j = j;
            this.dist = dist;
        }
    }

    private static final int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    public int minimumTime(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if(grid[0][1] > 1 && grid[1][0] > 1) {
            return -1;
        }

        int[][] op = new int[m][n];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                op[i][j] = -1;
            }
        }
        op[0][0] = 0;

        PriorityQueue<S> queue = new PriorityQueue<>((a,b) -> a.dist - b.dist);
        queue.add(new S(0, 0, 0));

        while(!queue.isEmpty()) {
            S fetch = queue.poll();
            if(fetch.i == m-1 && fetch.j == n-1) {
                return fetch.dist;
            }
            if(op[fetch.i][fetch.j] != fetch.dist) {
                continue;
            }
            for(int d=0;d<4;d++) {
                int ii = fetch.i+dirs[d][0];
                int jj = fetch.j+dirs[d][1];
                if(ii < 0 || jj < 0 || ii >= m || jj >= n) {
                    continue;
                }

                int atLeast = grid[ii][jj];
                int next = fetch.dist + 1;
                if(atLeast > next) {
                    int gap = atLeast - fetch.dist;
                    if(gap % 2 == 1) {
                        next = atLeast;
                    } else {
                        next = atLeast + 1;
                    }
                }

                int already = op[ii][jj];
                if(already == -1 || next < already) {
                    op[ii][jj] = next;
                    queue.add(new S(ii, jj, next));
                }
            }
        }

        return -1;
    }
}
