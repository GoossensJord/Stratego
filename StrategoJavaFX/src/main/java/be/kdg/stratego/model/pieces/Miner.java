package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.player.Player;
import javafx.scene.image.Image;


//can defuse bombs
public class Miner extends Piece {
    public Miner(Rank r, Player player, int x, int y) {
        super(r, player, x, y);
        this.image = new Image("Miner.png");
    }

    public Piece attack(Piece piece) {
        if (piece instanceof Bomb) {
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