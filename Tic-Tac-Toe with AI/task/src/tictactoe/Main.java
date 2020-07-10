package tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

//abstract class Game implements common methods and each game case has to implement makingMove method
abstract class Game {
    public static String[][] ticTaeToeField = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};
    public static int[] move = new int[2];
    public static int blockinX;
    public static int blockinY;
    public static int score;

    void minMax(int depth, boolean isMaximazer) {
        boolean isFinished;
        isFinished = threeInARowCheckNoPrint();
        if (!isFinished) {
            threeInARowCheckNoPrint();
            if (isMaximazer) {
                int bestScore = Integer.MIN_VALUE;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (" ".equals(ticTaeToeField[i][j])) {
                            ticTaeToeField[i][j] = "X";
                            minMax(depth + 1, false);
                            ticTaeToeField[i][j] = " ";
                            if (score > bestScore) {
                                bestScore = score;
                            }
                        }
                    }
                }
            } else {
                int bestScore = Integer.MAX_VALUE;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (" ".equals(ticTaeToeField[i][j])) {
                            ticTaeToeField[i][j] = "O";
                            minMax(depth + 1, true);
                            ticTaeToeField[i][j] = " ";
                            if (score < bestScore) {
                                bestScore = score;
                            }
                        }
                    }
                }
            }
        }
    }

    void drawField() {
        System.out.println("---------");
        System.out.printf("| %s %s %s |", ticTaeToeField[0][0], ticTaeToeField[0][1], ticTaeToeField[0][2]);
        System.out.println();
        System.out.printf("| %s %s %s |", ticTaeToeField[1][0], ticTaeToeField[1][1], ticTaeToeField[1][2]);
        System.out.println();
        System.out.printf("| %s %s %s |", ticTaeToeField[2][0], ticTaeToeField[2][1], ticTaeToeField[2][2]);
        System.out.println();
        System.out.println("---------");

    }

    static boolean checkInput(String coordinates) {
        String[][] correctionMatrix = new String[3][3];
        correctionMatrix[0][0] = "1 3";
        correctionMatrix[0][1] = "2 3";
        correctionMatrix[0][2] = "3 3";
        correctionMatrix[1][0] = "1 2";
        correctionMatrix[1][1] = "2 2";
        correctionMatrix[1][2] = "3 2";
        correctionMatrix[2][0] = "1 1";
        correctionMatrix[2][1] = "2 1";
        correctionMatrix[2][2] = "3 1";
        int adjustedX = 0;
        int adjustedY = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (coordinates.equals(correctionMatrix[i][j])) {
                    adjustedX = i;
                    adjustedY = j;
                }
            }
        }
        String[] coordinatesArrayString = coordinates.split(" ");
        int x = 0;
        int y = 0;
        boolean isCorrect = false;
        try {
            x = Integer.parseInt(coordinatesArrayString[0]);
            y = Integer.parseInt(coordinatesArrayString[1]);
            isCorrect = true;
            if (x < 1 || y < 1 || x > 3 || y > 3) {
                System.out.print("Coordinates should be from 1 to 3!");
                isCorrect = false;
            } else if (!" ".equals(ticTaeToeField[adjustedX][adjustedY])) {
                System.out.println("This cell is occupied! Choose another one!");
                isCorrect = false;
            } else {
                move[0] = adjustedX;
                move[1] = adjustedY;
            }
        } catch (NumberFormatException e) {
            System.out.print("You should enter numbers!");
        }

        return isCorrect;
    }

    public boolean threeInARowCheck() {
        score = 0;
        boolean isFinished = false;
        if (ticTaeToeField[0][0].equals(ticTaeToeField[0][1]) && ticTaeToeField[0][1].equals(ticTaeToeField[0][2])
                && !" ".equals(ticTaeToeField[0][0])) {
            System.out.println(ticTaeToeField[0][0] + " wins");
            score = ticTaeToeField[0][0].equals("X") ? 1  : -1;
            isFinished = true;
        }else if (ticTaeToeField[1][0].equals(ticTaeToeField[1][1]) && ticTaeToeField[1][1].equals(ticTaeToeField[1][2])
                && !" ".equals(ticTaeToeField[1][0])) {
            System.out.println(ticTaeToeField[1][0] + " wins");
            score = ticTaeToeField[1][0].equals("X") ? 1  : -1;
            isFinished = true;
        }else if (ticTaeToeField[2][0].equals(ticTaeToeField[2][1]) && ticTaeToeField[2][1].equals(ticTaeToeField[2][2]) &&
                !" ".equals(ticTaeToeField[2][0])) {
            System.out.println(ticTaeToeField[2][0] + " wins");
            score = ticTaeToeField[2][0].equals("X") ? 1  : -1;
            isFinished = true;
        }else if (ticTaeToeField[0][0].equals(ticTaeToeField[1][0]) && ticTaeToeField[1][0].equals(ticTaeToeField[2][0]) &&
                !" ".equals(ticTaeToeField[0][0])) {
            System.out.println(ticTaeToeField[0][0] + " wins");
            score = ticTaeToeField[0][0].equals("X") ? 1  : -1;
            isFinished = true;
        }else if (ticTaeToeField[0][1].equals(ticTaeToeField[1][1]) && ticTaeToeField[1][1].equals(ticTaeToeField[2][1]) &&
                !" ".equals(ticTaeToeField[0][1])) {
            System.out.println(ticTaeToeField[0][1] + " wins");
            score = ticTaeToeField[0][1].equals("X") ? 1  : -1;
            isFinished = true;
        }else if (ticTaeToeField[0][2].equals(ticTaeToeField[1][2]) && ticTaeToeField[1][2].equals(ticTaeToeField[2][2]) &&
                !" ".equals(ticTaeToeField[0][2])) {
            System.out.println(ticTaeToeField[0][2] + " wins");
            score = ticTaeToeField[0][2].equals("X") ? 1  : -1;
            isFinished = true;
        }else if (ticTaeToeField[0][0].equals(ticTaeToeField[1][1]) && ticTaeToeField[1][1].equals(ticTaeToeField[2][2]) &&
                !" ".equals(ticTaeToeField[0][0])) {
            System.out.println(ticTaeToeField[0][0] + " wins");
            score = ticTaeToeField[0][0].equals("X") ? 1  : -1;
            isFinished = true;

        }else if (ticTaeToeField[0][2].equals(ticTaeToeField[1][1]) && ticTaeToeField[1][1].equals(ticTaeToeField[2][0]) &&
                !" ".equals(ticTaeToeField[0][2])) {
            System.out.println(ticTaeToeField[2][0] + " wins");
            score = ticTaeToeField[0][2].equals("X") ? 1  : -1;
            isFinished = true;
        }
        return isFinished;
    }

    public boolean threeInARowCheckNoPrint() {
        score = 0;
        boolean isFinished = false;
        if (ticTaeToeField[0][0].equals(ticTaeToeField[0][1]) && ticTaeToeField[0][1].equals(ticTaeToeField[0][2])
                && !" ".equals(ticTaeToeField[0][0])) {
            //System.out.println(ticTaeToeField[0][0] + " wins");
            if (ticTaeToeField[0][0].equals("X")) {
                score = 1;
            } else {
                score = -1;
            }
            isFinished = true;
        } else if (ticTaeToeField[1][0].equals(ticTaeToeField[1][1]) && ticTaeToeField[1][1].equals(ticTaeToeField[1][2])
                && !" ".equals(ticTaeToeField[1][0])) {
            //System.out.println(ticTaeToeField[1][0] + " wins");
            if (ticTaeToeField[1][0].equals("X")) {
                score = 1;
            } else {
                score = -1;
            }
            isFinished = true;
        }else if (ticTaeToeField[2][0].equals(ticTaeToeField[2][1]) && ticTaeToeField[2][1].equals(ticTaeToeField[2][2]) &&
                !" ".equals(ticTaeToeField[2][0])) {
            //System.out.println(ticTaeToeField[2][0] + " wins");
            if (ticTaeToeField[2][0].equals("X")) {
                score = 1;
            } else {
                score = -1;
            }
            isFinished = true;
        }else if (ticTaeToeField[0][0].equals(ticTaeToeField[1][0]) && ticTaeToeField[1][0].equals(ticTaeToeField[2][0]) &&
                !" ".equals(ticTaeToeField[0][0])) {
            //System.out.println(ticTaeToeField[0][0] + " wins");
            if (ticTaeToeField[0][0].equals("X")) {
                score = 1;
            } else {
                score = -1;
            }
            isFinished = true;
        }else if (ticTaeToeField[0][1].equals(ticTaeToeField[1][1]) && ticTaeToeField[1][1].equals(ticTaeToeField[2][1]) &&
                !" ".equals(ticTaeToeField[0][1])) {
           // System.out.println(ticTaeToeField[0][1] + " wins");
            if (ticTaeToeField[0][1].equals("X")) {
                score = 1;
            } else {
                score = -1;
            }

            isFinished = true;
        }else if (ticTaeToeField[0][2].equals(ticTaeToeField[1][2]) && ticTaeToeField[1][2].equals(ticTaeToeField[2][2]) &&
                !" ".equals(ticTaeToeField[0][2])) {
            //System.out.println(ticTaeToeField[0][2] + " wins");
            if (ticTaeToeField[0][2].equals("X")) {
                score = 1;
            } else {
                score = -1;
            }

            isFinished = true;
        }else if (ticTaeToeField[0][0].equals(ticTaeToeField[1][1]) && ticTaeToeField[1][1].equals(ticTaeToeField[2][2]) &&
                !" ".equals(ticTaeToeField[0][0])) {
            //System.out.println(ticTaeToeField[0][0] + " wins");
            if (ticTaeToeField[0][0].equals("X")) {
                score = 1;
            } else {
                score = -1;
            }

            isFinished = true;

        }else if (ticTaeToeField[0][2].equals(ticTaeToeField[1][1]) && ticTaeToeField[1][1].equals(ticTaeToeField[2][0]) &&
                !" ".equals(ticTaeToeField[0][2])) {
            //System.out.println(ticTaeToeField[2][0] + " wins");
            if (ticTaeToeField[0][2].equals("X")) {
                score = 1;
            } else {
                score = -1;
            }
            isFinished = true;
        }
        return isFinished;
    }

    abstract public void makingMove();

    public boolean nextWinningMove(String winningMove) {
        boolean isEmpty = true;
        if (ticTaeToeField[0][0].equals(ticTaeToeField[0][1]) && winningMove.equals(ticTaeToeField[0][0])
                && !" ".equals(ticTaeToeField[0][0]) && " ".equals(ticTaeToeField[0][2])) {
                ticTaeToeField[0][2] = winningMove;
                isEmpty = false;
                blockinX = 0;
                blockinY = 2;
        } else if (ticTaeToeField[0][0].equals(ticTaeToeField[0][2]) && winningMove.equals(ticTaeToeField[0][0])
                && !" ".equals(ticTaeToeField[0][0])&& " ".equals(ticTaeToeField[0][1])) {
            ticTaeToeField[0][1] = winningMove;
            isEmpty = false;
            blockinX = 0;
            blockinY = 1;
        } else if (ticTaeToeField[0][1].equals(ticTaeToeField[0][2]) && winningMove.equals(ticTaeToeField[0][1])
                && !" ".equals(ticTaeToeField[0][1])&& " ".equals(ticTaeToeField[0][0])) {
            ticTaeToeField[0][0] = winningMove;
            isEmpty = false;
            blockinX = 0;
            blockinY = 0;
        } else if (ticTaeToeField[1][0].equals(ticTaeToeField[1][1]) && winningMove.equals(ticTaeToeField[1][0])
                && !" ".equals(ticTaeToeField[1][0])&& " ".equals(ticTaeToeField[1][2])) {
            ticTaeToeField[1][2] = winningMove;
            isEmpty = false;
            blockinX = 1;
            blockinY = 2;
        } else if (ticTaeToeField[1][0].equals(ticTaeToeField[1][2]) && winningMove.equals(ticTaeToeField[1][0])
                && !" ".equals(ticTaeToeField[1][0])&& " ".equals(ticTaeToeField[1][1])) {
            ticTaeToeField[1][1] = winningMove;
            isEmpty = false;
            blockinX = 1;
            blockinY = 1;
        } else if (ticTaeToeField[1][1].equals(ticTaeToeField[1][2]) && winningMove.equals(ticTaeToeField[1][1])
                && !" ".equals(ticTaeToeField[1][1])&& " ".equals(ticTaeToeField[1][0])) {
            ticTaeToeField[1][0] = winningMove;
            isEmpty = false;
            blockinX = 1;
            blockinY = 0;
        } else if (ticTaeToeField[2][0].equals(ticTaeToeField[2][1]) && winningMove.equals(ticTaeToeField[2][0])
                && !" ".equals(ticTaeToeField[2][0])&& " ".equals(ticTaeToeField[2][2])) {
            ticTaeToeField[2][2] = winningMove;
            isEmpty = false;
            blockinX = 2;
            blockinY = 2;
        } else if (ticTaeToeField[2][0].equals(ticTaeToeField[2][2]) && winningMove.equals(ticTaeToeField[2][0])
                && !" ".equals(ticTaeToeField[2][0])&& " ".equals(ticTaeToeField[2][1])) {
            ticTaeToeField[2][1] = winningMove;
            isEmpty = false;
            blockinX = 2;
            blockinY = 1;
        } else if (ticTaeToeField[2][1].equals(ticTaeToeField[2][2]) && winningMove.equals(ticTaeToeField[2][1])
                && !" ".equals(ticTaeToeField[2][1])&& " ".equals(ticTaeToeField[2][0])) {
            ticTaeToeField[2][0] = winningMove;
            isEmpty = false;
            blockinX = 2;
            blockinY = 0;
        } else if (ticTaeToeField[0][0].equals(ticTaeToeField[1][0]) && winningMove.equals(ticTaeToeField[0][0])
                && !" ".equals(ticTaeToeField[0][0])&& " ".equals(ticTaeToeField[2][0])) {
            ticTaeToeField[2][0] = winningMove;
            isEmpty = false;
            blockinX = 2;
            blockinY = 0;
        } else if (ticTaeToeField[0][0].equals(ticTaeToeField[2][0]) && winningMove.equals(ticTaeToeField[0][0])
                && !" ".equals(ticTaeToeField[0][0])&& " ".equals(ticTaeToeField[1][0])) {
            ticTaeToeField[1][0] = winningMove;
            isEmpty = false;
            blockinX = 1;
            blockinY = 0;
        } else if (ticTaeToeField[1][0].equals(ticTaeToeField[2][0]) && winningMove.equals(ticTaeToeField[1][0])
                && !" ".equals(ticTaeToeField[1][0])&& " ".equals(ticTaeToeField[0][0])) {
            ticTaeToeField[0][0] = winningMove;
            isEmpty = false;
            blockinX = 0;
            blockinY = 0;
        } else if (ticTaeToeField[0][1].equals(ticTaeToeField[1][1]) && winningMove.equals(ticTaeToeField[0][1])
                && !" ".equals(ticTaeToeField[0][1])&& " ".equals(ticTaeToeField[2][1])) {
            ticTaeToeField[2][1] = winningMove;
            isEmpty = false;
            blockinX = 2;
            blockinY = 1;
        } else if (ticTaeToeField[0][1].equals(ticTaeToeField[2][1]) && winningMove.equals(ticTaeToeField[0][1])
                && !" ".equals(ticTaeToeField[0][1])&& " ".equals(ticTaeToeField[1][1])) {
            ticTaeToeField[1][1] = winningMove;
            isEmpty = false;
            blockinX = 1;
            blockinY = 1;
        } else if (ticTaeToeField[1][1].equals(ticTaeToeField[2][1]) && winningMove.equals(ticTaeToeField[1][1])
                && !" ".equals(ticTaeToeField[1][1]) && " ".equals(ticTaeToeField[0][1])) {
            ticTaeToeField[0][1] = winningMove;
            isEmpty = false;
            blockinX = 0;
            blockinY = 1;
        } else if (ticTaeToeField[0][2].equals(ticTaeToeField[1][2]) && winningMove.equals(ticTaeToeField[0][2])
                && !" ".equals(ticTaeToeField[0][2]) && " ".equals(ticTaeToeField[2][2])) {
            ticTaeToeField[2][2] = winningMove;
            isEmpty = false;
            blockinX = 2;
            blockinY = 2;
        } else if (ticTaeToeField[0][2].equals(ticTaeToeField[2][2]) && winningMove.equals(ticTaeToeField[0][2])
                && !" ".equals(ticTaeToeField[0][2])&& " ".equals(ticTaeToeField[1][2])) {
            ticTaeToeField[1][2] = winningMove;
            isEmpty = false;
            blockinX = 1;
            blockinY = 2;
        } else if (ticTaeToeField[1][2].equals(ticTaeToeField[2][2]) && winningMove.equals(ticTaeToeField[1][2])
                && !" ".equals(ticTaeToeField[1][2])&& " ".equals(ticTaeToeField[0][2])) {
            ticTaeToeField[0][2] = winningMove;
            isEmpty = false;
            blockinX = 0;
            blockinY = 2;
        } else if (ticTaeToeField[0][0].equals(ticTaeToeField[1][1]) && winningMove.equals(ticTaeToeField[0][0])
                && !" ".equals(ticTaeToeField[0][0])&& " ".equals(ticTaeToeField[2][2])) {
            ticTaeToeField[2][2] = winningMove;
            isEmpty = false;
            blockinX = 2;
            blockinY = 2;
        } else if (ticTaeToeField[0][0].equals(ticTaeToeField[2][2]) && winningMove.equals(ticTaeToeField[0][0])
                && !" ".equals(ticTaeToeField[0][0])&& " ".equals(ticTaeToeField[1][1])) {
            ticTaeToeField[1][1] = winningMove;
            isEmpty = false;
            blockinX = 1;
            blockinY = 1;
        } else if (ticTaeToeField[1][1].equals(ticTaeToeField[2][2]) && winningMove.equals(ticTaeToeField[1][1])
                && !" ".equals(ticTaeToeField[1][1]) && " ".equals(ticTaeToeField[0][0])) {
            ticTaeToeField[0][0] = winningMove;
            isEmpty = false;
            blockinX = 0;
            blockinY = 0;
        } else if (ticTaeToeField[0][2].equals(ticTaeToeField[1][1]) && winningMove.equals(ticTaeToeField[0][2])
                && !" ".equals(ticTaeToeField[0][2])&& " ".equals(ticTaeToeField[2][0])) {
            ticTaeToeField[2][0] = winningMove;
            isEmpty = false;
            blockinX = 2;
            blockinY = 0;
        } else if (ticTaeToeField[0][2].equals(ticTaeToeField[2][0]) && winningMove.equals(ticTaeToeField[0][2])
                && !" ".equals(ticTaeToeField[0][2])&& " ".equals(ticTaeToeField[1][1])) {
            ticTaeToeField[1][1] = winningMove;
            isEmpty = false;
            blockinX = 1;
            blockinY = 1;
        } else if (ticTaeToeField[2][0].equals(ticTaeToeField[1][1]) && winningMove.equals(ticTaeToeField[2][0])
                && !" ".equals(ticTaeToeField[2][0])&& " ".equals(ticTaeToeField[0][2])) {
            ticTaeToeField[0][2] = winningMove;
            isEmpty = false;
            blockinX = 0;
            blockinY = 2;
        }
        return isEmpty;
    }


}

