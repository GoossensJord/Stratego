package stratego;

import board.Board;
import board.BoardMaker;
import pieces.Rank;
import pieces.Scout;
import player.Player;
import pieces.Piece;


import java.util.Scanner;


public class Stratego {

    private BoardMaker boardMaker = new BoardMaker();
    private Board board = new Board(boardMaker);
    private Player pl = new Player(1, "Jord", board);
    private Player pl2 = new Player(2, "Michiel", board);


    Scanner sc = new Scanner(System.in);


    public void playStratego() {
        boardMaker.fillWithSquares(board);
        System.out.println("Welcome to Stratego! \n 1: Play Classic Stratego \n 2: Play Randomfill Stratego \n 3: Exit game \n");
        int menuChoice = sc.nextInt();

        if (menuChoice == 1) {
            board.fillBoard(pl);
            board.fillBoard(pl2);
        } else if (menuChoice == 2) {
            boardMaker.randomlyPlacePieces(pl, pl2);
        } else if (menuChoice == 3) {
            System.exit(420);
        } else {
            System.out.println("Error, incorrect input \n");
            playStratego();
        }


        while (true) {
            System.out.println("\nSTRATEGO \n");
            System.out.println(pl.getName().toUpperCase());
            boardMaker.printOutCurrentBoard();
            System.out.println(pl2.getName().toUpperCase());
            madeMove(pl);
            System.out.println("\nSTRATEGO \n");
            System.out.println(pl.getName().toUpperCase());
            boardMaker.printOutCurrentBoard();
            System.out.println(pl2.getName().toUpperCase());
            madeMove(pl2);
        }
    }



    private void madeMove(Player player) {
        boolean madeMoveTwo = true;
        while (madeMoveTwo) {

            Piece tomovePlayerTwo = board.choosePiece(player);

            if (tomovePlayerTwo.getPlayer().equals(player)) {
                madeMoveTwo = false;

                board.makeMove(tomovePlayerTwo,player);
            } else System.out.println("This piece is not yours " + player.getName());

        }
    }
}


