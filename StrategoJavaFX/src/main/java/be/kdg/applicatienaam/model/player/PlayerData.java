package be.kdg.applicatienaam.model.player;

import be.kdg.applicatienaam.model.pieces.*;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlayerData {

    public List<Piece> createRandomPieceList(Player player) {

        List<Piece> piecesList = Arrays.asList(
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

        Collections.shuffle(piecesList);

        return piecesList;
    }
}
