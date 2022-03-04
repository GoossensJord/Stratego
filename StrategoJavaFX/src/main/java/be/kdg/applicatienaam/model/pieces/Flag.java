package be.kdg.applicatienaam.model.pieces;

import be.kdg.applicatienaam.model.player.Player;


//immovable, capture = win
public class Flag extends Piece {
    public Flag(Rank r, Player player, int x, int y) {
        super(r, player, x, y);
        super.isMovable = false;
        super.canAttack = false;



    }
}
