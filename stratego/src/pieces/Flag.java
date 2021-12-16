package pieces;

//immovable, capture = win
public class Flag extends Piece {
    public Flag(Rank r, boolean isMoveable, boolean canAttack) {
        super.rank = r;
        super.isMoveable = false;
        super.canAttack = false;
    }


}
