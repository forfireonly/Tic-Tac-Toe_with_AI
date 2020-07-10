import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scn = new Scanner(System.in);
        scn.nextLine();
        int min = Integer.MAX_VALUE;
        while (scn.hasNextInt()) {
            int number = scn.nextInt();
            if (number < min) {
                min = number;
            }
        }
        System.out.println(min);
    }
}