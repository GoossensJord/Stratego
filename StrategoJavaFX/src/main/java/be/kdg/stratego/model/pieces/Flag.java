package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.player.Player;


/**
 * Class responsible for Flag, extends Piece
 */
public class Flag extends Piece {

    /**
     *Initialises the Flag, gives it a rank, a player and an x y coordinate for the board. Also uses piece variables to declare this as an immovable piece that cannot attack.
     */
    public Flag(Rank r, Player player, int x, int y) {
        super(r, player, x, y);
        super.isMovable = false;
        super.canAttack = false;



    }
}
