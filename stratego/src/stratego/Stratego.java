package stratego;

import board.Board;
import board.Player;
import pieces.Piece;
import pieces.Rank;

public class Stratego {
    Board board = new Board();
    Player pl = new Player(1,"jord",board);
    Player pl2 = new Player(2,"michiel",board);

    public void playStratego(){
        board.fillBoard(pl,pl2);
        Piece p = new Piece(Rank.MAJOR,pl);
       // p.setPosition(new int[]{5, 0});
        // p.move();
    }
}
