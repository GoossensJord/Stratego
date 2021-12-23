package board;



public class Player {
    private String name;
    private Board board;
    private int id;

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
        return this.board;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }
}

