package pieces;

import board.Board;
import board.Player;

import java.util.List;

//moves any distance horizontal & vertical (no leaping over)
public class Scout extends Piece {
    public Scout(Rank scout, Player pl) {
        super(scout,pl);
    }

    public List<int[]> getCrossPositions(){
        for (int i = 0; i < this.player.getBoard().getBoardHeight(); i++) {
            moveableSquares.add(new int[]{this.getPosition()[0],i});
            moveableSquares.add(new int[]{i,this.getPosition()[0]});
        }
        return moveableSquares;
    }
}
