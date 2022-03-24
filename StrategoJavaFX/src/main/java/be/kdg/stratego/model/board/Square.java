package be.kdg.stratego.model.board;

import be.kdg.stratego.model.pieces.Piece;

/**
 * Class responsible for the Squares on a board, the board is first filled with squares before it is filled with pieces.
 */
public class Square {


    private boolean isOccupied;
    public Piece p;


    public Square() {
        this.isOccupied = false;


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


