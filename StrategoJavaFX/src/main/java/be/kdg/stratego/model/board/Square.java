package be.kdg.stratego.model.board;

import be.kdg.stratego.model.pieces.Piece;

public class Square {


    private boolean isOccupied;
    Board b;
    public Piece p;

    public Square(Board b) {
        this.isOccupied = false;
        this.b = b;


    }

    public Piece getPiece() {
        return this.p;
    }

    public void setPiece(Piece p) {
        this.p = p;
        this.isOccupied = true;
    }

    public boolean getIsOccupied() {
        return this.isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public void removePiece() {
        this.p = null;
        isOccupied = false;
    }
}


