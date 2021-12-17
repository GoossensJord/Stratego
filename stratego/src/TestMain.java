import board.Board;
import board.Player;
import pieces.Piece;
import pieces.Rank;

import java.util.ArrayList;

public class TestMain {
    public static void main(String[] args) {
        Board board = new Board();
        Player pl = new Player(1,"jord",board);
        Player pl2 = new Player(2,"michiel",board);

        Piece p = new Piece(Rank.MAJOR,pl);


        board.fillCharArray(pl,pl2);


        p.setPosition(new int[]{8, 0});
        p.move();
    }
}
