public class C134 {
    public static void main(String[] args) {
        var c = new C134();
        int[] gas = new int[] {1,2,3,4,5};
        int[] cost = new int[] {3,4,5,2,1};
        var rs = c.canCompleteCircuit(gas, cost);
        System.out.println(rs);
    }
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;

        int start = 0;
        int gone = 0;
        int balance = 0;
        int at = 0;
        int ans = -1;

        while(true) {
            balance += gas[at] - cost[at];
            at = (at + 1) % n;
            if (balance < 0) {
                if (at < start) {
                    break;
                }
                start = at;
                balance = 0;
                gone = 0;
            } else {
                gone += 1;
            }
            if (gone == n) {
                System.out.println("xuda");
                ans = start;
                break;
            }
        }
        return ans;
    }
}
