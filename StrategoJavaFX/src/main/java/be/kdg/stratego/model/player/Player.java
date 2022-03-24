package be.kdg.stratego.model.player;


import be.kdg.stratego.model.board.Board;
import be.kdg.stratego.model.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for holding all Player data
 */
public class Player {
    private String name;
    private Board board;
    private int id;
    List<Piece> piecesList;

    /**
     * Constructor for Player, it initialises the name, the board the player is playing on, the player ID and the pieceList for this player
     */
    public Player(int id, String name, Board b) {
        this.name = name;
        this.board = b;
        this.id = id;
        piecesList = new ArrayList<>();
    }


    /**
     * A method that returns the piece list of the player that calls the method
     */
    public List<Piece> getPiecesList(){
        piecesList = board.getPieceListByID(this.id);
        return this.piecesList;
    }

   /* public void setPiecesList(List<Piece> pieceList){
        this.piecesList = pieceList;
    }*/
    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Board getBoard() {
        return this.board;
    }

    public String getName() {
        return this.name;
    }
}