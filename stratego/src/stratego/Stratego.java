package stratego;

import board.Board;
import board.BoardMaker;
import player.Player;
import pieces.Piece;

import java.util.Scanner;


public class Stratego {

    private BoardMaker boardMaker = new BoardMaker();
    private Board board = new Board(boardMaker);
    private Player pl = new Player(1, "Jord", board);
    private Player pl2 = new Player(2, "Michiel", board);


    Scanner sc = new Scanner(System.in);


    public void playStratego() throws InterruptedException {
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
            madeMovePlayerOne();
            madeMovePlayerTwo();

        }
    }

    public void madeMovePlayerOne() throws InterruptedException {
        boolean madeMove = true;
        while (madeMove) {
            boardMaker.printOutCurrentBoard();

            Piece tomove = board.choosePiece(pl);
            if (tomove.getPlayer().equals(pl)) {
                madeMove = false;
                board.makeMove(tomove);
            } else {
                System.out.println("This piece is not yours " + pl.getName());
                Thread.sleep(1500);
            }
        }
    }

    public void madeMovePlayerTwo() throws InterruptedException {
        boolean madeMoveTwo = true;
        while (madeMoveTwo) {
            boardMaker.printOutCurrentBoard();

            Piece tomovePlayerTwo = board.choosePiece(pl2);

            if (tomovePlayerTwo.getPlayer().equals(pl2)) {
                madeMoveTwo = false;
                board.makeMove(tomovePlayerTwo);
            } else {
                System.out.println("This piece is not yours " + pl2.getName());
                Thread.sleep(1500);
            }
        }
    }
}


