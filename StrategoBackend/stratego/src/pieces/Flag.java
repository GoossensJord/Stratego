package pieces;

import player.Player;

//immovable, capture = win
public class Flag extends Piece {
    public Flag(Rank r, Player player, int x, int y) {
        super(r, player, x, y);
        super.isMovable = false;
        super.canAttack = false;
    }
}
