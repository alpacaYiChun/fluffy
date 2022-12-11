import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class T534B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int v1 = scanner.nextInt();
        int v2 = scanner.nextInt();
        int t = scanner.nextInt();
        int d = scanner.nextInt();
        if(d==0) {
            System.out.println(v1 * t);
            return;
        }
        int from = Math.max(v1, v2);
        int to = from + (t-1) * d;
        int max = -1;
        while(from != to) {
            int mid = (from + to) >> 1;
            if(ok(mid, v1, v2, d, t)) {
                max = mid;
                from = mid + 1;
            } else {
                to = mid;
            }
        }
        if(ok(from,v1,v2,d,t)) {
            max = from;
        }

        List<Integer> steps = new ArrayList<>();
        steps.add(v1);
        int now = v1;
        while(now!=max) {
            if(now+d<=max) {
                now+=d;
            } else {
                now = max;
            }
            steps.add(now);
        }
        steps.add(v2);
        now = v2;
        while(now!=max) {
            if(now+d<=max) {
                now += d;
            } else {
                now = max;
            }
            steps.add(now);
        }

        int ret = 0;
        for(int i=0;i<steps.size();i++) {
            ret+=steps.get(i);
        }

        if(steps.size()>t) {
            ret -= max;
        }

        System.out.println(ret);
    }

    public static boolean ok(int max, int v1, int v2, int d, int t) {
        int a = (max-v1)/d;
        if((max-v1)%d!=0) {
            ++a;
        }
        int b = (max-v2)/d;
        if((max-v2)%d!=0) {
            ++b;
        }
        int need = a+b;
        return need <= t-1;
    }
}
