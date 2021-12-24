package board;

import pieces.Piece;
import pieces.Rank;

import java.time.Instant;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BoardMaker{
    private int boardHeight = 60;
    private int boardWidth = 20;
    private final int SQUARE_ARRAY_WIDTH = Math.abs((boardWidth + 1) / 2);
    private final int SQUARE_ARRAY_HEIGHT = Math.abs((boardHeight + 1) / 6);
    protected Square[][] squaresBoard = new Square[SQUARE_ARRAY_HEIGHT + 1][SQUARE_ARRAY_WIDTH + 1];

    public BoardMaker(){

    }
    //FIlls the board with empty squares.
    public void fillWithSquares(Board board) {

        for (int i = 0; i <= SQUARE_ARRAY_HEIGHT; i++) {
            for (int j = 0; j <= SQUARE_ARRAY_WIDTH; j++) {
                if (squaresBoard[i][j] == null) {
                    squaresBoard[i][j] = new Square(board);
                }
            }
        }
    }

    public void printOutCurrentBoard() {
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("\n STRATEGO\n");
        for (int i = 0; i < SQUARE_ARRAY_HEIGHT; i++) {
            for (int j = 0; j < SQUARE_ARRAY_WIDTH; j++) {
                System.out.print(" ");
                System.out.print(squaresBoard[i][j] + " ");
            }
            System.out.print(" " + i);
            System.out.println();
        }

        System.out.println("\n 0   1   2   3   4   5   6   7   8   9");
        System.out.println();
        System.out.println("Current Time:\t" + localTime.format(dateTimeFormatter) + "\n");
    }
    //Randomly places all 40 pieces on the board, mainly for testing purposes, future gamemode maybe?
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
        int counter = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                if (player.getId() == 1) {
                    arrangePiecesTopPlayer(piecesPlayer.get(counter));
                    piecesPlayer.get(counter).setX(i);
                    piecesPlayer.get(counter++).setY(j);
                }
                else{
                    arrangePiecesBottomPlayer(piecesPlayer.get(counter));
                    piecesPlayer.get(counter).setX(i);
                    piecesPlayer.get(counter++).setY(j);
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

    public boolean spaceAvailable(int heightIndex, int widthIndex) {
        if (!squaresBoard[heightIndex][widthIndex].getIsOccupied()) {
            return true;
        } else {
            System.out.println("place taken");
            return false;
        }

    }

    public void assignPiece(Rank rank, Player player) {
        BoardInputChecker boardInputChecker = new BoardInputChecker();
        boolean validNumberRange = true;
        boolean placeTaken = true;
        String index = " ";
        int heightIndex;
        int widthIndex;

        while (validNumberRange) {
            while (placeTaken) {
                index = boardInputChecker.inputString(player, rank);
                heightIndex = Character.digit(index.charAt(0), 10);
                widthIndex = Character.digit(index.charAt(1), 10);

                if (!boardInputChecker.rangeOfInputNumbersCheck(player, heightIndex, widthIndex)) {
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

    public Square[][] getSquaresBoard() {
        return squaresBoard;
    }
}
