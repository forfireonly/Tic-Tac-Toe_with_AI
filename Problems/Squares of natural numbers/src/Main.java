import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scn = new Scanner(System.in);
        int numberN = scn.nextInt();
        int number = 1;
        while (number * number <= numberN) {
            System.out.println(number * number);
            number++;
        }
    }
}