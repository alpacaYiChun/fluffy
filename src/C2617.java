public class C2617 {
    public int minimumVisitedCells(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] minDown = new int[m][n][17];
        int[][][] minRight = new int[m][n][17];
        int[][] op = new int[m][n];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                op[i][j] = Integer.MAX_VALUE;
                for(int k=0;k<17;k++) {
                    minDown[i][j][k] = Integer.MAX_VALUE;
                    minRight[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        op[m-1][n-1] = 0;
        minDown[m-1][n-1][0] = 0;
        minRight[m-1][n-1][0] = 0;
        for(int i=m-1;i>=0;i--) {
            for(int j=n-1;j>=0;j--) {
                if(i==m-1&&j==n-1) {
                    continue;
                }
                int rMin = Integer.MAX_VALUE;
                int dMin = Integer.MAX_VALUE;
                int maxRight = Math.min(grid[i][j] + j, n-1);
                if(maxRight > j) {
                    int step = maxRight - j;
                    int at = j + 1;
                    for(int b=16;b>=0;b--) {
                        int bit = 1<<b;
                        if((step & bit) != 0) {
                            rMin = Math.min(rMin, minRight[i][at][b]);
                            at += bit;
                        }
                    }
                }
                int maxDown = Math.min(grid[i][j] + i, m-1);
                if(maxDown > i) {
                    int step = maxDown - i;
                    int at = i + 1;
                    for(int b=16;b>=0;b--) {
                        int bit = 1<<b;
                        if((step & bit) != 0) {
                            dMin = Math.min(dMin, minDown[at][j][b]);
                            at += bit;
                        }
                    }
                }
                int kMin = Math.min(rMin, dMin);
                if(kMin != Integer.MAX_VALUE) {
                    op[i][j] = kMin + 1;
                }
                minRight[i][j][0] = op[i][j];
                minDown[i][j][0] = op[i][j];
                // Maintain
                for(int k=1;k<17;k++) {
                    if(i+(1<<k)<=m) {
                        minDown[i][j][k] = Math.min(minDown[i][j][k-1], minDown[i+(1<<(k-1))][j][k-1]);
                    }
                    if(j+(1<<k)<=n) {
                        minRight[i][j][k] = Math.min(minRight[i][j][k-1], minRight[i][j+(1<<(k-1))][k-1]);
                    }
                }
            }
        }

        if(op[0][0] == Integer.MAX_VALUE) {
            return -1;
        }

        return op[0][0] + 1;
    }
}
