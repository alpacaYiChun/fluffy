public class C2585 {
    private static final long BIG = 1000000007;
    public int waysToReachTarget(int target, int[][] types) {
        long[] op = new long[target + 1];
        op[0] = 1;
        for(int[] type : types) {
            int mark = type[1];
            int amount = type[0];
            for (int i = target; i >= 0; i--) {
                long now = op[i];
                for (int j = 1; j <= amount && j * mark <= i; j++) {
                    now += op[i - mark * j];
                    now %= BIG;
                }
                op[i] = now;
            }
        }

        return (int)op[target];
    }
}
