package stratego;

import board.Board;
import board.BoardMaker;
import board.Player;
import pieces.Piece;


public class Stratego {

    private BoardMaker boardMaker = new BoardMaker();
    private Board board = new Board(boardMaker);
    private Player pl = new Player(1, "jord", board);
    private Player pl2 = new Player(2, "michiel", board);


    public void playStratego() {
        boardMaker.fillWithSquares(board);


        //   board.fillBoard(pl);
        //   board.fillBoard(pl2);
        boardMaker.randomlyPlacePieces(pl);
        boardMaker.randomlyPlacePieces(pl2);

        while (true) {
            boardMaker.printOutCurrentBoard();
            Piece tomove = board.choosePiece();
            board.makeMove(tomove);

        }


    }
}
