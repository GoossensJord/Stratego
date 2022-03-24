package be.kdg.stratego.model.player;


import be.kdg.stratego.model.board.Board;
import be.kdg.stratego.model.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class responsible for holding all Player data
 */
public class Player {
    private String name;
    private final Board board;
    private final int id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (id != player.id) return false;
        if (!Objects.equals(name, player.name)) return false;
        if (!Objects.equals(board, player.board)) return false;
        return Objects.equals(piecesList, player.piecesList);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (board != null ? board.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (piecesList != null ? piecesList.hashCode() : 0);
        return result;
    }

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