public class comb4 {
    public int combinationSum4(int[] nums, int target) {
        int[][] c = new int[target+1][target+1];
        for(int i=1;i<=target;i++) {
            c[i][0]=1;
        }
        c[1][1] = 1;
        for(int i=2;i<=target;i++) {
            for(int j=1;j<=i;j++) {
                c[i][j] = c[i-1][j] + c[i-1][j-1];
            }
        }

        int[][] count = new int[target+1][target+1];
        count[0][0] = 1;

        for(int i=0;i<nums.length;i++) {
            for(int j=target;j>=nums[i];j--) {
                for(int use=1;nums[i]*use<=j;use++) {
                    int remain = j - nums[i] * use;
                    for(int total = target; total >= use; total--) {
                        int remainCount = total - use;
                        count[j][total] += count[remain][remainCount] * c[total][use];
                    }
                }
            }
        }

        for(int i=0;i<=target;i++) {
            for(int j=0;j<=target;j++) {
                System.out.print(count[i][j]+" ");
            }
            System.out.println();
        }

        int ret = 0;
        for(int j=0;j<=target;j++) {
            ret += count[target][j];
        }

        return ret;
    }
}
