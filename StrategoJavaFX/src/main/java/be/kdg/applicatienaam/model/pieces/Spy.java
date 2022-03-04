package be.kdg.applicatienaam.model.pieces;


import be.kdg.applicatienaam.model.player.Player;
import javafx.scene.image.Image;

//defeats the Marshal if he attacks first
public class Spy extends Piece {

    public Spy(Rank r, Player player, int x, int y) {
        super(r, player, x, y);
        this.image = new Image("Spy.png");
    }


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

