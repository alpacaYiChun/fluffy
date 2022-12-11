import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class T546C {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n= scanner.nextInt();
        LinkedList<Integer> first = new LinkedList<>();
        LinkedList<Integer> second = new LinkedList<>();
        int k1 = scanner.nextInt();
        for(int i=0;i<k1;i++) {
            first.add(scanner.nextInt());
        }
        int k2 = scanner.nextInt();
        for(int i=0;i<k2;i++) {
            second.add(scanner.nextInt());
        }
        HashMap<Integer, HashSet<Integer>> record = new HashMap<Integer, HashSet<Integer>>();

    }

    private static int mask(LinkedList<Integer> q) {
        return 0;
    }
}
