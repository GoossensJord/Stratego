package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.player.Player;

/**
 * Class responsible for the Bomb, extends Piece
 */
public class Bomb extends Piece {


    /**
     * Constructor for Bomb, initialises its rank, its player and the x y value on the board. Also uses piece variables to declare this as an immovable piece that cannot attack.
     */
    public Bomb(Rank r, Player player, int x, int y) {

        super(r, player, x, y);
        this.isMovable = false;
        this.canAttack = false;
    }


}