class EasyEasy extends Game {

    @Override
    public void makingMove() {
        boolean isX = true;
        Random rand = new Random();
        int x = 0;
        int y = 0;
        boolean isFinished = false;
        drawField();
        int numberOfMoves = 0;
        while (numberOfMoves < 9){
            //drawField();
            System.out.println("Making move level \"easy\"");

            do {
                x = rand.nextInt(3);
                y = rand.nextInt(3);
            } while (!" ".equals(ticTaeToeField[x][y]));
            if (isX) {
                ticTaeToeField[x][y] = "X";
                isX = false;
            } else {
                ticTaeToeField[x][y] = "O";
                isX = true;
            }

            drawField();
            if (threeInARowCheck()) {
                break;
            }

            numberOfMoves++;
        }
        if (numberOfMoves == 9) {
            System.out.println("Draw");
        }

    }
}


class UserEasy extends Game {

    @Override
    public void makingMove() {
        boolean isCorrect;
        int countOfMoves = 0;
        Scanner scn = new Scanner(System.in);
        while(countOfMoves < 9) {
            isCorrect = false;
            drawField();
            System.out.println("Making move level \"easy\"");
            while (!isCorrect) {
                System.out.print("Enter the coordinates: ");
                String coordinates = scn.nextLine();
                isCorrect = checkInput(coordinates);
            }
            ticTaeToeField[move[0]][move[1]] = "X";
            countOfMoves++;
            if (countOfMoves == 9) {
                break;
            }
            if (threeInARowCheck()) {
                break;
            }
            drawField();
            System.out.println("Making move level \"easy\"");
            threeInARowCheck();
            if (threeInARowCheck()) {
                break;
            }
            Random rand = new Random();
            int x = 0;
            int y = 0;
            do {
                x = rand.nextInt(3);
                y = rand.nextInt(3);
            } while (!" ".equals(ticTaeToeField[x][y]));
            ticTaeToeField[x][y] = "O";
            countOfMoves++;
            threeInARowCheck();
            if (threeInARowCheck()) {
                break;
            }

        }
        if (countOfMoves == 9) {
            System.out.println("Draw");
        }
    }
}

