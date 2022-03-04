package be.kdg.applicatienaam.model.pieces;
import be.kdg.applicatienaam.model.player.Player;
import javafx.scene.image.Image;

//can be captured by the Spy if the Spy attacks first
public class Marshal extends Piece {
    boolean captured;

    public Marshal(Rank r, Player player, int x, int y) {
        super(r, player, x, y);
        this.captured = false;
        this.image = new Image("Marshal.png");
    }


}
