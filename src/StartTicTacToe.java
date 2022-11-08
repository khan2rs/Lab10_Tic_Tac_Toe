import java.util.Scanner;

public class StartTicTacToe {

            // setting up game board
            //private static final int ROW = 3;
            //private static final int COL = 3;
            //private static String board [][] = new String[ROW][COL];
            private static final int ROW = 3;
            private static final int COL = 3;
            private static String board[][] = new String[ROW][COL];



            public static void main(String[] args) {
                String player = "X";
                boolean playAgain = false;

                Scanner in = new Scanner(System.in);

                do {
                    int moves = 0;

                    clearBoard();

                    do {
                        if (moves == 0) {
                            player = "X";
                        } else {
                            if (player.equals("X")) {
                                player = "O";
                            } else {
                                player = "X";
                            }
                        }
                        showBoard();

                        // gets user move
                        int[] cords;

                        do {
                            cords = getMove(player);
                        }
                        while (!isValidMove(cords[0], cords[1]));

                        // display move onto the board
                        board[cords[0]][cords[1]] = player;

                        moves += 1;
                    }
                    while (!gameOver(player));

                    showBoard();

                    if (isWin(player)) {
                        System.out.println("\nPlayer " + player + " wins!");
                    } else {
                        System.out.println("\nIt's a tie!");
                    }

                    // asks user if they want to play again
                    playAgain = SafeInputs.getYNConfirm(in, "Play again?");
                }
                while (playAgain);

            }
//here's where we start methods. methods to use:
//•	private static void clearBoard() // sets all the board elements to a space
//•	private static void display() // shows the Tic Tac Toe game used as part of the promt for the users move choice…
//•	private static boolean isValidMove(int row, int col) // returns true if there is a space at the given proposed move coordinates which means it is a legal move.
//private static boolean isWin(String player) // checks to see if there is a win state on the current board for the specified player (X or O) This method in turn calls three additional methods that break down the 3 kinds of wins that are possible.
//•	private static boolean isColWin(String player) // checks for a col win for specified player
//•	private static boolean isRowWin(String player) // checks for a row win for the specified player
//•	private static boolean isDiagnalWin(String player) // checks for a diagonal win for the specified player
//•	private static boolean isTie() // checks for a tie condition: all spaces on the board are filled OR there is an X and an O in every win vector (i.e. all possible 8 wins are blocked by having both and X and an O in them.)
            // clears the board - makes all cells spaces
            private static void clearBoard() {
                // nested loop that runs through all the cells
                for (int row = 0; row < ROW; row++) {
                    for (int col = 0; col < COL; col++) {
                        // makes the cell a space
                        board[row][col] = " ";
                    }
                }
            }

     //display aka showBoard
            private static void showBoard() {



                for (int row = 0; row < ROW; row++) {
                    if (row == 0) {
                        System.out.print("     1 ");
                    } else if (row == 1) {
                        System.out.print("     2 ");
                    } else if (row == 2) {
                        System.out.print("     3 ");
                    }
                    for (int col = 0; col < COL; col++) {

                        if (col < 2) {
                            System.out.print(board[row][col] + " | ");
                        } else if (col == 2) {
                            System.out.print(board[row][col]);
                        }
                    }
                    if (row < 2) {
                        System.out.println("\n       --  --  --");
                    }
                }
                System.out.println("");
            }

            // player input here
            private static int[] getMove(String player) {
                Scanner in = new Scanner(System.in);

                int rowMove = SafeInputs.getRangedInt(in, "Player " + player + " which ROW do you want place your move in?", 1, 3);
                int colMove = SafeInputs.getRangedInt(in, "Player " + player + " which COL do you want to place your move in?", 1, 3);

                rowMove -= 1;
                colMove -= 1;

                int coordinates[] = new int[2];
                coordinates[0] = rowMove;
                coordinates[1] = colMove;

                return coordinates;
            }

            private static boolean isValidMove(int row, int col) {
                boolean validMove = false;

                if (board[row][col].equals(" ")) {
                    validMove = true;
                } else {
                    System.out.println("Sorry, that move is invalid. Try again.");
                    showBoard();
                    validMove = false;
                }

                return validMove;
            }

            private static boolean isWin(String player) {
                if (isColWin(player) || isRowWin(player) || isDiagonalWin(player)) {
                    return true;
                } else {
                    return false;
                }
            }

