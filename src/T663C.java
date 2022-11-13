import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class T663C {
    private static class P {
        char c;
        int count;
        public P(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner((System.in));
        String line = scanner.nextLine();
        char[] ar = line.toCharArray();
        int n = ar.length;
        List<P> list = new ArrayList<>();
        char now = ar[0];
        int count = 1;
        for(int i=1;i<n;i++) {
            if(ar[i] != now) {
                list.add(new P(now, count));
                count = 1;
                now = ar[i];
            } else {
                ++count;
            }
        }
        list.add(new P(now, count));
        StringBuilder sb = new StringBuilder();
        for(int k=0;k<list.size();k++) {
            P p = list.get(k);
            int idx = p.c - 'a';
            int diff = -1;
            for(int inc = 1; inc <= 25; inc++) {
                int pdx = (idx + inc)%26;
                int next = -1;
                if(k<list.size()-1) {
                    next = list.get(k+1).c - 'a';
                }
                if(pdx != next) {
                    diff = pdx;
                    break;
                }
            }

            char f = (char)(diff+'a');
            for(int i=0;i<p.count;i++) {
                if(i%2==0) {
                   sb.append(p.c);
                } else {
                    sb.append(f);
                }
            }
        }
        System.out.println(sb.toString());
    }
}
