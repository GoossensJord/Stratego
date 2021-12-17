import board.Board;
import board.Player;
import pieces.Piece;
import pieces.Rank;

import java.util.ArrayList;

public class TestMain {
    public static void main(String[] args) {
        Board board = new Board();
        Player pl = new Player("jord",board,null);
        Piece p = new Piece(Rank.MAJOR,pl);


        board.fillCharArray();
        board.makeBoard();

        p.setPosition(new int[]{8, 0});
        p.move();
    }
}
