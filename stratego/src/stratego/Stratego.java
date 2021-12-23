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
        board.fillWithSquares(pl);
        board.printOutCurrentBoard();
        board.fillBoard(pl);
        board.fillBoard(pl2);
    }
}
