import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        int counter = 0;
        while (scanner.hasNextInt()) {
            if (scanner.nextInt() > 0) {
                counter++;
            }
        }
        System.out.println(counter == 1);

    }
}