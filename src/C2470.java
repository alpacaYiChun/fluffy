import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class C2470 {
    public int subarrayLCM(int[] nums, int k) {
        List<Integer> allPrimes = collectPrime();

        HashMap<Integer, Integer> target = wocao(k, allPrimes);

        int n = nums.length;

        List<HashMap<Integer,Integer>> stat = new ArrayList<>();
        for(int i=0;i<n;i++) {
            stat.add(wocao(nums[i], allPrimes));
        }

        int ret = 0;

        for(int i=0;i<n;i++) {
            HashMap<Integer,Integer> now = new HashMap<>();
            for(int j=i;j<n;j++) {
                lcm(now, stat.get(j));
                System.out.println(i+","+j+":");
                for(Map.Entry<Integer,Integer> e: now.entrySet()) {
                    System.out.println(e.getKey()+"="+e.getValue());
                }
                if(noHope(now, target)) {
                    break;
                }
                if(equal(now, target)) {
                    ++ret;
                }
            }
        }

        return ret;
    }

    private boolean equal(HashMap<Integer, Integer> a, HashMap<Integer, Integer> b) {
        if(a.size()!=b.size()) {
            return false;
        }
        for(Map.Entry<Integer, Integer> e : a.entrySet()) {
            int key = e.getKey();
            int val = e.getValue();
            if(!b.containsKey(key) || b.get(key)!=val) {
                return false;
            }
        }
        return true;
    }
    private boolean noHope(HashMap<Integer, Integer> now, HashMap<Integer, Integer> target) {
        for(Map.Entry<Integer, Integer> e : now.entrySet()) {
            if(!target.containsKey(e.getKey()) || e.getValue() > target.get(e.getKey())) {
                return true;
            }
        }
        return false;
    }

    private void lcm(HashMap<Integer,Integer> origin, HashMap<Integer, Integer> nw) {
        for(Map.Entry<Integer,Integer> e : nw.entrySet()) {
            int key = e.getKey();
            int already = 0;
            if(origin.containsKey(key)) {
                already = origin.get(key);
            }
            int val = e.getValue();
            origin.put(key, Math.max(val, already));
        }
    }

    private HashMap<Integer,Integer> wocao(int t, List<Integer> allPrimes) {
        HashMap<Integer, Integer> ret = new HashMap<>();
        for(Integer prime : allPrimes) {
            if(prime>t) {
                break;
            }
            int c = 0;
            while(t%prime==0) {
                t /= prime;
                ++c;
            }
            if(c>0) {
                ret.put(prime, c);
            }
        }
        return ret;
    }
    private List<Integer> collectPrime() {
        List<Integer> ret = new ArrayList<>();
        for(int i=2;i<=1000;i++) {
            if(isPrime(i)) {
                ret.add(i);
            }
        }
        return ret;
    }

    private boolean isPrime(int n) {
        for(int i=2;i*i<=n;i++) {
            if(n%i==0) {
                return false;
            }
        }
        return true;
    }
}
