package be.kdg.applicatienaam.model;

import be.kdg.applicatienaam.model.board.Board;
import be.kdg.applicatienaam.model.board.BoardMaker;
import be.kdg.applicatienaam.model.board.Square;
import be.kdg.applicatienaam.model.pieces.Piece;
import be.kdg.applicatienaam.model.pieces.Scout;
import be.kdg.applicatienaam.model.player.Player;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class StrategoModel {
    private final BoardMaker boardMaker;
    private final Board board;
    private final Player pl;
    private final Player pl2;

    // public static final constanten
// private attributen
    public StrategoModel() {
        boardMaker = new BoardMaker();
        board = new Board(boardMaker);
        pl = new Player(1, "Jord", board);
        pl2 = new Player(2, "Michiel", board);
// Constructor
    }

    public void playStratego() {
        boardMaker.randomlyPlacePieces(pl, pl2);

    }

// implementatie logica van de
// applicatie ahv methods
// implementatie van de nodige Getters
// implementatie van de nodige Setters

    public List<int[]> getMoves(Piece p) {

        if (p instanceof Scout) return ((Scout) p).allMoves();
        else if (!(p.availableSquares(p.getX(), p.getY()) == null)) return p.availableSquares(p.getX(), p.getY());
        else return new ArrayList<>()   ;
    }

    public Piece choosePiece(int x, int y) {
        return board.getBord()[x][y].getPiece();
    }


    public Square[][] getBoard() {
        return boardMaker.getSquaresBoard();
    }
}