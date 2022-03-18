package be.kdg.stratego.model.player;


import be.kdg.stratego.model.board.Board;
import be.kdg.stratego.model.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private Board board;
    private int id;
    List<Piece> piecesList;

    public Player(int id, String name, Board b) {
        this.name = name;
        this.board = b;
        this.id = id;
        piecesList = new ArrayList<>();
    }

    public Board getBoard() {
        return this.board;
    }

    public String getName() {
        return this.name;
    }

    public List<Piece> getPiecesList(){
        piecesList = board.getPieceListByID(this.id);
        return this.piecesList;
    }
    public int getId() {
        return this.id;
    }



}