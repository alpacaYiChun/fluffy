import java.util.*;

public class T515B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int boy = scanner.nextInt();
        int girl = scanner.nextInt();
        int happyBoy = scanner.nextInt();
        List<Integer> happyBoys = new ArrayList<>();
        for(int i=0;i<happyBoy;i++) {
            int b = scanner.nextInt();
            happyBoys.add(b);
        }
        int happyGirl = scanner.nextInt();
        List<Integer> happyGirls = new ArrayList<>();
        for(int i=0;i<happyGirl;i++) {
            int g = scanner.nextInt();
            happyGirls.add(g);
        }
        List<HashSet<Integer>> boyToGirl = new ArrayList<>();
        List<HashSet<Integer>> girlToBoy = new ArrayList<>();
        for(int i=0;i<boy;i++) {
            boyToGirl.add(new HashSet<>());
        }
        for(int i=0;i<girl;i++) {
            girlToBoy.add(new HashSet<>());
        }
        for(int b = 0; b< boy; b++) {
            int now = b%girl;
            while(!boyToGirl.get(b).contains(now)) {
                boyToGirl.get(b).add(now);
                girlToBoy.get(now).add(b);
                now = (now + boy) % girl;
            }
        }
        boolean[] checkBoys = new boolean[boy];
        boolean[] checkGirls = new boolean[girl];
        LinkedList<int[]> queue = new LinkedList<int[]>();
        for(Integer hb : happyBoys) {
            checkBoys[hb] = true;
            queue.add(new int[]{1,hb});
        }
        for(Integer hg : happyGirls) {
            checkGirls[hg] = true;
            queue.add(new int[]{0,hg});
        }
        while(!queue.isEmpty()) {
            int[] fetch = queue.poll();
            if(fetch[0] == 1) {
                int guy = fetch[1];
                for(Integer gg : boyToGirl.get(guy)) {
                    if(!checkGirls[gg]) {
                        checkGirls[gg] = true;
                        queue.add(new int[]{0, gg});
                    }
                }
            } else {
                int gie = fetch[1];
                for(Integer gb : girlToBoy.get(gie)) {
                    if(!checkBoys[gb]) {
                        checkBoys[gb] = true;
                        queue.add(new int[]{1, gb});
                    }
                }
            }
        }

        /*
        for(int i=0;i<checkBoys.length;i++) {
            System.out.print(checkBoys[i]+" ");
        }
        System.out.println();
        for(int i=0;i<checkGirls.length;i++) {
            System.out.print(checkGirls[i]+" ");
        }
        */

        boolean fail = false;
        for(int i=0;i<boy;i++) {
            if(!checkBoys[i]) {
                fail = true;
                break;
            }
        }
        if(!fail) {
            for(int i=0;i<girl;i++) {
                if(!checkGirls[i]) {
                    fail = true;
                    break;
                }
            }
        }

        if(fail) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
    }
}
