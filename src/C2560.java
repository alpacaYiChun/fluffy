import java.util.Arrays;

public class C2560 {
    public int minCapability(int[] nums, int k) {
        int to = Arrays.stream(nums).max().getAsInt();
        int from = 1;
        int ret = to;
        while(from!=to) {
            int mid = (from + to) / 2;
            if(ok(nums, k, mid)) {
                ret = mid;
                to = mid;
            } else {
                from = mid + 1;
            }
        }
        if(ok(nums, k, from)) {
            ret = from;
        }
        return ret;
    }

    private boolean ok(int[] nums, int k, int limit) {
        int chunk = 0;
        int count = 0;
        for(int i=0;i<nums.length;i++) {
            if(nums[i] <= limit) {
                chunk++;
            } else {
                count += chunk % 2 == 0? (chunk / 2) : (chunk / 2 + 1);
                chunk = 0;
            }
        }

        count += chunk % 2 == 0? (chunk / 2) : (chunk / 2 + 1);

        return count >= k;
    }
}
