package be.kdg.applicatienaam.model;

import be.kdg.applicatienaam.model.board.Board;
import be.kdg.applicatienaam.model.board.BoardMaker;
import be.kdg.applicatienaam.model.board.Square;
import be.kdg.applicatienaam.model.pieces.Piece;
import be.kdg.applicatienaam.model.pieces.Scout;
import be.kdg.applicatienaam.model.player.Player;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private final BoardMaker boardMaker;
    private final Board board;
    private final Player pl;
    private final Player pl2;


    public GameModel() {
        boardMaker = new BoardMaker();
        board = new Board(boardMaker);
        pl = new Player(1, "Jord", board);
        pl2 = new Player(2, "Michiel", board);

    }

    public void fillRandomly() {
        boardMaker.randomlyPlacePieces(pl, pl2);
    }

    public List<int[]> getMoves(Piece p) {

        if (p instanceof Scout) return ((Scout) p).allMoves();
        else if (!(p.availableSquares(p.getX(), p.getY()) == null)) {
            return p.availableSquares(p.getX(), p.getY());
        } else return null;
    }

    public Piece choosePiece(int x, int y) {
        return board.getBord()[x][y].getPiece();
    }


    public Square[][] getBoard() {
        return boardMaker.getSquaresBoard();
    }

    public void makeChosenMove(int[] move, Piece p) {
        board.makeMove(move, p);
    }

    public Piece getPiece(int x, int y) {
        return boardMaker.getSquaresBoard()[x][y].getPiece();
    }
}