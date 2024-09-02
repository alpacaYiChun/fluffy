public class C2730 {
    public int longestSemiRepetitiveSubstring(String s) {
        char[] ar = s.toCharArray();
        int n = ar.length;
        int lastRep = -1;
        boolean has = false;
        int begin = 0;
        int ret = 1;
        for(int i=1;i<n;i++) {
            if(ar[i] == ar[i-1]) {
                if(has) {
                    begin = lastRep + 1;
                } else {
                    has = true;
                }
                lastRep = i - 1;
            }
            int interval = i - begin + 1;
            if(interval > ret) {
                ret = interval;
            }
        }
        int last = n - begin;
        if(last > ret) {
            ret = last;
        }
        return ret;
    }
}
