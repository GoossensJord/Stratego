package board;

import pieces.Piece;
import pieces.Rank;

import java.lang.module.FindException;
import java.util.Scanner;

public class Board {

    public int boardHeight = 20; //minimum = 20 increments of 10 only
    public int boardWidth = 20; //minimum = 20 increments of 10 only
    private final int SQUARE_ARRAY_WIDTH = Math.abs((boardWidth + 1) / 2);
    private final int SQUARE_ARRAY_HEIGHT = Math.abs((boardHeight + 1) / 6);


    //char array to be replaced with array of squares
    Piece[][] piecesTeamA = new Piece[SQUARE_ARRAY_HEIGHT + 1][SQUARE_ARRAY_WIDTH + 1];
    Piece[][] piecesTeamB = new Piece[SQUARE_ARRAY_HEIGHT + 1][SQUARE_ARRAY_WIDTH + 1];

    //if piece selected highlight moveable squares
    public Board() {

    }

    //temporary fill method for char array until replacement
    public void fillCharArray(Player playerA, Player playerB) {
        //placeholder until entire setup is done
        for (int i = 0; i <= SQUARE_ARRAY_HEIGHT; i++) {
            for (int j = 0; j <= SQUARE_ARRAY_WIDTH; j++) {
                if (piecesTeamA[i][j] == null) {
                    piecesTeamA[i][j] = new Piece(Rank.EMPTY, playerA);

                }
                if (piecesTeamB[i][j] == null) {
                    piecesTeamB[i][j] = new Piece(Rank.EMPTY, playerB);
                }

            }
        }

        for (int i = 1; i < 7; i++) {
            assignPiece(Rank.BOMB, playerA, playerB);
        }
        for (int i = 1; i < 9; i++) {
            assignPiece(Rank.SCOUT, playerA, playerB);

        }
        for (int i = 1; i < 6; i++) {
            assignPiece(Rank.MINER, playerA, playerB);
        }
        for (int i = 1; i < 5; i++) {
            assignPiece(Rank.SERGEANT, playerA, playerB);
            assignPiece(Rank.LUITENANT, playerA, playerB);
            assignPiece(Rank.CAPTAIN, playerA, playerB);

        }
        for (int i = 1; i < 4; i++) {
            assignPiece(Rank.MAJOR, playerA, playerB);

        }
        for (int i = 0; i < 2; i++) {
            assignPiece(Rank.COLONEL, playerA, playerB);
        }
        assignPiece(Rank.MARSHAL, playerA, playerB);
        assignPiece(Rank.GENERAL, playerA, playerB);
        assignPiece(Rank.SPY, playerA, playerB);
        assignPiece(Rank.FLAG,playerA,playerB);


    }

    public void assignPiece(Rank rank, Player playerA, Player playerB) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Where do u want to place the " + rank.getName() +"? HW= ");
        String flagIndex = sc.next();
        int heightIndexFlag = Character.digit(flagIndex.charAt(0), 10);
        int widthIndexFlag = Character.digit(flagIndex.charAt(1), 10);
        piecesTeamA[heightIndexFlag][widthIndexFlag] = new Piece(rank, playerA);
        piecesTeamB[heightIndexFlag][widthIndexFlag] = new Piece(rank, playerB);
        printOutCurrentBoard();
    }

//in comment, low priority
  /*  public void makeBoard() {

        int counterHorizontalIndex = 0;
        int counterVerticalIndex = 0;

        System.out.println("    Welcome to Stratego!");
        for (int i = 0; i < boardHeight + 1; i++) {
            if (i % 2 != 0) {
                if (counterVerticalIndex < 10) {
                    System.out.print(counterVerticalIndex++ + "\t");
                } else System.out.print(counterVerticalIndex++ + "\t");

            } else System.out.print("\t");


            for (int j = 0; j < boardWidth + 1; j++) {
                if (i % 2 == 0) {
                    System.out.print("-");
                } else if (j % 2 == 0) {
                    System.out.print("|");
                } else if (i > boardHeight * 0.4 && i <= boardHeight * 0.6)
                    System.out.print(" ");
                else if (i < boardHeight * 0.6) {
                    System.out.print(piecesTeamA[i][j].toString());
                } else System.out.print(piecesTeamB[i][j].toString());

            }
            System.out.println();
        }
        System.out.print("    ");
        for (
                int i = 0; i < (boardWidth) / 2; i++) {
            if (counterHorizontalIndex < 10) {
                System.out.print(" " + counterHorizontalIndex++);
            } else
                System.out.print(counterHorizontalIndex++);
        }



    }*/

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
            System.out.print(i + 6);
            System.out.println();
        }
        System.out.println("\n0 1 2 3 4 5 6 7 8 9");
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
    //xd
}