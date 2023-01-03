import java.util.Scanner;

public class T335A {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int n = scanner.nextInt();
        char[] ar = s.toCharArray();
        int[] count = new int[26];
        for(int i=0;i<ar.length;i++) {
            int idx = ar[i] - 'a';
            count[idx]++;
        }
        int len = ar.length;
        int from = 1;
        int to = len;
        int good = -1;
        while(from!=to) {
            int mid = (from + to) >> 1;
            if(ok(count, mid, n)) {
                good = mid;
                to = mid;
            } else {
                from = mid + 1;
            }
        }
        if(ok(count, from, n)) {
            good = from;
        }
        if(good == -1) {
            System.out.println(-1);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<26;i++) {
            int need = count[i] / good;
            if(count[i] % good != 0) {
                ++need;
            }
            for(int j=0;j<need;j++) {
                sb.append((char)('a'+i));
            }
        }
        int already = sb.length();
        for(int k = 0; k<n-already; k++) {
            sb.append('a');
        }
        System.out.println(good);
        System.out.println(sb.toString());
    }

    private static boolean ok(int[] count, int seg, int n) {
        int all = 0;
        for(int i=0;i<26;i++) {
            int need = count[i] / seg;
            if(count[i] % seg != 0) {
                ++need;
            }
            all += need;
            if(all > n) {
                return false;
            }
        }
        return true;
    }
}
