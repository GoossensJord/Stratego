package pieces;
//defeats the Marshal if he attacks first
public class Spy extends Piece{
    @Override
    public Piece attack(Piece piece) {
        if(piece instanceof Marshal) ((Marshal) piece).captured = true;
        return this;
    }
}
