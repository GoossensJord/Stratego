package pieces;

import player.Player;

//immovable, is captured by miner
public class Bomb extends Piece{
    public Bomb(Rank r, Player player, int x, int y) {
        super(r, player, x, y);
        this.isMoveable = false;
        this.canAttack = false;
    }
}
