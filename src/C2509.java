public class C2509 {
    public static void main(String[] args) {
        C2509 c = new C2509();
        System.out.println(c.common(5,3));
        System.out.println(new C2509().cycleLengthQueries(3, new int[][]{{5,3}, {4, 7}, {2, 3}}));
    }
    public int[] cycleLengthQueries(int n, int[][] queries) {
        int[] ret = new int[queries.length];
        int i = 0;
        for(int[] q : queries) {
            int a = q[0];
            int b = q[1];
            int c = common(a, b) - 1;
            int pathA = bit(a) - 1;
            int pathB = bit(b) - 1;
            ret[i++] = pathA + pathB - 2*c + 1;
        }
        return ret;
    }

    private int common(int a, int b) {
        int i = 31;
        int j = 31;
        while ((a & (1 << i)) == 0) {
            --i;
        }
        while ((b & (1 << j)) == 0) {
            --j;
        }
        //System.out.println(i+","+j);
        int share = 0;
        while (i >= 0 && j >= 0 && ff(a,i,b,j)) {
            ++share;
            --i;
            --j;
        }
        return share;
    }

    private boolean ff(int a, int i, int b, int j) {
        int left = a & (1 << i);
        int right = b & (1 << j);
        return (left == 0 && right == 0) || (left != 0 && right != 0);
    }

    private int bit(int k) {
        int count = 0;
        while(k != 0) {
            count++;
            k = k >> 1;
        }
        return count;
    }
}
