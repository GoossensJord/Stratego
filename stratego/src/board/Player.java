package board;

import pieces.Piece;

public class Player {
    public String name;
    public Board board;
    public int id;
    //placeholder for piece functions.
    //noteven , leaving it here just in case
    //Piece[] pieces;

    public Player(int id,String name,Board b /*,Piece[] pieces*/){
        this.name = name;
        this.board = b;
        this.id = id;
        //this.pieces = pieces;
    }
    public void play(Board board){
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}

