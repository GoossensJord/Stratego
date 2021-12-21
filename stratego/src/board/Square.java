package board;

import pieces.Piece;

public class Square {
    private boolean isOccupied;
    private Board board;
    public Piece p;
    public Square(Board b){
        this.isOccupied = true;
        this.board = b;
    }

    public void setPiece(Piece p){
        this.p = p;
        isOccupied = true;
    }
    public boolean getIsOccupied() {
        return isOccupied;
    }
}
