package be.kdg.applicatienaam.model.player;


import be.kdg.applicatienaam.model.board.Board;
import be.kdg.applicatienaam.model.pieces.Piece;

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

    public int getId() {
        return this.id;
    }


}