class EasyUser extends Game {

    @Override
    public void makingMove() {
        boolean isCorrect;
        int count = 0;
        boolean isX = true;
        Scanner scn = new Scanner(System.in);
        for (int i = 0; i < 9; i++) {
            isCorrect = false;
            drawField();
            System.out.println("Making move level \"easy\"");
            if (isX) {
                Random rand = new Random();
                int x = 0;
                int y = 0;
                do {
                    x = rand.nextInt(3);
                    y = rand.nextInt(3);
                } while (!" ".equals(ticTaeToeField[x][y]));
                ticTaeToeField[x][y] = "X";
                isX = false;
            } else {
                while (!isCorrect) {
                    System.out.print("Enter the coordinates: ");
                    String coordinates = scn.nextLine();
                    isCorrect = checkInput(coordinates);
                }
                ticTaeToeField[move[0]][move[1]] = "O";
            }
            threeInARowCheck();
            if (threeInARowCheck()) {
                break;
            }
            count++;

        }
        if (count == 9) {
            System.out.println("Draw");
        }
    }
}

class UserUser extends Game {

    @Override
    public void makingMove() {
        boolean isCorrect;
        int count = 0;
        boolean isX = true;
        Scanner scn = new Scanner(System.in);
        int x = 0;
        int y = 0;
        while (count < 9) {
            isCorrect = false;
            while (!isCorrect) {
                System.out.print("Enter the coordinates: ");
                String coordinates = scn.nextLine();
                isCorrect = checkInput(coordinates);
            }
            if (isX) {
                ticTaeToeField[move[0]][move[1]] = "X";
                isX = false;

            } else {
            ticTaeToeField[move[0]][move[1]] = "O";
            isX = true;
            }
            drawField();
            if (threeInARowCheck()) {
                break;
            }

            count++;
        }
        if (count == 9) {
            System.out.println("Draw");
        }
    }
}

