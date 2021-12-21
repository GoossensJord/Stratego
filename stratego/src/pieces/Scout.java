package pieces;

import board.Board;
import board.Player;

import java.util.List;

//moves any distance horizontal & vertical (no leaping over)
public class Scout extends Piece {
   /* public Scout(Rank scout, Player pl) {
        super(scout,pl);
    }

    public List<int[]> getCrossPositions(){
        int curx = getX();
        int cury = getY();

        for (int i = 0; i < 20; i++) {
            int[] posarrX = new int[]{curx,i};
            int[] posarrY = new int[]{i,cury};
            if(!(super.outOfBoundsOrOccupied(posarrX)&& super.outOfBoundsOrOccupied(posarrY))) {
                moveableSquares.add(new int[]{curx, i});
                moveableSquares.add(new int[]{i, cury});
            }
        }
        return moveableSquares;
    }*/
}
