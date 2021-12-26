package pieces;

import player.Player;

import java.util.List;

//moves any distance horizontal & vertical (no leaping over)
public class Scout extends Piece {
    public Scout(Rank scout, Player player, int x, int y) {
        super(Rank.SCOUT, player, x, y);
    }

    public List<int[]> getCrossPositions(int boardSize) {

        for (int i = 0; i < boardSize; i++) {
            int[] posarrX = new int[]{this.x, i};
            int[] posarrY = new int[]{i, this.y};

            movableSquares.add(posarrX);
            movableSquares.add(posarrY);
        }
        return movableSquares;
    }
}
