package board;

public class Player {
    public String name;
    public Board board;


    public Player(String name,Board b){
        this.name = name;
        this.board = b;
    }
    public void play(Board board){
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }
}
