package be.kdg.stratego.model.board;

import be.kdg.stratego.model.pieces.Piece;

/**
 * Class responsible for the Squares on a board, the board is first filled with squares before it is filled with pieces.
 */
public class Square {


    private boolean isOccupied;
    public Piece p;

    /**
     * Constructor for square, as a square is initialised the boolean isOccupied is set to false, because no piece has been put on the square yet.
     */
    public Square() {
        this.isOccupied = false;


    }

    /**
     * Removes piece from a square
     */
    public void removePiece() {
        this.p = null;
        isOccupied = false;
    }

    /**
     *Sets a piece on the square, also sets the boolean isOccupied to true
     * */
    public void setPiece(Piece p) {
        this.p = p;
        this.isOccupied = true;
    }

    public Piece getPiece() {
        return this.p;
    }



    public boolean getIsOccupied() {
        return this.isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }


}


