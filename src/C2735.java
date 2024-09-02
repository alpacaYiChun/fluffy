public class C2735 {
    public long minCost(int[] nums, int x) {
        int n = nums.length;
        int[] min = new int[n];
        for(int i=0;i<n;i++) {
            min[i] = Integer.MAX_VALUE;
        }
        long ret = Long.MAX_VALUE;
        for(int go=0;go<n;go++){
            for(int i=0;i<n;i++) {
                int j = (i+go) % n;
                if(nums[j] < min[i]) {
                    min[i] = nums[j];
                }
            }
            long sum = 0;
            for(int i=0;i<n;i++) {
                sum += min[i];
            }
            long g = go;
            sum += x * g;
            if(sum < ret) {
                ret = sum;
            }
        }
        return ret;
    }
}
