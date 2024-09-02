public class C2734 {
    public String smallestString(String s) {
        char[] ar = s.toCharArray();
        int n = ar.length;
        int begin = -1;
        int end = -1;
        for(int i=0;i<n;i++) {
            if(ar[i] != 'a' && begin == -1) {
                begin = i;
            } else if(ar[i] == 'a' && begin != -1) {
                end = i;
                break;
            }
        }
        if(begin != -1 && end == -1) {
            end = n;
        }
        if(begin != -1) {
            for (int i = begin; i < end; i++) {
                ar[i] = (char) (ar[i] - 1);
            }
        } else {
            ar[n-1]='z';
        }
        return new String(ar);
    }
}