class MediumUser extends Game {

    @Override
    public void makingMove() {
        boolean isCorrect;
        int count = 0;
        boolean isX = true;
        Scanner scn = new Scanner(System.in);
        drawField();
        while (count < 9) {
            isCorrect = false;
            if (isX) {
                boolean isEmpty = nextWinningMove("X");
                if (isEmpty) {
                    isEmpty = nextWinningMove("O");
                    if (!isEmpty) {
                        ticTaeToeField[blockinX][blockinY] = "X";
                    }

                }
                if (isEmpty) {
                    Random rand = new Random();
                    int x;
                    int y;
                    do {
                        x = rand.nextInt(3);
                        y = rand.nextInt(3);
                    } while (!" ".equals(ticTaeToeField[x][y]));
                    ticTaeToeField[x][y] = "X";
                }
                    count++;
                    isX = false;
                System.out.println("Making move level \"medium\"");
                drawField();


            } else {
                while (!isCorrect) {
                    System.out.print("Enter the coordinates: ");
                    String coordinates = scn.nextLine();
                    isCorrect = checkInput(coordinates);
                }
                ticTaeToeField[move[0]][move[1]] = "O";
                count++;
                isX = true;
                drawField();
            }
            if (threeInARowCheck()) {
                break;
            }
        }
        if (count == 9) {
            System.out.println("Draw");
        }
    }
}

