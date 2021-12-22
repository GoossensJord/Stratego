package pieces;

import board.Player;

//can defuse bombs
public class Miner extends Piece{
    public Miner(Rank r, Player player, int x, int y) {
        super(r, player, x, y);
    }
    @Override
    public Piece attack(Piece piece) {
        if(piece instanceof Bomb) {
            piece.setDeadOrAlive(false);
            return this;
        }
        else if (piece.getRankPower() < this.getRankPower()){
            piece.setDeadOrAlive(false);
            return this;
        }
        else {
            this.setDeadOrAlive(false);
            return piece;
        }
    }
}
