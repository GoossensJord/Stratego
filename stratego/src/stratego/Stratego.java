package stratego;

import board.Board;
import board.BoardMaker;
import player.Player;
import pieces.Piece;

import java.util.Scanner;


public class Stratego {

    private BoardMaker boardMaker = new BoardMaker();
    private Board board = new Board(boardMaker);
    private Player pl = new Player(1, "jord", board);
    private Player pl2 = new Player(2, "michiel", board);

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
            boardMaker.printOutCurrentBoard();
            Piece tomove = board.choosePiece();
            board.makeMove(tomove);

        }
    }
}
