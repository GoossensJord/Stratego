package pieces;

import player.Player;

//defeats the Marshal if he attacks first
public class 2Spy extends Piece {

    public Spy(Rank r, Player player, int x, int y) {
        super(r, player, x, y);
    }

    @Override
    public Piece attack(Piece piece) {
        if (piece instanceof Marshal) {
            piece.setDeadOrAlive(false);
            return this;
        } else if (piece.getRankPower() < this.getRankPower()) {
            piece.setDeadOrAlive(false);
            return this;
        } else {
            this.setDeadOrAlive(false);
            return piece;
        }
    }
}
