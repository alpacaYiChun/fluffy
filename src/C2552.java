public class C2552 {
    public long countQuadruplets(int[] nums) {

    }

    private void fenwickUpdate(long[] fenwick, int index, int n) {
        int now = index;
        while(now <= n) {
            fenwick[now]++;
            now = getParent(now);
        }
    }

    private int getParent(int index) {
        int strip = index & (index - 1);
        int lastBit = index ^ strip;
        return index + lastBit;
    }

    private long sum(long[] f, int index) {
        int now = index;
        long ret = 0;
        while(now != 0) {
            ret += f[now];
            now = now & (now - 1);
        }
        return ret;
    }
}
