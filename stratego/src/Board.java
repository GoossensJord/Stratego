public class Board {
    public int boardHeight = 20; //minimum = 20 increments of 10 only
    public int boardWidth = 20; //minimum = 20 increments of 10 only
    private final int SQUARE_ARRAY_WIDTH = Math.abs((boardWidth + 1) / 2);
    private final int SQUARE_ARRAY_HEIGHT = Math.abs((boardHeight + 1) / 6);


    //char array to be replaced with array of squares
    char[][] temporaryReplacementForSquaresTeamA = new char[SQUARE_ARRAY_HEIGHT][SQUARE_ARRAY_WIDTH];
    char[][] temporaryReplacementForSquaresTeamB = new char[SQUARE_ARRAY_HEIGHT][SQUARE_ARRAY_WIDTH];

    //if piece selected highlight moveable squares
    public Board() {

    }

    //temporary fill method for char array until replacement
    public void fillCharArray() {
        for (int i = 0; i < SQUARE_ARRAY_HEIGHT; i++) {
            for (int j = 0; j < SQUARE_ARRAY_WIDTH; j++) {
                temporaryReplacementForSquaresTeamA[i][j] = 'A';
                temporaryReplacementForSquaresTeamB[i][j] = 'B';
            }
        }
    }


    public void makeBoard() {

        int counterHorizontalIndex = 0;
        int counterVerticalIndex = 0;
        System.out.println("    Welcome to Stratego!");
        for (int i = 0; i < boardHeight + 1; i++) {
            if (i % 2 != 0) {
                if (counterVerticalIndex < 10) {
                    System.out.print(counterVerticalIndex++ +"\t");
                }
                else System.out.print(counterVerticalIndex++ + "\t");

            }
            else System.out.print("\t");


            for (int j = 0; j < boardWidth + 1; j++) {
                if (i % 2 == 0) {
                    System.out.print("-");
                } else if (j % 2 == 0) {
                    System.out.print("|");
                } else if (i > boardHeight * 0.4 && i <= boardHeight * 0.6)
                    System.out.print(" ");
                else if (i < boardHeight * 0.6)
                    System.out.print(temporaryReplacementForSquaresTeamA[i / 7][j / 2]);

                else System.out.print(temporaryReplacementForSquaresTeamB[i / 7][j / 2]);
            }

            System.out.println();


        }
        System.out.print("    ");
        for (int i = 0; i < (boardWidth) / 2; i++) {
            if (counterHorizontalIndex < 10) {
                System.out.print(" " + counterHorizontalIndex++);
            }
            else
                System.out.print(counterHorizontalIndex++);
        }


    }

    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }
}