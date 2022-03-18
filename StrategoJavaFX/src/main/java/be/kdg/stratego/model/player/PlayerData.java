package be.kdg.stratego.model.player;

import be.kdg.stratego.model.pieces.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class responsible for creating a list of pieces
 */
public class PlayerData {
    List<Piece> piecesList;

    public PlayerData() {
        piecesList = new ArrayList<>();
    }

    /**
     * Creates the list of pieces
     * @param player When pieces are initialised they require a player.
     */
    public List<Piece> createPieceList(Player player) {

        piecesList = Arrays.asList(
                new Bomb(Rank.BOMB, player, 0, 0),
                new Bomb(Rank.BOMB, player, 0, 0),
                new Bomb(Rank.BOMB, player, 0, 0),
                new Bomb(Rank.BOMB, player, 0, 0),
                new Bomb(Rank.BOMB, player, 0, 0),
                new Bomb(Rank.BOMB, player, 0, 0),
                new Marshal(Rank.MARSHAL, player, 0, 0),
                new Piece(Rank.GENERAL, player, 0, 0),
                new Piece(Rank.COLONEL, player, 0, 0),
                new Piece(Rank.COLONEL, player, 0, 0),
                new Piece(Rank.MAJOR, player, 0, 0),
                new Piece(Rank.MAJOR, player, 0, 0),
                new Piece(Rank.MAJOR, player, 0, 0),
                new Piece(Rank.CAPTAIN, player, 0, 0),
                new Piece(Rank.CAPTAIN, player, 0, 0),
                new Piece(Rank.CAPTAIN, player, 0, 0),
                new Piece(Rank.CAPTAIN, player, 0, 0),
                new Piece(Rank.LUITENANT, player, 0, 0),
                new Piece(Rank.LUITENANT, player, 0, 0),
                new Piece(Rank.LUITENANT, player, 0, 0),
                new Piece(Rank.LUITENANT, player, 0, 0),
                new Piece(Rank.SERGEANT, player, 0, 0),
                new Piece(Rank.SERGEANT, player, 0, 0),
                new Piece(Rank.SERGEANT, player, 0, 0),
                new Piece(Rank.SERGEANT, player, 0, 0),
                new Miner(Rank.MINER, player, 0, 0),
                new Miner(Rank.MINER, player, 0, 0),
                new Miner(Rank.MINER, player, 0, 0),
                new Miner(Rank.MINER, player, 0, 0),
                new Miner(Rank.MINER, player, 0, 0),
                new Scout(Rank.SCOUT, player, 0, 0),
                new Scout(Rank.SCOUT, player, 0, 0),
                new Scout(Rank.SCOUT, player, 0, 0),
                new Scout(Rank.SCOUT, player, 0, 0),
                new Scout(Rank.SCOUT, player, 0, 0),
                new Scout(Rank.SCOUT, player, 0, 0),
                new Scout(Rank.SCOUT, player, 0, 0),
                new Scout(Rank.SCOUT, player, 0, 0),
                new Spy(Rank.SPY, player, 0, 0),
                new Flag(Rank.FLAG, player, 0, 0)
        );
        return piecesList;
    }

    //todo amnt check
    //todo positioncheck
    public Piece addPieceToPieceList(Piece p) {
        Rank instance = p.getRank();

        int counter = instance.getCounter();
        if (instance.getCounter() < p.getRank().getAmount()) {
            piecesList.add(p);
            counter++;
        }
        p.getRank().setCounter(counter);
        return p;
    }

    public List<Piece> getPiecesList() {
        return piecesList;
    }
}
