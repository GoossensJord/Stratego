package board;

import pieces.Piece;
import pieces.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Board {
    //placeholder for input later.
    private int boardHeight = 20;
    private int boardWidth = 20;
    //square arr sizing
    private final int SQUARE_ARRAY_WIDTH = Math.abs((boardWidth + 1) / 2);
    private final int SQUARE_ARRAY_HEIGHT = Math.abs((boardHeight + 1) / 6);

    private Player p1;
    private Player p2;

    Square[][] squaresTeamA = new Square[SQUARE_ARRAY_HEIGHT + 1][SQUARE_ARRAY_WIDTH + 1];
    Square[][] squaresTeamB = new Square[SQUARE_ARRAY_HEIGHT + 1][SQUARE_ARRAY_WIDTH + 1];


    //if piece selected highlight moveable squares
    public Board() {
        this.p1 = new Player(1,"XD", this);
    }

    public void fillWithEmptyPieces() {

        for (int i = 0; i <= SQUARE_ARRAY_HEIGHT; i++) {
            for (int j = 0; j <= SQUARE_ARRAY_WIDTH; j++) {
                if (squaresTeamA[i][j] == null) {
                    squaresTeamA[i][j].setPiece(new Piece(Rank.EMPTY,p1));

                }
                if (squaresTeamB[i][j] == null) {
                    squaresTeamB[i][j].setPiece(new Piece(Rank.EMPTY, p2));
                }
            }
        }
    }
    public void fillBoardRandomly(){ // delen door 10 = rij -> modulo = kolomn
        List<Integer> numbersToRandomize = new ArrayList<>();
        for (int i = 0; i < 39; i++) {
            numbersToRandomize.add(i);
        }
        Collections.shuffle(numbersToRandomize);
        for (Rank r: Rank.values()) {
            for (int i = 0; i < r.getAmnt(); i++) {
                int row = numbersToRandomize.get(i)/10;
                int column = numbersToRandomize.get(i) % 10;
                squaresTeamB[row][column].setPiece(new Piece(r, null));
                squaresTeamA[row][column].setPiece(new Piece(r,null));

            }
        }
    }

    public void fillBoard(Player player){
        for (Rank r: Rank.values()){
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

                if (!rangeOfInputNumbersCheck(heightIndex, widthIndex)) {

                    if (spaceAvailable(rank, player,heightIndex, widthIndex)) {
                        placeTaken = false;
                        validNumberRange = false;
                        printOutCurrentBoard();
                    }

                }
            }
        }


    }


    public boolean spaceAvailable(Rank rank, Player player, int heightIndex, int widthIndex) {

        if (squaresTeamA[heightIndex][widthIndex].getRank().equals(Rank.EMPTY) && player.getId() == 1) {

            squaresTeamA[heightIndex][widthIndex].setPiece(new Piece(rank,player));
            return true;

        } else if (squaresTeamB[heightIndex][widthIndex].getRank().equals(Rank.EMPTY) && player.getId() != 1) {

            squaresTeamB[heightIndex][widthIndex].setPiece(new Piece(rank, player));
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
                System.out.print(squaresTeamA[i][j] + " ");
            }
            System.out.print(i);
            System.out.println();
        }
        System.out.println();
        System.out.println();

        for (int i = 0; i <= SQUARE_ARRAY_HEIGHT; i++) {
            for (int j = 0; j < SQUARE_ARRAY_WIDTH; j++) {
                System.out.print(squaresTeamB[i][j] + " ");
            }
            System.out.print(i);
            System.out.println();
        }
        System.out.println("\n0  1  2  3  4  5  6  7  8  9");
    }

    public void shufflePos(){
        List<int[]> pos = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                pos.add(new int[]{i,j});
            }
        }
        Collections.shuffle(pos);
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