package board;

import pieces.Piece;


public class Square {

    private boolean isOccupied;
    private Board board;
    public Piece p;

    public Square(Board b) {
        this.isOccupied = false;
        this.board = b;
    }

    public void setPiece(Piece p) {
        this.p = p;
        this.isOccupied = true;
    }

    public void removePiece() {
        this.p = null;
        isOccupied = false;
    }
    public boolean getIsOccupied() {
        return this.isOccupied;
    }

    public Piece getPiece() {
        return this.p;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    @Override
    public String toString() {
        if (p != null) return this.p.toString();
        else return "  ";
    }
}
