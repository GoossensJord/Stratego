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
        Scanner sc = new Scanner(System.in);

        for (int i = 1; i < 7; i++) {
            System.out.println("Where do u want to place Bomb nr " + i + " HW = ");
            String bombIndex = sc.next();
            int heightIndexBomb = Character.digit(bombIndex.charAt(0),10);
            int widthIndexBomb = Character.digit(bombIndex.charAt(1),10);
            piecesTeamA[heightIndexBomb][widthIndexBomb] = new Piece(Rank.BOMB, playerA);
            piecesTeamB[heightIndexBomb][widthIndexBomb] = new Piece(Rank.BOMB, playerB);
            printOutCurrentBoard();
        }
        for (int i = 1; i < 9; i++) {
            System.out.println("Where do u want to place Scout nr " + i + " HW = ");
            String scoutIndex = sc.next();
            int heightIndexScout = Character.digit(scoutIndex.charAt(0),10);
            int widthIndexScout = Character.digit(scoutIndex.charAt(1),10);
            piecesTeamA[heightIndexScout][widthIndexScout] = new Piece(Rank.SCOUT, playerA);
            piecesTeamB[heightIndexScout][widthIndexScout] = new Piece(Rank.SCOUT, playerB);
            printOutCurrentBoard();
        }
        for (int i = 1; i < 6; i++) {
            System.out.println("Where do u want to place Miner nr " + i + " HW = ");
            String minerIndex = sc.next();
            int heightIndexMiner = Character.digit(minerIndex.charAt(0),10);
            int widthIndexMiner = Character.digit(minerIndex.charAt(1),10);
            piecesTeamA[heightIndexMiner][widthIndexMiner] = new Piece(Rank.MINER, playerA);
            piecesTeamB[heightIndexMiner][widthIndexMiner] = new Piece(Rank.MINER, playerB);
            printOutCurrentBoard();
        }
        for (int i = 1; i < 5; i++) {
            System.out.println("Where do u want to place Major nr " + i + " HW = ");
            String seargentIndex = sc.next();
            int heightIndexSeargent = Character.digit(seargentIndex.charAt(0),10);
            int widthIndexSeargent = Character.digit(seargentIndex.charAt(1),10);
            piecesTeamA[heightIndexSeargent][widthIndexSeargent] = new Piece(Rank.SERGEANT, playerA);
            piecesTeamB[heightIndexSeargent][widthIndexSeargent] = new Piece(Rank.SERGEANT, playerB);
            printOutCurrentBoard();

            System.out.println("Where do u want to place Major nr " + i + " HW = ");
            String luitenantIndex = sc.next();
            int heightIndexLuitenant = Character.digit(luitenantIndex.charAt(0),10);
            int widthIndexLuitenant = Character.digit(luitenantIndex.charAt(1),10);
            piecesTeamA[heightIndexLuitenant][widthIndexLuitenant] = new Piece(Rank.LUITENANT, playerA);
            piecesTeamB[heightIndexLuitenant][widthIndexLuitenant] = new Piece(Rank.LUITENANT, playerB);
            printOutCurrentBoard();

            System.out.println("Where do u want to place Captain nr " + i + " HW = ");
            String captainIndex = sc.next();
            int heightIndexCaptain = Character.digit(captainIndex.charAt(0),10);
            int widthIndexCaptain = Character.digit(captainIndex.charAt(1),10);
            piecesTeamA[heightIndexCaptain][widthIndexCaptain] = new Piece(Rank.CAPTAIN, playerA);
            piecesTeamB[heightIndexCaptain][widthIndexCaptain] = new Piece(Rank.CAPTAIN, playerB);
            printOutCurrentBoard();
        }
        for (int i = 1; i < 4; i++) {
            System.out.println("Where do u want to place Major nr " + i + " HW = ");
            String majorIndex = sc.next();
            int heightIndexMajor = Character.digit(majorIndex.charAt(0),10);
            int widthIndexMajor = Character.digit(majorIndex.charAt(1),10);
            piecesTeamA[heightIndexMajor][widthIndexMajor] = new Piece(Rank.MAJOR, playerA);
            piecesTeamB[heightIndexMajor][widthIndexMajor] = new Piece(Rank.MAJOR, playerB);
            printOutCurrentBoard();
        }
        for (int i = 0; i < 2; i++) {
            System.out.println("Where do u want to place Colonel nr " + i + " HW = ");
            String colonelIndex = sc.next();
            int heightIndexColonel = Character.digit(colonelIndex.charAt(0),10);
            int widthIndexColonel = Character.digit(colonelIndex.charAt(1),10);
            piecesTeamA[heightIndexColonel][widthIndexColonel] = new Piece(Rank.COLONEL, playerA);
            piecesTeamB[heightIndexColonel][widthIndexColonel] = new Piece(Rank.COLONEL, playerB);
            printOutCurrentBoard();
        }
        System.out.println("Where do u want to place the Marshal? HW= ");
        String marshalIndex = sc.next();
        int heightIndexMarshal = Character.digit(marshalIndex.charAt(0),10);
        int widthIndexMarshal = Character.digit(marshalIndex.charAt(1),10);
        piecesTeamA[heightIndexMarshal][widthIndexMarshal] = new Piece(Rank.MARSHAL, playerA);
        piecesTeamB[heightIndexMarshal][widthIndexMarshal] = new Piece(Rank.MARSHAL, playerB);
        printOutCurrentBoard();

        System.out.println("Where do u want to place the General? HW= ");
        String generalIndex = sc.next();
        int heightIndexGeneral = Character.digit(generalIndex.charAt(0),10);
        int widthIndexGeneral = Character.digit(generalIndex.charAt(1),10);
        piecesTeamA[heightIndexGeneral][widthIndexGeneral] = new Piece(Rank.GENERAL, playerA);
        piecesTeamB[heightIndexGeneral][widthIndexGeneral] = new Piece(Rank.GENERAL, playerB);
        printOutCurrentBoard();

        System.out.println("Where do u want to place the Spy? HW= ");
        String spyIndex = sc.next();
        int heightIndexSpy = Character.digit(spyIndex.charAt(0),10);
        int widthIndexSpy = Character.digit(spyIndex.charAt(1),10);
        piecesTeamA[heightIndexSpy][widthIndexSpy] = new Piece(Rank.SPY, playerA);
        piecesTeamB[heightIndexSpy][widthIndexSpy] = new Piece(Rank.SPY, playerB);
        printOutCurrentBoard();

        System.out.println("Where do u want to place the Flag? HW= ");
        String flagIndex = sc.next();
        int heightIndexFlag = Character.digit(flagIndex.charAt(0),10);
        int widthIndexFlag = Character.digit(flagIndex.charAt(1),10);
        piecesTeamA[heightIndexFlag][widthIndexFlag] = new Piece(Rank.FLAG, playerA);
        piecesTeamB[heightIndexFlag][widthIndexFlag] = new Piece(Rank.FLAG, playerB);
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
            System.out.print(i+6);
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