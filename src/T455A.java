import java.util.*;

public class T455A {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        HashMap<Integer, Long> count = new HashMap<>();
        for(int i=0;i<n;i++) {
            int num = scanner.nextInt();
            long already = 0;
            if(count.containsKey(num)) {
                already = count.get(num);
            }
            count.put(num, already + 1);
        }
        List<Map.Entry<Integer, Long>> list = new ArrayList<>();
        list.addAll(count.entrySet());
        Collections.sort(list, (a, b) -> a.getKey() - b.getKey());
        int len = list.size();
        long[] op = new long[len + 1];
        op[0] = 0;
        op[1] = list.get(0).getKey() * list.get(0).getValue();
        for(int i=2;i<=len;i++) {
            long v1 = op[i-1];
            long v2 = 0;
            int now = list.get(i-1).getKey();
            int prev = list.get(i-2).getKey();
            long nowCount = list.get(i-1).getValue();
            if(now == prev + 1) {
                v2 = op[i - 2] + now * nowCount;
            } else {
                v2 = op[i - 1] + now * nowCount;
            }
            op[i] = Math.max(v1, v2);
        }
        System.out.println(op[len]);
    }
}
