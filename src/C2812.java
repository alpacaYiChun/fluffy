import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class C2812 {
    public static final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int[][] g = new int[n][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                g[i][j] = -1;
            }
        }
        var queue = new LinkedList<int[]>();
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(grid.get(i).get(j) == 1) {
                    queue.add(new int[]{i,j,0});
                    g[i][j] = 0;
                }
            }
        }
        while(!queue.isEmpty()) {
            var fetch = queue.poll();
            for(var d : dirs) {
                int ii = fetch[0] + d[0];
                int jj = fetch[1] + d[1];
                if(ii<0||jj<0||ii>=n||jj>=n||g[ii][jj] != -1) {
                    continue;
                }
                queue.add(new int[]{ii,jj,fetch[2] + 1});
                g[ii][jj] = fetch[2] + 1;
            }
        }
        var pq = new PriorityQueue<int[]>((a,b) -> b[2] - a[2]);
        pq.add(new int[]{0,0,g[0][0]});
        int[][] op = new int[n][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                op[i][j] = -1;
            }
        }
        op[0][0] = g[0][0];

        while(!pq.isEmpty()) {
            var fetch = pq.poll();
            if(fetch[0]==n-1&&fetch[1]==n-1) {
                return fetch[2];
            }
            if(op[fetch[0]][fetch[1]] != fetch[2]) {
                continue;
            }
            for(var d : dirs) {
                int ii = fetch[0] + d[0];
                int jj = fetch[1] + d[1];
                if(ii<0||jj<0||ii>=n||jj>=n) {
                    continue;
                }
                int attempt = Math.min(fetch[2], g[ii][jj]);
                if(attempt <= op[ii][jj]) {
                    continue;
                }
                op[ii][jj] = attempt;
                pq.add(new int[]{ii,jj,attempt});
            }
        }
        return -1;
    }
}
