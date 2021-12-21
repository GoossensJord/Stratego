package board;

import pieces.Piece;
import pieces.Rank;

import java.util.*;

public class Board {
    //placeholder for input later.
    private int boardHeight = 60;
    private int boardWidth = 20;
    //square arr sizing
    private final int SQUARE_ARRAY_WIDTH = Math.abs((boardWidth + 1) / 2);
    private final int SQUARE_ARRAY_HEIGHT = Math.abs((boardHeight + 1) / 6);
    Square square;

    Square[][] squaresBoard = new Square[SQUARE_ARRAY_HEIGHT + 1][SQUARE_ARRAY_WIDTH + 1];


    //if piece selected highlight moveable squares
    public Board() {
        square = new Square(this);
    }

    //makes squares on all index to not get nullpointers, then adds empty pieces
    public void fillWithEmptyPieces(Player player) {

        for (int i = 0; i <= SQUARE_ARRAY_HEIGHT; i++) {
            for (int j = 0; j <= SQUARE_ARRAY_WIDTH; j++) {
                if (squaresBoard[i][j] == null) {
                    squaresBoard[i][j] = new Square(this);
                    squaresBoard[i][j].setPiece(new Piece(Rank.EMPTY, player, 0, 0));

                }
            }
        }
    }

    public void arrangePiecesTopPlayer(Piece piece) {
        for (int i = 0; i < SQUARE_ARRAY_HEIGHT; i++) {
            for (int j = 0; j < SQUARE_ARRAY_WIDTH; j++) {
                if (spaceAvailable(i, j)) {
                    squaresBoard[i][j].setPiece(piece);
                    return;
                }
            }
        }
    }


    public void arrangePiecesBottomPlayer(Piece piece) {
        for (int i = SQUARE_ARRAY_HEIGHT - 1; i >= 0; i--) {
            for (int j = SQUARE_ARRAY_WIDTH - 1; j >= 0; j--) {
                if (spaceAvailable(i, j)) {
                    squaresBoard[i][j].setPiece(piece);
                    return;
                }
            }
        }
    }


    public void fillBoard(Player player) {
        for (Rank r : Rank.values()) {
            for (int i = 0; i < r.getAmnt(); i++) {
                assignPiece(r, player);
            }
        }
        System.out.println("full");
    }


    public void assignPiece(Rank rank, Player player) {
        Scanner sc = new Scanner(System.in);
        boolean validNumberRange = true;
        boolean validAmountOfNumbers;
        boolean placeTaken = true;
        String index = " ";
        int heightIndex;
        int widthIndex;

        while (validNumberRange) {
            while (placeTaken) {
                validAmountOfNumbers = true;
                while (validAmountOfNumbers) {
                    System.out.println(player.getName() + ", where do u want to place the " + rank.getName() + "? ");
                    index = sc.next();
                    validAmountOfNumbers = ammountOfInputCharacterCheck(index);
                }
                heightIndex = Character.digit(index.charAt(0), 10);
                widthIndex = Character.digit(index.charAt(1), 10);

                if (!rangeOfInputNumbersCheck(player, heightIndex, widthIndex)) {

                    if (spaceAvailable(heightIndex, widthIndex)) {
                        squaresBoard[heightIndex][widthIndex].setPiece(new Piece(rank, player, heightIndex, widthIndex));
                        placeTaken = false;
                        validNumberRange = false;
                        printOutCurrentBoard();
                    }

                }
            }
        }


    }


    public boolean spaceAvailable(int heightIndex, int widthIndex) {


        if (squaresBoard[heightIndex][widthIndex].getRank().equals(Rank.EMPTY)) {

            return true;

        } else {

            //System.out.println("place taken");
            return false;

        }

    }


    public boolean rangeOfInputNumbersCheck(Player player, int heightIndex, int widthIndex) {

        if (player.getId() == 1) {
            if (heightIndex <= 3 && widthIndex <= 9 && heightIndex >= 0) {
                return false;
            } else {
                System.out.println("incorrect number range");
                return true;
            }
        } else {
            if (heightIndex >= 6 && widthIndex <= 9 && heightIndex <= 9) {
                return false;

            } else {
                System.out.println("incorrect number range");
                return true;
            }

        }
    }


