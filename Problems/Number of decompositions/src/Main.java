import java.util.Scanner;
class Main {
    static StringBuilder decom = new StringBuilder();
    public static void main(String[] args) {
        // put your code here

        Scanner scn = new Scanner(System.in);
        int number = scn.nextInt();
        partition(number);
        String myDecom = String.valueOf(decom);
        String[] myDecomArray = myDecom.split(" {2}");
        for (int i = myDecomArray.length - 1; i >= 0; i--) {
            System.out.println(myDecomArray[i]);
        }

    }

    public static void partition(int n) {
        partition(n, n, "");

    }
    public static void partition(int n, int max, String prefix) {

        if (n == 0) {
            decom.append(prefix).append(" ");
            return;
        }

        for (int i = Math.min(max, n); i >= 1; i--) {
            partition(n - i, i, prefix + " " + i);
        }
    }

}