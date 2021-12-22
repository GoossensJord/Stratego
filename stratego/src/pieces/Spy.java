package pieces;
//defeats the Marshal if he attacks first
public class Spy extends Piece{
    @Override
    public Piece attack(Piece piece) {
        if(piece instanceof Marshal){
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
