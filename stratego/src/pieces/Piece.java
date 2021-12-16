package pieces;

import java.util.ArrayList;
import java.util.List;

public class Piece {
    Rank rank;
    int[] position;
    boolean deadOrAlive;
    boolean isMoveable;
    boolean canAttack;
    List<int[]> moveableSquares;

    public Piece(Rank r) {
        this.rank = r;
        this.position = new int[2];
        this.deadOrAlive = true;
        this.isMoveable = true;
        this.canAttack = true;
        moveableSquares = new ArrayList<int[]>();
    }

    public Piece() {
        this.position = new int[2];
        this.deadOrAlive = true;
    }

    public void move(){
        System.out.println("Where would you like to move");
        for (int i = 0; i < 4; i++) {
            moveableSquares.get(i) = new int[2];
        }

    }
}
