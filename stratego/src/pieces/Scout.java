package pieces;

import player.Player;

import java.util.List;

//moves any distance horizontal & vertical (no leaping over)
public class Scout extends Piece {
    public Scout(Rank scout, Player player, int x, int y) {
        super(Rank.SCOUT, player, x, y);
    }

    public List<int[]> getCrossPositions() {

        for (int i = 0; i < 10; i++) {
            int[] posarrX = new int[]{this.x, i};
            int[] posarrY = new int[]{i, this.y};
            if(this.player.getBoard().spaceAvailable(posarrX[0],posarrX[1])){movableSquares.add(posarrX);}
            if(this.player.getBoard().spaceAvailable(posarrY[0],posarrY[1])){movableSquares.add(posarrY);}
        }
        return movableSquares;
    }

}
