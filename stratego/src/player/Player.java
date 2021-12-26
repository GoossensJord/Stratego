package player;


import board.Board;

public class Player {
    private String name;
    private Board board;
    private int id;

    public Player(int id, String name, Board b) {
        this.name = name;
        this.board = b;
        this.id = id;
        //this.pieces = pieces;
    }

    public Board getBoard() {
        return this.board;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }
}

