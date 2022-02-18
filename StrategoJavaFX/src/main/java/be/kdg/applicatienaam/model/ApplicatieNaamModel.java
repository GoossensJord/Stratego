package be.kdg.applicatienaam.model;
import be.kdg.applicatienaam.model.board.Board;
import be.kdg.applicatienaam.model.board.BoardMaker;
import be.kdg.applicatienaam.model.board.Square;
import be.kdg.applicatienaam.model.player.Player;

public class ApplicatieNaamModel {
    private final BoardMaker boardMaker;
    private final Board board;
    private final Player pl;
    private final Player pl2;
    // public static final constanten
// private attributen
    public ApplicatieNaamModel() {
        boardMaker = new BoardMaker();
        board = new Board(boardMaker);
        pl = new Player(1, "Jord", board);
        pl2 = new Player(2, "Michiel", board);
// Constructor
    }
    public void playStratego(){
        boardMaker.randomlyPlacePieces(pl,pl2);
    }
// implementatie logica van de
// applicatie ahv methods
// implementatie van de nodige Getters
// implementatie van de nodige Setters


    public Square[][] getBoard() {
        return board.getBord();
    }
}