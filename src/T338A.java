import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class T338A {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] box = new int[n];
        for(int i=0;i<n;i++) {
            box[i] = scanner.nextInt();
        }
        Arrays.sort(box);
        PriorityQueue<Integer> funk = new PriorityQueue<>();
        for(int i=0;i<n;i++) {
            int w = box[i];
            if(!funk.isEmpty()) {
                int top = funk.poll();
                if(top<=w) {
                    funk.add(top + 1);
                } else {
                    funk.add(top);
                    funk.add(1);
                }
            } else {
                funk.add(1);
            }
        }

        System.out.println(funk.size());
    }
}
