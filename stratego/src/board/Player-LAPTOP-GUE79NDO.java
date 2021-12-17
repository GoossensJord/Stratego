package board;

import pieces.Piece;

public class Player {
    public String name;
    public Board board;
    //placeholder for piece functions.
    //noteven , leaving it here just in case
    //Piece[] pieces;

    public Player(String name,Board b,Piece[] pieces){
        this.name = name;
        this.board = b;
        //this.pieces = pieces;
    }
    public void play(Board board){
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }
}
