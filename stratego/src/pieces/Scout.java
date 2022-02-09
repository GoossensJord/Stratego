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
}