class UserMedium extends Game {

    @Override
    public void makingMove() {
        boolean isCorrect;
        int countOfMoves = 0;
        Scanner scn = new Scanner(System.in);
        boolean isEmpty;
        while(countOfMoves < 9) {
            isCorrect = false;
            drawField();
            System.out.println("Making move level \"medium\"");
            while (!isCorrect) {
                System.out.print("Enter the coordinates: ");
                String coordinates = scn.nextLine();
                isCorrect = checkInput(coordinates);
            }
            ticTaeToeField[move[0]][move[1]] = "X";
            countOfMoves++;
            if (countOfMoves == 9) {
                break;
            }
            drawField();
            System.out.println("Making move level \"medium\"");
            threeInARowCheck();
            if (threeInARowCheck()) {
                break;
            }
            isEmpty = nextWinningMove("O");
            if (isEmpty) {
                isEmpty = nextWinningMove("X");
                if (!isEmpty) {
                    ticTaeToeField[blockinX][blockinY] = "O";
                }

            }
            if (isEmpty) {
                Random rand = new Random();
                int x = 0;
                int y = 0;
                do {
                    x = rand.nextInt(3);
                    y = rand.nextInt(3);
                } while (!" ".equals(ticTaeToeField[x][y]));
                ticTaeToeField[x][y] = "O";
            }
            countOfMoves++;
            threeInARowCheck();
            if (threeInARowCheck()) {
                break;
            }

        }
        if (countOfMoves == 9) {
            System.out.println("Draw");
        }
    }
}

class MediumMedium extends Game {

    @Override
    public void makingMove() {
        boolean isX = true;
        Random rand = new Random();
        int x = 0;
        int y = 0;
        boolean isEmpty;
        drawField();
        int numberOfMoves = 0;
        while (numberOfMoves <= 9){
            //drawField();
            isEmpty = true;
            System.out.println("Making move level \"medium\"");
            if (isX) {
                isEmpty = nextWinningMove("X");
                if (isEmpty) {
                    isEmpty = nextWinningMove("O");
                    if (!isEmpty) {
                        ticTaeToeField[blockinX][blockinY] = "X";
                    }

                }
                if (isEmpty) {
                    do {
                        x = rand.nextInt(3);
                        y = rand.nextInt(3);
                    } while (!" ".equals(ticTaeToeField[x][y]));
                    {
                        ticTaeToeField[x][y] = "X";
                    }
                }
                isX = false;

            } else {
                isEmpty = nextWinningMove("O");
                if (isEmpty) {
                    isEmpty = nextWinningMove("X");
                    if (!isEmpty) {
                        ticTaeToeField[blockinX][blockinY] = "O";
                    }

                }
                if (isEmpty) {

                    do {
                        x = rand.nextInt(3);
                        y = rand.nextInt(3);
                    } while (!" ".equals(ticTaeToeField[x][y]));
                    {
                        ticTaeToeField[x][y] = "O";
                    }
                }
                isX = true;

            }
            drawField();
            if (threeInARowCheck()) {
                break;
            }
            numberOfMoves++;
            if (numberOfMoves == 9) {
                break;
            }

        }
        if (numberOfMoves == 9) {
            System.out.println("Draw");
        }

    }
}

