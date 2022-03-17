package be.kdg.stratego.model;


import be.kdg.stratego.model.board.Square;
import be.kdg.stratego.model.player.Player;

public class GameSaveState {
    Square[][] boardState;
    Player playerTurn;

    public GameSaveState(Player startPlayer, Square[][] startBoard) {
        this.playerTurn = startPlayer;
        this.boardState = startBoard;
    }

    public void setBoardState(Square[][] boardState) {
        this.boardState = boardState;
    }

    public void setPlayerTurn(Player playerTurn) {
        this.playerTurn = playerTurn;
    }

    public Player getPlayerTurn() {
        return playerTurn;
    }
}
