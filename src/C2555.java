import java.util.ArrayList;

public class C2555 {
    public int maximizeWin(int[] prizePositions, int k) {
        var list = new ArrayList<int[]>();
        int last = -1;
        int count = 0;
        for(int i = 0;i<prizePositions.length;i++) {
            int val = prizePositions[i];
            if(val != last) {
                if(last != -1) {
                    list.add(new int[]{last, count});
                }
                last = val;
                count = 1;
            } else {
                count++;
            }
        }
        list.add(new int[]{last, count});

        int from = 0;
        int to = 0;
        // from ... to - 1
        int interval = 0;
        int[][] t = new int[list.size()][2];
        while(to < list.size()) {
            int start = list.get(from)[0];
            int end = list.get(to)[0];
            int gap = end - start + 1;
            if(gap > k) {
                t[from] = new int[]{to - 1, interval};
                interval -= list.get(from)[1];
                ++from;
            } else {
                interval += list.get(to)[1];
                ++to;
            }
        }
        while(from < list.size()) {
            t[from] = new int[]{to - 1, interval};
            interval -= list.get(from)[1];
            ++from;
        }

        int[] mm = new int[list.size()];
        mm[list.size()-1] = list.get(list.size()-1)[1];
        int now = mm[list.size()-1];
        for(int i = list.size()-2;i>=0;i--) {
            now = Math.max(now, list.get(i)[1]);
            mm[i] = now;
        }

        int max = 0;

        for(int i=0;i<list.size();i++) {
            int end = t[i][0];
            int val1 = t[i][1];
            int val2 = 0;
            if(end < list.size() - 1) {
                val2 = mm[end + 1];
            }
            int c = val1 + val2;
            max = Math.max(c, max);
        }

        return max;
    }
}
