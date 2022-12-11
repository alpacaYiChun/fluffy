import java.util.*;

public class T520B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int init = scanner.nextInt();
        int target = scanner.nextInt();

        if(init == target) {
            System.out.println(0);
            return;
        }

        System.out.println(ping(init, target));
    }

    private static int ping(int init, int target) {
        HashSet<Integer> already = new HashSet<>();
        LinkedList<Integer> queue = new LinkedList<>();
        LinkedList<Integer> step = new LinkedList<>();
        already.add(init);
        queue.add(init);
        step.add(0);

        while(!queue.isEmpty()) {
            int fetch = queue.poll();
            int dist = step.poll();
            List<Integer> possible = new ArrayList<>(2);
            if(fetch<target) {
                possible.add(fetch*2);
            }
            if(fetch>1) {
                possible.add(fetch-1);
            }
            for(Integer dd : possible) {
                if(dd==target) {
                    return dist + 1;
                }
                if(!already.contains(dd)) {
                    already.add(dd);
                    queue.add(dd);
                    step.add(dist+1);
                }
            }
        }

        return -1;
    }
}
