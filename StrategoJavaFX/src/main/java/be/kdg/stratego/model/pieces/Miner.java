package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.player.Player;



/**
 * Class responsible for Miner, extends Piece
 */
public class Miner extends Piece {
    /**
     * Constructor for Miner, initialises its rank, its player and the x y value on the board.
     */
    public Miner(Rank r, Player player, int x, int y) {
        super(r, player, x, y);
    }
}