package be.kdg.stratego.model.pieces;
import be.kdg.stratego.model.player.Player;

/**
 * Class responsible for Marshal, extends Piece
 */
public class Marshal extends Piece {
    boolean captured;
    /**
     * Constructor for Marshal, initialises its rank, its player and the x y value on the board.
     **/
    public Marshal(Rank r, Player player, int x, int y) {
        super(r, player, x, y);
        this.captured = false;
    }


}