            private static boolean isRowWin(String player) {
                for (int row = 0; row < ROW; row++) {
                    if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                        return true;
                    }
                }
                return false;
            }

            private static boolean isColWin(String player) {
                for (int col = 0; col < COL; col++) {
                    if (board[col][0].equals(player) && board[col][1].equals(player) && board[col][2].equals(player)) {
                        return true;
                    }
                }
                return false;
            }


            private static boolean isTie() {
                if (rowOne() && rowTwo() && rowThree() && colOne() && colTwo() && colThree() && diagOne() && diagTwo()) {
                    return true;
                }
                return false;
            }
            private static boolean isDiagonalWin(String player) {
                if ((board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) || (board[2][0].equals(player) && board[1][1].equals(player) && board[0][2].equals(player))) {
            return true;
        }
        return false;
    }
            private static boolean rowOne() {
                if (board[0][0].equals("X")) {
                    if (board[0][1].equals("O") || board[0][2].equals("O")) {
                        return true;
                    }
                } else if (board[0][0].equals("O")) {
                    if (board[0][1].equals("X") || board[0][2].equals("X")) {
                        return true;
                    }
                } else if ((board[0][1].equals("X") && board[0][2].equals("O")) || (board[0][1].equals("O") && board[0][2].equals("X"))) {
                    return true;
                }
                return false;
            }

            private static boolean rowTwo() {
                if (board[1][0].equals("X")) {
                    if (board[1][1].equals("O") || board[1][2].equals("O")) {
                        return true;
                    }
                } else if (board[1][0].equals("O")) {
                    if (board[1][1].equals("X") || board[1][2].equals("X")) {
                        return true;
                    }
                } else if ((board[1][1].equals("X") && board[1][2].equals("O")) || (board[1][1].equals("O") && board[1][2].equals("X"))) {
                    return true;
                }
                return false;
            }

            private static boolean rowThree() {
                if (board[2][0].equals("X")) {
                    if (board[2][1].equals("O") || board[2][2].equals("O")) {
                        return true;
                    }
                } else if (board[2][0].equals("O")) {
                    if (board[2][1].equals("X") || board[2][2].equals("X")) {
                        return true;
                    }
                } else if ((board[2][1].equals("X") && board[2][2].equals("O")) || (board[2][1].equals("O") && board[2][2].equals("X"))) {
                    return true;
                }
                return false;
            }

            private static boolean colOne() {
                if (board[0][0].equals("X")) {
                    if (board[1][0].equals("O") || board[2][0].equals("O")) {
                        return true;
                    }
                } else if (board[0][0].equals("O")) {
                    if (board[1][0].equals("X") || board[2][0].equals("X")) {
                        return true;
                    }
                } else if ((board[1][0].equals("X") && board[2][0].equals("O")) || (board[1][0].equals("O") && board[2][0].equals("X"))) {
                    return true;
                }
                return false;
            }

            private static boolean colTwo() {
                if (board[0][1].equals("X")) {
                    if (board[1][1].equals("O") || board[2][1].equals("O")) {
                        return true;
                    }
                } else if (board[0][1].equals("O")) {
                    if (board[1][1].equals("X") || board[2][1].equals("X")) {
                        return true;
                    }
                } else if ((board[1][1].equals("X") && board[2][1].equals("O")) || (board[1][1].equals("O") && board[2][1].equals("X"))) {
                    return true;
                }
                return false;
            }

            private static boolean colThree() {
                if (board[0][2].equals("X")) {
                    if (board[1][2].equals("O") || board[2][2].equals("O")) {
                        return true;
                    }
                } else if (board[0][2].equals("O")) {
                    if (board[1][2].equals("X") || board[2][2].equals("X")) {
                        return true;
                    }
                } else if ((board[1][2].equals("X") && board[2][2].equals("O")) || (board[1][2].equals("O") && board[2][2].equals("X"))) {
                    return true;
                }
                return false;
            }

            private static boolean diagOne() {
                if (board[0][0].equals("X")) {
                    if (board[1][1].equals("O") || board[2][2].equals("O")) {
                        return true;
                    }
                } else if (board[0][0].equals("O")) {
                    if (board[1][1].equals("X") || board[2][2].equals("X")) {
                        return true;
                    }
                } else if ((board[1][1].equals("X") && board[2][2].equals("O")) || (board[1][1].equals("O") && board[2][2].equals("X"))) {
                    return true;
                }
                return false;
            }

            private static boolean diagTwo() {
                if (board[0][2].equals("X")) {
                    if (board[1][1].equals("O") || board[2][0].equals("O")) {
                        return true;
                    }
                } else if (board[0][2].equals("O")) {
                    if (board[1][1].equals("X") || board[2][0].equals("X")) {
                        return true;
                    }
                } else if ((board[1][1].equals("X") && board[2][0].equals("O")) || (board[1][1].equals("O") && board[2][0].equals("X"))) {
                    return true;
                }
                return false;
            }

            private static boolean gameOver(String player) {
                if (isTie() || isWin(player)) {
                    return true;
                }
                return false;
            }
        }