class MediumEasy extends Game {

    @Override
    public void makingMove() {
        boolean isX = true;
        Random rand = new Random();
        int x = 0;
        int y = 0;
        boolean isEmpty;
        drawField();
        int numberOfMoves = 0;
        while (numberOfMoves <= 9){
            //drawField();
            isEmpty = true;
            System.out.println("Making move level \"medium\"");
            if (isX) {
                isEmpty = nextWinningMove("X");
                if (isEmpty) {
                    isEmpty = nextWinningMove("O");
                    if (!isEmpty) {
                        ticTaeToeField[blockinX][blockinY] = "X";
                    }

                }
                if (isEmpty) {
                    do {
                        x = rand.nextInt(3);
                        y = rand.nextInt(3);
                    } while (!" ".equals(ticTaeToeField[x][y]));
                    {
                        ticTaeToeField[x][y] = "X";
                    }
                }
                isX = false;

            } else {
                    do {
                        x = rand.nextInt(3);
                        y = rand.nextInt(3);
                    } while (!" ".equals(ticTaeToeField[x][y]));
                    {
                        ticTaeToeField[x][y] = "O";
                    }
                isX = true;

            }
            drawField();
            if (threeInARowCheck()) {
                break;
            }
            numberOfMoves++;
            if (numberOfMoves == 9) {
                break;
            }

        }
        if (numberOfMoves == 9) {
            System.out.println("Draw");
        }
    }
}

class EasyMedium extends Game {

    @Override
    public void makingMove() {
        boolean isX = true;
        Random rand = new Random();
        int x = 0;
        int y = 0;
        boolean isEmpty;
        drawField();
        int numberOfMoves = 0;
        while (numberOfMoves <= 9){
            //drawField();
            if (isX) {
                    do {
                        x = rand.nextInt(3);
                        y = rand.nextInt(3);
                    } while (!" ".equals(ticTaeToeField[x][y]));
                    {
                        ticTaeToeField[x][y] = "X";
                    }
                isX = false;

            } else {
                isEmpty = nextWinningMove("X");
                if (isEmpty) {
                    isEmpty = nextWinningMove("O");
                    if (!isEmpty) {
                        ticTaeToeField[blockinX][blockinY] = "X";
                    }

                }
                if (isEmpty) {
                    do {
                        x = rand.nextInt(3);
                        y = rand.nextInt(3);
                    } while (!" ".equals(ticTaeToeField[x][y]));
                    {
                        ticTaeToeField[x][y] = "O";
                    }
                }
                isX = true;

            }
            drawField();
            if (threeInARowCheck()) {
                break;
            }
            numberOfMoves++;
            if (numberOfMoves == 9) {
                break;
            }

        }
        if (numberOfMoves == 9) {
            System.out.println("Draw");
        }

    }
}

class HardUser extends Game {

    @Override
    public void makingMove() {

        int numberOfMoves = 0;
        boolean isCorrect;
        int x = -1;
        int y = -1;
        Scanner scn = new Scanner(System.in);
        while (numberOfMoves <= 9) {
            isCorrect = false;
                System.out.println("Making move level \"hard\"");
                int bestScore = Integer.MIN_VALUE;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (ticTaeToeField[i][j].equals(" ")) {
                            ticTaeToeField[i][j] = "X";
                        //}
                        minMax(0, false);
                        ticTaeToeField[i][j] = " ";
                        if (score > bestScore) {
                            bestScore = score;
                            x = i;
                            y = j;
                        }
                        }
                    }
                }
                ticTaeToeField[x][y] = "X";
                numberOfMoves++;
                drawField();

                if (threeInARowCheck()) {
                    break;
                }
                while (!isCorrect) {
                    System.out.print("Enter the coordinates: ");
                    String coordinates = scn.nextLine();
                    isCorrect = checkInput(coordinates);
                }
                ticTaeToeField[move[0]][move[1]] = "O";
                numberOfMoves++;
                drawField();

            if (threeInARowCheck()) {
                break;
            }
        }
        if (numberOfMoves == 9) {
            System.out.println("Draw");
        }
    }
}

class UserHard extends Game {

    @Override
    public void makingMove() {
        boolean isCorrect;
        int countOfMoves = 0;
        Scanner scn = new Scanner(System.in);
        boolean isEmpty;
        while(countOfMoves <= 9) {
            isCorrect = false;
            drawField();
            System.out.println("Making move level \"hard\"");
            while (!isCorrect) {
                System.out.print("Enter the coordinates: ");
                String coordinates = scn.nextLine();
                isCorrect = checkInput(coordinates);
            }
            ticTaeToeField[move[0]][move[1]] = "X";
            countOfMoves++;
            drawField();
            System.out.println("Making move level \"medium\"");
            threeInARowCheck();
            if (threeInARowCheck()) {
                break;
            }
            int x = -1;
            int y = -1;
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (ticTaeToeField[i][j].equals(" ")) {
                        ticTaeToeField[i][j] = "X";
                        //}
                        minMax(0, false);
                        ticTaeToeField[i][j] = " ";
                        if (score > bestScore) {
                            bestScore = score;
                            x = i;
                            y = j;
                        }
                    }
                }
            }
            ticTaeToeField[x][y] = "O";
            countOfMoves++;
            drawField();

