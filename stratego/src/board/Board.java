package board;

import pieces.Piece;
import pieces.Rank;

import java.util.Scanner;

public class Board {

    public int boardHeight = 20;
    public int boardWidth = 20;
    private final int SQUARE_ARRAY_WIDTH = Math.abs((boardWidth + 1) / 2);
    private final int SQUARE_ARRAY_HEIGHT = Math.abs((boardHeight + 1) / 6);


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

            assignPiece(Rank.BOMB, playerA);
            assignPiece(Rank.BOMB, playerB);

        }
        for (int i = 1; i < 9; i++) {
            assignPiece(Rank.SCOUT, playerA);
            assignPiece(Rank.SCOUT, playerB);

        }
        for (int i = 1; i < 6; i++) {

            assignPiece(Rank.MINER, playerA);
            assignPiece(Rank.MINER, playerB);

        }
        for (int i = 1; i < 5; i++) {

            assignPiece(Rank.SERGEANT, playerA);
            assignPiece(Rank.SERGEANT, playerB);

            assignPiece(Rank.LUITENANT, playerA);
            assignPiece(Rank.LUITENANT, playerB);

            assignPiece(Rank.CAPTAIN, playerA);
            assignPiece(Rank.CAPTAIN, playerB);

        }
        for (int i = 1; i < 4; i++) {

            assignPiece(Rank.MAJOR, playerA);
            assignPiece(Rank.MAJOR, playerB);


        }
        for (int i = 0; i < 2; i++) {

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
        String index = " ";
        int heightIndex = 0;
        int widthIndex = 0;

        while (validNumberRange) {
            validAmountOfNumbers = true;
            while (validAmountOfNumbers) {
                System.out.println(player.getName() + ", where do u want to place the " + rank.getName() + "? ");
                index = sc.next();
                validAmountOfNumbers = ammountOfInputCharacterCheck(index);
            }
            heightIndex = Character.digit(index.charAt(0), 10);
            widthIndex = Character.digit(index.charAt(1), 10);

            if (player.getId() != 1) {
                heightIndex = heightIndex - 6;
            }

           validNumberRange = rangeOfInputNumbersCheck(heightIndex,widthIndex);
        }
        if (player.getId() == 1) {
            piecesTeamA[heightIndex][widthIndex] = new Piece(rank, player);
        } else {
            piecesTeamB[heightIndex][widthIndex] = new Piece(rank, player);
        }

        printOutCurrentBoard();
    }


    public boolean rangeOfInputNumbersCheck(int heightIndex, int widthIndex){

        if (heightIndex <= 3 && widthIndex <= 9 && heightIndex >= 0) {
            return false;

        } else {
            System.out.println("incorrect number range");
            return true;

        }
    }

    public boolean ammountOfInputCharacterCheck(String input) {
        if (input.toString().length() != 2) {
            System.out.println("Enter two numbers no spaces");
            return true;
        }
        else return false;
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
            System.out.print(i + 6);
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
    //xd

}