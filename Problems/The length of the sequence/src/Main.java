import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scn = new Scanner(System.in);
        int count = 0;
        int number = -1;
        while (scn.hasNext()) {
            number = scn.nextInt();
            if (number == 0) {
                break;
            } else {
                count++;
            }
        }
        System.out.print(count);
    }
}