            if (threeInARowCheck()) {
                break;
            }
        }
        if (countOfMoves == 9) {
            System.out.println("Draw");
        }
    }
}

class EasyHard extends Game {

    @Override
    public void makingMove() {
        boolean isX = true;
        Random rand = new Random();
        int x = 0;
        int y = 0;
        boolean isEmpty;
        drawField();
        int numberOfMoves = 0;
        while (numberOfMoves <= 9){
            //drawField();
                do {
                    x = rand.nextInt(3);
                    y = rand.nextInt(3);
                } while (!" ".equals(ticTaeToeField[x][y]));
                ticTaeToeField[x][y] = "X";
            drawField();
            if (threeInARowCheck()) {
                break;
            }
            numberOfMoves++;
            if (numberOfMoves == 9) {
                break;
            }

            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (ticTaeToeField[i][j].equals(" ")) {
                        ticTaeToeField[i][j] = "X";
                        //}
                        minMax(0, false);
                        ticTaeToeField[i][j] = " ";
                        if (score > bestScore) {
                            bestScore = score;
                            x = i;
                            y = j;
                        }
                    }
                }
            }
            ticTaeToeField[x][y] = "O";
            numberOfMoves++;
            drawField();

            if (threeInARowCheck()) {
                break;
            }
        }
        if (numberOfMoves == 9) {
            System.out.println("Draw");
        }
    }
}

class MediumHard extends Game {

    @Override
    public void makingMove() {

        boolean isX = true;
        Random rand = new Random();
        int x = 0;
        int y = 0;
        boolean isEmpty;
        drawField();
        int numberOfMoves = 0;
        while (numberOfMoves <= 9){
            //drawField();
            isEmpty = true;
            if (isX) {
                isEmpty = nextWinningMove("X");
                if (isEmpty) {
                    isEmpty = nextWinningMove("O");
                    if (!isEmpty) {
                        ticTaeToeField[blockinX][blockinY] = "X";
                    }

                }
                if (isEmpty) {
                    do {
                        x = rand.nextInt(3);
                        y = rand.nextInt(3);
                    } while (!" ".equals(ticTaeToeField[x][y]));
                    {
                        ticTaeToeField[x][y] = "X";
                    }
                }
                isX = false;

            } else {
                int bestScore = Integer.MIN_VALUE;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (ticTaeToeField[i][j].equals(" ")) {
                            ticTaeToeField[i][j] = "X";
                            //}
                            minMax(0, false);
                            ticTaeToeField[i][j] = " ";
                            if (score > bestScore) {
                                bestScore = score;
                                x = i;
                                y = j;
                            }
                        }
                    }
                }
                ticTaeToeField[x][y] = "O";
                isX = true;

            }
            drawField();
            if (threeInARowCheck()) {
                break;
            }
            numberOfMoves++;
            if (numberOfMoves == 9) {
                break;
            }

        }
        if (numberOfMoves == 9) {
            System.out.println("Draw");
        }

    }
}

class HardEasy extends Game {

    @Override
    public void makingMove() {
        boolean isX = true;
        Random rand = new Random();
        int x = 0;
        int y = 0;
        boolean isEmpty;
        drawField();
        int numberOfMoves = 0;
        while (numberOfMoves <= 9){
            //drawField();
            isEmpty = true;

            if (isX) {
                int bestScore = Integer.MIN_VALUE;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (ticTaeToeField[i][j].equals(" ")) {
                            ticTaeToeField[i][j] = "X";
                            //}
                            minMax(0, false);
                            ticTaeToeField[i][j] = " ";
                            if (score > bestScore) {
                                bestScore = score;
                                x = i;
                                y = j;
                            }
                        }
                    }
                }
                ticTaeToeField[x][y] = "X";
                isX = false;

            } else {
                do {
                    x = rand.nextInt(3);
                    y = rand.nextInt(3);
                } while (!" ".equals(ticTaeToeField[x][y]));
                {
                    ticTaeToeField[x][y] = "O";
                }
                isX = true;

            }
            drawField();
            if (threeInARowCheck()) {
                break;
            }
            numberOfMoves++;
            if (numberOfMoves == 9) {
                break;
            }

        }
        if (numberOfMoves == 9) {
            System.out.println("Draw");
        }
    }
}

class HardMedium extends Game {

    @Override
    public void makingMove() {
        boolean isX = true;
        Random rand = new Random();
        int x = 0;
        int y = 0;
        boolean isEmpty;
        drawField();
        int numberOfMoves = 0;
        while (numberOfMoves <= 9){
            //drawField();
            isEmpty = true;

            if (isX) {
                int bestScore = Integer.MIN_VALUE;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (ticTaeToeField[i][j].equals(" ")) {
                            ticTaeToeField[i][j] = "X";
                            //}
                            minMax(0, false);
                            ticTaeToeField[i][j] = " ";
                            if (score > bestScore) {
                                bestScore = score;
                                x = i;
                                y = j;
                            }
                        }
                    }
                }
                ticTaeToeField[x][y] = "X";
                isX = false;

            } else {
                isEmpty = nextWinningMove("X");
                if (isEmpty) {
                    isEmpty = nextWinningMove("O");
                    if (!isEmpty) {
                        ticTaeToeField[blockinX][blockinY] = "X";
                    }

                }
                if (isEmpty) {
                    do {
                        x = rand.nextInt(3);
                        y = rand.nextInt(3);
                    } while (!" ".equals(ticTaeToeField[x][y]));
                    {
                        ticTaeToeField[x][y] = "O";
                    }
                }
                isX = true;

            }
            drawField();
            if (threeInARowCheck()) {
                break;
            }
            numberOfMoves++;
            if (numberOfMoves == 9) {
                break;
            }

        }
        if (numberOfMoves == 9) {
            System.out.println("Draw");
        }
    }
}

