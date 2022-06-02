package tictactoe;
import java.lang.*;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String board = "_________";
        System.out.println("Enter cells: " + board);
        printBoard(board);
        boolean xTurn = true;
        while (true){
            if (scanner.hasNextInt()) {
                int coordinate1 = scanner.nextInt();
                int coordinate2 = scanner.nextInt();
                boolean checkNumber = 1 <= coordinate1 && coordinate1 <= 3 && 1 <= coordinate2 && coordinate2 <= 3;
                if (!checkNumber){
                    System.out.println("Enter the coordinates: " + coordinate1 + " " + coordinate2);
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }
                char ch;
                if (xTurn) {
                    ch = 'X';
                    xTurn = false;
                } else {
                    ch = 'O';
                    xTurn = true;
                }
                String result = play(board,coordinate1,coordinate2,ch);
                if (result.equals("2")) {
                    System.out.println("This cell is occupied! Choose another one!");
                    if (xTurn) {
                        xTurn = false;
                    } else {
                        xTurn = true;
                    }
                } else {
                    board = result;
                    continue;
                }
            } else {
                String line = scanner.nextLine();
                System.out.println("Enter the coordinates: " + line);
                System.out.println("You should enter numbers!");
                continue;
            }
        }
    }
    public static String play(String boards, int coordinate_1, int coordinate_2, char ch) {
        String board = boards;
        int[][][] array = new int[3][3][2];
        int num  = 1;
        int num2 = 1;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j][0] = num;
                for (int k = 0; k < array[i][j].length; k++) {
                    array[i][j][1] = num2;

                }num2++;
            }num++;
            num2 = 1;
        }
        System.out.println("Enter the coordinates: " + coordinate_1 + " " + coordinate_2);
        int[] coordinates = {coordinate_1, coordinate_2};
        int index = 0;

        if (Arrays.equals(coordinates, array[0][0])){
            index = 0;
        } else if (Arrays.equals(coordinates, array[0][1])){
            index = 1;
        } else if (Arrays.equals(coordinates, array[0][2])){
            index = 2;
        } else if (Arrays.equals(coordinates, array[1][0])){
            index = 3;
        } else if (Arrays.equals(coordinates, array[1][1])){
            index = 4;
        } else if (Arrays.equals(coordinates, array[1][2])){
            index = 5;
        } else if (Arrays.equals(coordinates, array[2][0])){
            index = 6;
        } else if (Arrays.equals(coordinates, array[2][1])){
            index = 7;
        } else if (Arrays.equals(coordinates, array[2][2])){
            index = 8;
        }

        if (board.charAt(index) == '_') {
            board = board.substring(0, index) + ch + board.substring(index + 1);
            printBoard(board);
            getWinner(board);
            return board;
        } else {
            return "2";
        }
    }
    public static void printBoard(String board) {

        System.out.println("---------");
        System.out.println("| " + board.charAt(0) + " " + board.charAt(1) + " " + board.charAt(2) + " |");
        System.out.println("| " + board.charAt(3) + " " + board.charAt(4) + " " + board.charAt(5) + " |");
        System.out.println("| " + board.charAt(6) + " " + board.charAt(7) + " " + board.charAt(8) + " |");
        System.out.println("---------");
    }

    public static void getWinner(String value) {
        int drawGame = 0;
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c == '_') {
                drawGame++;
            }
        }

        boolean draw = drawGame == 0;
        boolean topRow = value.charAt(0) == value.charAt(1) && value.charAt(0) == value.charAt(2);
        boolean middleRow = value.charAt(3) == value.charAt(4) && value.charAt(3) == value.charAt(5);
        boolean bottomRow = value.charAt(6) == value.charAt(7) && value.charAt(6) == value.charAt(8);
        boolean diagonal = value.charAt(6) == value.charAt(4) && value.charAt(6) == value.charAt(2);
        boolean diagonal2 = value.charAt(0) == value.charAt(4) && value.charAt(0) == value.charAt(8);
        boolean verticalLeft = value.charAt(0) == value.charAt(3) && value.charAt(0) == value.charAt(6);
        boolean verticalMiddle = value.charAt(1) == value.charAt(4) && value.charAt(1) == value.charAt(7);
        boolean verticalRight = value.charAt(2) == value.charAt(5) && value.charAt(2) == value.charAt(8);
        boolean charAt0Win = (topRow ||   verticalLeft) && value.charAt(0) != '_';
        boolean charAt4Win = (middleRow ||  diagonal || diagonal2 || verticalMiddle) && value.charAt(4) != '_';
        boolean charAt8Win = (bottomRow || verticalRight) && value.charAt(8) != '_';

        if (charAt0Win) {
            System.out.println(value.charAt(0) + " wins");
            System.exit(0);
        } else if (charAt4Win) {
            System.out.println(value.charAt(4) + " wins");
            System.exit(0);
        } else if (charAt8Win) {
            System.out.println(value.charAt(8) + " wins");
            System.exit(0);
        } else if (draw) {
            System.out.println("Draw");
            System.exit(0);

        }
    }
}