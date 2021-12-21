package board;

import pieces.Piece;
import pieces.Rank;

import java.util.Scanner;

public class Board {

    private int boardHeight = 20;
    private int boardWidth = 20;
    private final int SQUARE_ARRAY_WIDTH = Math.abs((boardWidth + 1) / 2);
    private final int SQUARE_ARRAY_HEIGHT = Math.abs((boardHeight + 1) / 6);


    Piece[][] piecesTeamA = new Piece[SQUARE_ARRAY_HEIGHT + 1][SQUARE_ARRAY_WIDTH + 1];
    Piece[][] piecesTeamB = new Piece[SQUARE_ARRAY_HEIGHT + 1][SQUARE_ARRAY_WIDTH + 1];

    //if piece selected highlight moveable squares
    public Board() {

    }

    public void fillWithEmptyPieces(Player playerA, Player playerB) {

        for (int i = 0; i <= SQUARE_ARRAY_HEIGHT; i++) {
            for (int j = 0; j <= SQUARE_ARRAY_WIDTH; j++) {
                if (piecesTeamA[i][j] == null) {
                    piecesTeamA[i][j] = new Piece(Rank.SCOUT, playerA);
                }
                if (piecesTeamB[i][j] == null) {
                    piecesTeamB[i][j] = new Piece(Rank.SCOUT, playerB);
                }
            }
        }
    }

    public void fillBoard(Player playerA, Player playerB) {

        fillWithEmptyPieces(playerA, playerB);
        for (int i = 1; i < Rank.BOMB.getAmnt(); i++) {
            assignPiece(Rank.BOMB, playerA);
            assignPiece(Rank.BOMB, playerB);
        }
        for (int i = 1; i < Rank.GENERAL.getAmnt(); i++) {
            assignPiece(Rank.SCOUT, playerA);
            assignPiece(Rank.SCOUT, playerB);
        }
        for (int i = 1; i < Rank.MINER.getAmnt(); i++) {
            assignPiece(Rank.MINER, playerA);
            assignPiece(Rank.MINER, playerB);
        }
        for (int i = 1; i < Rank.LUITENANT.getAmnt(); i++) {
            assignPiece(Rank.SERGEANT, playerA);
            assignPiece(Rank.SERGEANT, playerB);

            assignPiece(Rank.LUITENANT, playerA);
            assignPiece(Rank.LUITENANT, playerB);

            assignPiece(Rank.CAPTAIN, playerA);
            assignPiece(Rank.CAPTAIN, playerB);
        }
        for (int i = 1; i < Rank.MAJOR.getAmnt(); i++) {
            assignPiece(Rank.MAJOR, playerA);
            assignPiece(Rank.MAJOR, playerB);
        }
        for (int i = 1; i < Rank.COLONEL.getAmnt(); i++) {

            assignPiece(Rank.COLONEL, playerA);
            assignPiece(Rank.COLONEL, playerB);
        }

        assignPiece(Rank.MARSHAL, playerA);
        assignPiece(Rank.MARSHAL, playerB);

        assignPiece(Rank.GENERAL, playerA);
        assignPiece(Rank.GENERAL, playerB);

        assignPiece(Rank.SPY, playerA);
        assignPiece(Rank.SPY, playerB);

        assignPiece(Rank.FLAG, playerA);
        assignPiece(Rank.FLAG, playerB);
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

                if (!rangeOfInputNumbersCheck(heightIndex, widthIndex)) {

                    if (spaceAvailable(rank, player, piecesTeamA, piecesTeamB, heightIndex, widthIndex)) {
                        placeTaken = false;
                        validNumberRange = false;
                        printOutCurrentBoard();
                    }

                }
            }
        }


    }

    public boolean spaceAvailable(Rank rank, Player player, Piece[][] piecesTeamA, Piece[][] piecesTeamB, int heightIndex, int widthIndex) {

        if (piecesTeamA[heightIndex][widthIndex].getRank().equals(Rank.EMPTY) && player.getId() == 1) {

            piecesTeamA[heightIndex][widthIndex] = new Piece(rank, player);
            return true;

        } else if (piecesTeamB[heightIndex][widthIndex].getRank().equals(Rank.EMPTY) && player.getId() != 1) {

            piecesTeamB[heightIndex][widthIndex] = new Piece(rank, player);
            return true;

        } else {

            System.out.println("place taken");
            return false;

        }

    }

    public boolean rangeOfInputNumbersCheck(int heightIndex, int widthIndex) {

        if (heightIndex <= 3 && widthIndex <= 9 && heightIndex >= 0) {
            return false;

        } else {
            System.out.println("incorrect number range");
            return true;

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

        for (int i = 0; i <= SQUARE_ARRAY_HEIGHT; i++) {

            for (int j = 0; j < SQUARE_ARRAY_WIDTH; j++) {
                System.out.print(piecesTeamA[i][j] + " ");
            }
            System.out.print(i);
            System.out.println();
        }
        System.out.println();
        System.out.println();

        for (int i = 0; i <= SQUARE_ARRAY_HEIGHT; i++) {
            for (int j = 0; j < SQUARE_ARRAY_WIDTH; j++) {
                System.out.print(piecesTeamB[i][j] + " ");
            }
            System.out.print(i);
            System.out.println();
        }
        System.out.println("\n0  1  2  3  4  5  6  7  8  9");
    }

    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
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
    //xd

}