class HardHard extends Game {

    @Override
    public void makingMove() {
        boolean isX = true;
        Random rand = new Random();
        int x = 0;
        int y = 0;
        boolean isEmpty;
        drawField();
        int numberOfMoves = 0;
        while (numberOfMoves <= 9){
            //drawField();
            isEmpty = true;

            if (isX) {
                int bestScore = Integer.MIN_VALUE;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (ticTaeToeField[i][j].equals(" ")) {
                            ticTaeToeField[i][j] = "X";
                            //}
                            minMax(0, false);
                            ticTaeToeField[i][j] = " ";
                            if (score > bestScore) {
                                bestScore = score;
                                x = i;
                                y = j;
                            }
                        }
                    }
                }
                ticTaeToeField[x][y] = "X";
                isX = false;

            } else {
                int bestScore = Integer.MIN_VALUE;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (ticTaeToeField[i][j].equals(" ")) {
                            ticTaeToeField[i][j] = "X";
                            //}
                            minMax(0, false);
                            ticTaeToeField[i][j] = " ";
                            if (score > bestScore) {
                                bestScore = score;
                                x = i;
                                y = j;
                            }
                        }
                    }
                }
                ticTaeToeField[x][y] = "O";
                isX = true;

            }
            drawField();
            if (threeInARowCheck()) {
                break;
            }
            numberOfMoves++;
            if (numberOfMoves == 9) {
                break;
            }

        }
        if (numberOfMoves == 9) {
            System.out.println("Draw");
        }

    }
}

public class Main {

    public static String[] inputCommand = new String[2];

    public static boolean checkInputOfCommand(String command) {
        String[] commandArray = command.split(" ");
        boolean isCommandCorrect = false;
        if (commandArray.length <= 2) {
            if (commandArray[0].equals("exit")) {
                System.exit(0);
            } else if (!commandArray[0].equals("start")) {
                System.out.println("Bad Parameters!");
            }
        } else {
            if (("easy".equals(commandArray[1]) || "medium".equals(commandArray[1])
            || "hard".equals(commandArray[1]) || "user".equals(commandArray[1])) &&
                    ("easy".equals(commandArray[2]) || "medium".equals(commandArray[2])
                            || "hard".equals(commandArray[2]) || "user".equals(commandArray[2]))) {

                inputCommand[0] = commandArray[1];
                inputCommand[1] = commandArray[2];
                isCommandCorrect = true;
            } else {
                System.out.println("Bad Parameters!");
                isCommandCorrect = false;
            }
        }
        return isCommandCorrect;
    }

    public static void main(String[] args) throws IOException {
        // write your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean isCommandCorrect = false;
        while (!isCommandCorrect) {
            System.out.print("Input command: ");
            String inputCommand = reader.readLine();
            isCommandCorrect = checkInputOfCommand(inputCommand);
        }
        switch (inputCommand[0]) {
            case "easy":
                switch (inputCommand[1]) {
                    case "easy":
                        EasyEasy newGame = new EasyEasy();
                        newGame.makingMove();
                        break;
                    case "user":
                        EasyUser newGame1 = new EasyUser();
                        newGame1.makingMove();
                        break;
                    case "medium":
                        EasyMedium newGame2 = new EasyMedium();
                        newGame2.makingMove();
                        break;

                    case "hard":
                        EasyHard newGame3 = new EasyHard();
                        newGame3.makingMove();
                        break;

                    default:
                }
                break;
            case "user" :
                switch (inputCommand[1]) {
                    case "easy":
                        UserEasy newGame = new UserEasy();
                        newGame.makingMove();
                        break;
                    case "user":
                        UserUser newGame1 = new UserUser();
                        newGame1.makingMove();
                        break;
                    case "medium":
                        UserMedium newGame2 = new UserMedium();
                        newGame2.makingMove();
                        break;

                    case "hard":
                        UserHard newGame3 = new UserHard();
                        newGame3.makingMove();
                        break;

                    default:
                }

                break;

            case "medium":
                switch ((inputCommand[1])) {
                    case "easy":
                        MediumEasy newGame = new MediumEasy();
                        newGame.makingMove();
                        break;
                    case "user":
                        MediumUser newGame2 = new MediumUser();
                        newGame2.makingMove();
                        break;

                    case "medium":
                        MediumMedium newGame3 = new MediumMedium();
                        newGame3.makingMove();
                        break;

                    case "hard":
                        MediumHard newGame4 = new MediumHard();
                        newGame4.makingMove();
                        break;

                    default:
                }

                break;

            case "hard":
                switch ((inputCommand[1])) {
                    case "easy":
                        HardEasy newGame = new HardEasy();
                        newGame.makingMove();
                        break;
                    case "user":
                        HardUser newGame2 = new HardUser();
                        newGame2.makingMove();
                        break;

                    case "medium":
                        HardMedium newGame3 = new HardMedium();
                        newGame3.makingMove();
                        break;

                    case "hard":
                        HardHard newGame4 = new HardHard();
                        newGame4.makingMove();
                        break;

                    default:
                }

                break;

            default:
        }
    }
}

