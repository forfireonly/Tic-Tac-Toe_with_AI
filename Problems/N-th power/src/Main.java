import java.util.Scanner;

public class Main {

    public static double pow(double a, long n) {

        // write your code here
        if (a == 0.0) {
            return 0;
        } else if (a == 1.0) {
            return 1.0;
        } else if (n == 0) {
            return 1.0;
        } else if (n == 1) {
            return a;
        } else if (n % 2 == 0) {
            double y = pow(a, n / 2);
            return y * y;
        } else {
            return a * pow(a, n - 1);
        }
    }

    /* Do not change code below */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final double a = Double.parseDouble(scanner.nextLine());
        final int n = Integer.parseInt(scanner.nextLine());
        System.out.println(pow(a, n));
    }
}