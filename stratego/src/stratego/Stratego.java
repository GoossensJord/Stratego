package stratego;

import board.Board;
import board.Player;
import pieces.Piece;
import pieces.Rank;
import pieces.Scout;

public class Stratego {
    Board board = new Board();
    Player pl = new Player(1,"jord",board);
    Player pl2 = new Player(2,"michiel",board);

    public void playStratego(){
        board.fillWithEmptyPieces(pl,pl2);
        Scout p = (Scout)new Piece(Rank.SCOUT,pl);
        p.setPosition(new int[]{5, 0});
        p.getCrossPositions();
    }
}