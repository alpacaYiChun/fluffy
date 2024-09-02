import java.util.Arrays;
import java.util.List;

public class C2809 {
    public int minimumTime(List<Integer> nums1, List<Integer> nums2, int x) {
        int n = nums1.size();
        int[][] g = new int[n][2];
        for(int i=0;i<n;i++) {
            g[i][0] = nums1.get(i);
            g[i][1] = nums2.get(i);
        }
        Arrays.sort(g, (a, b) -> b[1] - a[1]);
        int[][][] op = new int[2][n][3];
        int s1 = 0;
        int s2 = 0;
        for(int i=0;i<n;i++) {
            s1 += g[i][0];
            s2 += g[i][1];
            op[0][i][0] = s1;
            op[0][i][1] = s2;
            op[0][i][2] = 0;
        }
        if(s1 + s2 <= x) {
            return 0;
        }
        int flag = 0;
        for(int k=1;k<n;k++) {
            int old = flag;
            flag ^= 1;
            for(int i=k;i<n;i++) {
                // ith participates in ladder
                int part2 = op[old][i-1][2] + g[i][1] * (k-1);
                int part1 = op[old][i-1][1];
                int part0 = op[old][i-1][0];
                // ith does not participate in ladder
                int dart2 = -1;
                int dart1 = -1;
                int dart0 = -1;
                if(i>k) {
                    dart2 = op[flag][i-1][2];
                    dart1 = op[flag][i-1][1] + g[i][1];
                    dart0 = op[flag][i-1][0] + g[i][0];

                }
                int p = part2 + k * part1 + part0;
                int d = dart2 + k * dart1 + dart0;
                if(i>k && d < p) {
                    op[flag][i][0] = dart0;
                    op[flag][i][1] = dart1;
                    op[flag][i][2] = dart2;
                } else {
                    op[flag][i][0] = part0;
                    op[flag][i][1] = part1;
                    op[flag][i][2] = part2;
                }
            }
            int fin = op[flag][n-1][0] + op[flag][n-1][2] + k * op[flag][n-1][1];
            if(fin <= x) {
                return k;
            }
        }
        return -1;
    }
}
