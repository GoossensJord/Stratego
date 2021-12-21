package stratego;

import board.Board;
import board.Player;
import pieces.Piece;
import pieces.Rank;
import pieces.Scout;

public class Stratego {
    Board board = new Board();
    Piece piece = new Piece();
    Player pl = new Player(1,"jord",board);
    Player pl2 = new Player(2,"michiel",board);

    public void playStratego(){
        board.fillWithEmptyPieces(pl);
       board.randomlyPlacePieces(pl);
       board.randomlyPlacePieces(pl2);
        board.printOutCurrentBoard();

       /* Piece p = new Piece(Rank.MAJOR,pl,0,0);
        Piece s = new Scout(Rank.SCOUT,pl);

        s.setPosition(new int[]{5, 0});
        ((Scout) s).getCrossPositions();*/

    }
}
