import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class T538B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        var list = new ArrayList<Integer>();
        while(n!=0) {
            list.add(n%10);
            n/=10;
        }
        var ret = new ArrayList<Integer>();
        while(!clean(list)) {
            int temp = 0;
            for(int i=list.size()-1;i>=0;i--) {
                temp *= 10;
                if(list.get(i)>0) {
                    temp += 1;
                    list.set(i, list.get(i) - 1);
                }
            }
            ret.add(temp);
        }

        StringBuilder sb= new StringBuilder();
        boolean start = true;
        for(Integer r : ret) {
            if(start) {
                start = false;
            } else {
                sb.append(" ");
            }
            sb.append(r);
        }
        System.out.println(ret.size());
        System.out.println(sb.toString());
    }

    private static boolean clean(List<Integer> digits) {
        for(Integer i : digits) {
            if(i!=0) {
                return false;
            }
        }
        return true;
    }
}
