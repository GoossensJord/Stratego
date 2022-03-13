package be.kdg.applicatienaam.model;

import be.kdg.applicatienaam.model.board.Board;
import be.kdg.applicatienaam.model.board.BoardMaker;
import be.kdg.applicatienaam.model.board.Square;
import be.kdg.applicatienaam.model.pieces.Piece;
import be.kdg.applicatienaam.model.pieces.Rank;
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
        pl = new Player(0, "Jord", board);
        pl2 = new Player(1, "Michiel", board);
    }

    public void fillRandomly() {
        boardMaker.makePieces(pl, pl2);
        boardMaker.shufflePieces();
        boardMaker.placePieces();
    }

    public void fillManually(){

    }

    public List<int[]> getMoves(Piece p) {
        if(p == null) return null;
        if (p instanceof Scout) return ((Scout) p).allMoves();
        else if (p.availableSquares(p.getX(), p.getY()) != null) {
            return p.availableSquares(p.getX(), p.getY());
        }
        return null;
    }

    public List<int[]> getAttacks(Piece p) {
        if(p == null) return null;
        if (p.getAttacks(p.getX(), p.getY()) != null) {
            return p.getAttacks(p.getX(), p.getY());
        }
        return null;
    }

    public boolean isMatchupWinner(Piece p, Piece p1){
        if(p.getRank().equals(Rank.MINER) && p1.getRank().equals(Rank.BOMB)) return true;
        if(p.getRank().equals(Rank.SPY) && p1.getRank().equals(Rank.MARSHAL)) return true;
        return p.getRankPower() > p1.getRankPower();

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

    public void makeChosenAttack(int[] attack, Piece p) {
        board.makeAttack(attack, p);
    }

    public List<String> getAllPiecesString() {
        return boardMaker.getListView();
    }

    public void makePieceByString(String pieceString,int x,int y){
        for (Rank r : Rank.values()) {
            if(r.getName().substring(0,2).equals(pieceString)){
                Piece p = new Piece(r,pl,x,y);
                boardMaker.manualListChecker(p);
                System.out.println(boardMaker.manualPieceSelection(p));
                break;
            }
        }
    }
    public List<int[]> piecesOnePlayer(int id){
        return boardMaker.getPiecesOnePlayer(id);
    }
}