import java.util.Scanner;
import java.util.Stack;

public class T190C {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        var waste = scanner.nextLine();
        var input = scanner.nextLine();
        String[] ar = input.split(" ");
        var rs = m(ar);
        System.out.println(rs);
    }

    private static class P {
        String val;
        boolean good;

        public P(String val, boolean good) {
            this.val = val;
            this.good = good;
        }

        public String get() {
            return val;
        }
    }

    private static String m(String[] ar) {
        Stack<P> s = new Stack<>();

        for (String str : ar) {
            if (str.equals("int")) {
                s.push(new P("int", true));
            } else {
                s.push(new P("pair", false));
            }

            boolean happen = false;
            do {
                happen = false;
                P m3 = null;
                P m2 = null;
                P m1 = null;
                m1 = s.peek();
                if (s.size() > 1) {
                    m2 = s.get(s.size() - 2);
                }
                if (s.size() > 2) {
                    m3 = s.get(s.size() - 3);
                }
                if (m3 != null && !m3.good && m2.good && m1.good) {
                    var construct = "pair<" + m2.get() + "," + m1.get() + ">";
                    s.pop();
                    s.pop();
                    s.pop();
                    s.push(new P(construct, true));
                    happen = true;
                }
            } while(happen);
        }

        if(s.size()==1&&s.peek().good) {
            return s.peek().get();
        }

        return "Error occurred";
    }
}
