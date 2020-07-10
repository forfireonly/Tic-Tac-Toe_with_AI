import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scn = new Scanner(System.in);
        int number = scn.nextInt();
        if (number < 1) {
            System.out.println("no army");
        } else if (number <= 19) {
            System.out.println("pack");
        } else if (number <= 249) {
            System.out.println("throng");
        } else if (number <= 999) {
            System.out.println("zounds");
        } else {
            System.out.println("legion");
        }
    }
}