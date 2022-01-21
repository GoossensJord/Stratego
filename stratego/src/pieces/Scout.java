package pieces;

import player.Player;

import java.util.ArrayList;
import java.util.List;

//moves any distance horizontal & vertical (no leaping over)
public class Scout extends Piece {
    protected List<List<int[]>> movableSquares;

    public Scout(Rank scout, Player player, int x, int y) {
        super(Rank.SCOUT, player, x, y);
        movableSquares = new ArrayList<List<int[]>>();
        for (int i = 0; i < 4; i++) movableSquares.add(new ArrayList<int[]>());
    }

    public List<List<int[]>> getCrossPositions() {
        int[] startPos = getstartPos(this.x, this.y);

        for (int i = startPos[0]; i < 10; i++) {
            int[] posarrX = new int[]{this.x, i};
            if (this.player.getBoard().spaceAvailable(posarrX[0], posarrX[1])) {
                if(posarrX[0]<this.x)movableSquares.get(3).add(posarrX);
                else movableSquares.get(0).add(posarrX);
            } else break;
        }
        for (int i = startPos[1]; i < 10; i++) {
            int[] posarrY = new int[]{i, this.y};
            if (this.player.getBoard().spaceAvailable(posarrY[0], posarrY[1])) {
                if(posarrY[1]<this.y)movableSquares.get(2).add(posarrY);
                else movableSquares.get(1).add(posarrY);
            } else break;
        }
        return movableSquares;
    }

    public int[] getstartPos(int posx, int posy) {
        int[] out = new int[2];
        for (int i = posx; i > 0; i--) {
            if (!this.player.getBoard().spaceAvailable(posx, i)) {
                out[0] = ++i;
                break;
            }
        }
        for (int i = posx; i > 0; i--) {
            if (!this.player.getBoard().spaceAvailable(i, i)) {
                out[1] = ++i;
                break;
            }
        }
        return out;
    }
}
