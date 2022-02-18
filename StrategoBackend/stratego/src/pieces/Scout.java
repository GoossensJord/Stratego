package pieces;

import player.Player;

import java.util.ArrayList;
import java.util.List;

//moves any distance horizontal & vertical (no leaping over)
public class Scout extends Piece {
    private boolean firstMove = true;

    public Scout(Rank scout, Player player, int x, int y) {
        super(Rank.SCOUT, player, x, y);

        movableSquares = new ArrayList<List<int[]>>();
        for (int i = 0; i < 4; i++) movableSquares.add(new ArrayList<int[]>());

    }

    public List<int[]> allMoves() {
        if (firstMove) {
            this.firstMove = false;
            return firstmove();
        } else {
            List<int[]> allmoves = new ArrayList<>(xMoveable());
            allmoves.addAll(yMoveable());

            return allmoves;
        }

    }

    private List<int[]> firstmove() {

        List<int[]> startmoves = new ArrayList<>();
        if (this.getPlayer().getId() == 1) {
            startmoves.add(new int[]{this.x + 1, this.y});
            startmoves.add(new int[]{this.x + 2, this.y});
        } else {
            startmoves.add(new int[]{this.x - 1, this.y});
            startmoves.add(new int[]{this.x - 2, this.y});
        }
        return startmoves;
    }

    public List<int[]> yMoveable() {
        List<int[]> moves = new ArrayList<>();
        int xval = this.x;

        for (int i = xval+1; i < 10; i++) {
            if (this.getPlayer().getBoard().spaceAvailable(i, this.y)) moves.add(new int[]{i, this.y});
            else break;
        }
        for (int i = xval - 1; i >= 0; i--) {
            if (this.getPlayer().getBoard().spaceAvailable(i, this.y)) moves.add(new int[]{i, this.y});
            else break;
        }
        return moves;

    }

    public List<int[]> xMoveable() {
        List<int[]> moves = new ArrayList<>();
        int yval = this.y;

        for (int i = yval+1; i < 10; i++) {
            if (this.getPlayer().getBoard().spaceAvailable(this.x, i)) moves.add(new int[]{this.x, i});
            else break;
        }
        for (int i = yval - 1; i >= 0; i--) {
            if (this.getPlayer().getBoard().spaceAvailable(this.x, i)) moves.add(new int[]{this.x, i});
            else break;
        }
        return moves;

    }
}
