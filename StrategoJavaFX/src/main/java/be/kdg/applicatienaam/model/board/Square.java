package be.kdg.applicatienaam.model.board;

import be.kdg.applicatienaam.model.pieces.Piece;

public class Square{


    private boolean isOccupied;
    Board b;
    String s;
    public Piece p;

    public Square(Board b) {
        this.isOccupied = false;
        this.b = b;
        s = "x";

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

    @Override
    public String toString() {
        if (p != null) return this.p.toString();
        else return "  ";
    }}
