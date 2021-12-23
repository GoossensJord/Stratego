package stratego;

import board.Board;
import board.Player;
import pieces.Piece;
import pieces.Rank;
import pieces.Scout;

public class Stratego {
    Board board = new Board();
    Player pl = new Player(1, "jord", board);
    Player pl2 = new Player(2, "michiel", board);
    Piece s = new Scout(Rank.SCOUT, pl, 5, 5);

    public void playStratego() {
//        board.fillBoard(pl);
//        board.fillBoard(pl2);


//        board.fillBoard(pl);
//        board.availableSquares(3,5);
//        board.fillBoard(pl2);
        board.randomlyPlacePiecesData(pl);
        board.printOutCurrentBoard();
        Piece tomove = board.choosePiece();
        board.makeMove(tomove);
        board.printOutCurrentBoard();


    }
}
