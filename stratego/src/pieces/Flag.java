package pieces;

import player.Player;

//immovable, capture = win
public class Flag extends Piece {
    public Flag(Rank r, Player player, int x, int y) {
        super.rank = r;
        super.isMoveable = false;
        super.canAttack = false;
    }
}
