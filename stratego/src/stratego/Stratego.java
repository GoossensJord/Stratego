package stratego;

import board.Board;
import board.BoardMaker;
import player.Player;
import pieces.Piece;


import java.util.Scanner;


public class Stratego {

    private final BoardMaker boardMaker = new BoardMaker();
    private final Board board = new Board(boardMaker);
    private final Player pl = new Player(1, "Jord", board);
    private final Player pl2 = new Player(2, "Michiel", board);
    boolean flagTaken = true;


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


        while (flagTaken) {
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

                if(board.attackPossible(tomovePlayerTwo.getX(), tomovePlayerTwo.getY(), player).size() != 0){
                    System.out.println("Would you like to attack or move?  1: Move  2: Attack");
                    int answer = sc.nextInt();
                    if (answer == 2) {
                        board.makeAttack(tomovePlayerTwo, player);
                    }
                    else board.makeMove(tomovePlayerTwo,player);

                }
                else board.makeMove(tomovePlayerTwo,player);
            } else System.out.println("This piece is not yours " + player.getName());

        }
    }
}


