import java.util.ArrayList;

public class C2896 {
    public int minOperations(String s1, String s2, int x) {
        int n = s1.length();
        char[] a1 = s1.toCharArray();
        char[] a2 = s2.toCharArray();
        var list = new ArrayList<Integer>();
        for(int i=0;i<n;i++) {
            if(a1[i]!=a2[i]) {
                list.add(i);
            }
        }
        int n2 = list.size();
        if(n2%2==1) {
            return -1;
        }
        if(n2==0) {
            return 0;
        }
        int raw = 0;
        for(int i=1;i<n2;i+=2) {
            raw += list.get(i) - list.get(i-1);
        }
        int[] op = new int[n2];
        int pivot = -1;
        for(int i=1;i<n2;i+=2) {
            int min = Integer.MAX_VALUE;
            int between = 0;
            int here = -1;
            for(int k=i-1;k>=Math.max(0, pivot);k-=2) {
                //System.out.println("i="+i+",k="+k+",between="+between+",pivot="+pivot);
                int last = k == 0? 0: op[k-1];
                int attempt = last + between + x;
                if (attempt < min) {
                    here = k;
                    min = attempt;
                }
                if(k>=1) {
                    between += list.get(k) - list.get(k-1);
                }

            }
            pivot = here;
            int dry = 0;
            if(i>1) {
                dry = op[i-2];
            }
            dry += list.get(i) - list.get(i-1);
            if(dry < min) {
                min = dry;
            }
            op[i] = min;
        }
        /*
        for(int i=0;i<n2;i++) {
            System.out.print(op[i]+",");
        }
        */
        //System.out.println();
        return Math.min(raw, op[n2-1]);
    }
}
