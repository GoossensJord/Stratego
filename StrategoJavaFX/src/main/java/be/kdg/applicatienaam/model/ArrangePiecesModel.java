/*package be.kdg.applicatienaam.model;

import be.kdg.applicatienaam.model.board.Board;
import be.kdg.applicatienaam.model.board.BoardMaker;
import be.kdg.applicatienaam.model.pieces.Piece;
import be.kdg.applicatienaam.model.pieces.Rank;
import be.kdg.applicatienaam.model.player.Player;

import java.util.List;

public class ArrangePiecesModel {
    private BoardMaker boardMaker;
    private Board board;
    public ArrangePiecesModel() {
        boardMaker = new BoardMaker();
        board = new Board(boardMaker);
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
}
*/