    public boolean ammountOfInputCharacterCheck(String input) {

        if (input.length() != 2) {

            System.out.println("Enter two numbers no spaces");
            return true;

        } else return false;
    }


    public void printOutCurrentBoard() {
        System.out.println("Stratego\n");
        for (int i = 0; i < SQUARE_ARRAY_HEIGHT; i++) {
            for (int j = 0; j < SQUARE_ARRAY_WIDTH; j++) {
                System.out.print(squaresBoard[i][j] + " ");
            }
            System.out.print(" " + i);
            System.out.println();
        }

        System.out.println("\n0  1  2  3  4  5  6  7  8  9");
    }


    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    public int getPIECE_ARRAY_WIDTH() {
        return this.SQUARE_ARRAY_WIDTH;
    }

    public int getPIECE_ARRAY_HEIGHT() {
        return this.SQUARE_ARRAY_WIDTH;
    }

    public void move(int x, int y) {
        if (square.outOfBoundsOrOccupied(x, y)) {

        }
    }

    //xd
    public void randomlyPlacePieces(Player player) {
        List<Piece> piecesPlayer = Arrays.asList(
                new Piece(Rank.BOMB, player, 0, 0),
                new Piece(Rank.BOMB, player, 0, 0),
                new Piece(Rank.BOMB, player, 0, 0),
                new Piece(Rank.BOMB, player, 0, 0),
                new Piece(Rank.BOMB, player, 0, 0),
                new Piece(Rank.BOMB, player, 0, 0),
                new Piece(Rank.MARSHAL, player, 0, 0),
                new Piece(Rank.GENERAL, player, 0, 0),
                new Piece(Rank.COLONEL, player, 0, 0),
                new Piece(Rank.COLONEL, player, 0, 0),
                new Piece(Rank.MAJOR, player, 0, 0),
                new Piece(Rank.MAJOR, player, 0, 0),
                new Piece(Rank.MAJOR, player, 0, 0),
                new Piece(Rank.CAPTAIN, player, 0, 0),
                new Piece(Rank.CAPTAIN, player, 0, 0),
                new Piece(Rank.CAPTAIN, player, 0, 0),
                new Piece(Rank.CAPTAIN, player, 0, 0),
                new Piece(Rank.LUITENANT, player, 0, 0),
                new Piece(Rank.LUITENANT, player, 0, 0),
                new Piece(Rank.LUITENANT, player, 0, 0),
                new Piece(Rank.LUITENANT, player, 0, 0),
                new Piece(Rank.SERGEANT, player, 0, 0),
                new Piece(Rank.SERGEANT, player, 0, 0),
                new Piece(Rank.SERGEANT, player, 0, 0),
                new Piece(Rank.SERGEANT, player, 0, 0),
                new Piece(Rank.MINER, player, 0, 0),
                new Piece(Rank.MINER, player, 0, 0),
                new Piece(Rank.MINER, player, 0, 0),
                new Piece(Rank.MINER, player, 0, 0),
                new Piece(Rank.MINER, player, 0, 0),
                new Piece(Rank.SCOUT, player, 0, 0),
                new Piece(Rank.SCOUT, player, 0, 0),
                new Piece(Rank.SCOUT, player, 0, 0),
                new Piece(Rank.SCOUT, player, 0, 0),
                new Piece(Rank.SCOUT, player, 0, 0),
                new Piece(Rank.SCOUT, player, 0, 0),
                new Piece(Rank.SCOUT, player, 0, 0),
                new Piece(Rank.SCOUT, player, 0, 0),
                new Piece(Rank.SPY, player, 0, 0),
                new Piece(Rank.FLAG, player, 0, 0));

        Collections.shuffle(piecesPlayer);
        for (Piece piece : piecesPlayer) {
            if (player.getId() == 1) {
                arrangePiecesTopPlayer(piece);
            } else {
                arrangePiecesBottomPlayer(piece);
            }
        }

    }
}