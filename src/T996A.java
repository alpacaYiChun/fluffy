import java.util.Scanner;

public class T996A {
    //1 , 5, 10, 20, 100

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int p = scanner.nextInt();
        System.out.println(change(p));
    }

    private static int change(int amount) {
        int t100 = amount / 100;
        amount %= 100;
        int t20 = amount / 20;
        amount %= 20;
        int t10 = amount / 10;
        amount %= 10;
        int t5 = amount / 5;
        amount %= 5;

        return t100+t20+t10+t5+amount;
    }
}
