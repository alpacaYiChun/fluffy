public class C2559 {
    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length;
        int[] accu = new int[n];
        int s = 0;
        int i = 0;
        for(String str : words) {
            var first = str.charAt(0);
            var last = str.charAt(str.length()-1);
            if(is(first) && is(last)) {
                s++;
            }
            accu[i++] = s;
        }
        int j = 0;
        int[] ret = new int[queries.length];
        for (int[] q : queries) {
            int from = q[0];
            int to = q[1];
            int f = from == 0? 0: accu[from - 1];
            int l = accu[to];
            ret[j++] = l - f;
        }

        return ret;
    }

    private boolean is(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
