package pieces;
//can be captured by the Spy if the Spy attacks first
public class Marshal extends Piece {
    boolean captured;
    public Marshal(){
        super();
        this.captured = false;
    }

    @Override
    public Piece attack(Piece piece) {
        return super.attack(piece);
    }
}
