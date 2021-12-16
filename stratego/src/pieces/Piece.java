package pieces;

import board.Board;
import board.Player;

import java.util.ArrayList;
import java.util.List;

public class Piece {
    Rank rank;
    int[] position;
    boolean deadOrAlive;
    boolean isMoveable;
    boolean canAttack;
    List<int[]> moveableSquares;
    Player player;

    public Piece(Rank r,Player player) {
        this.rank = r;
        this.position = new int[2];
        this.deadOrAlive = true;
        this.isMoveable = true;
        this.canAttack = true;
        moveableSquares = new ArrayList<int[]>();
        this.player = player;
    }

    public Piece() {
        this.position = new int[2];
        this.deadOrAlive = true;
    }

    public void move() {
        System.out.println("Where would you like to move");

        int[] pos1 = {this.position[0], this.position[1] + 1};
        int[] pos2 = {this.position[0], this.position[1] - 1};
        int[] pos3 = {this.position[0] + 1, this.position[1]};
        int[] pos4 = {this.position[0] - 1, this.position[1]};

        moveableSquares.add(pos1);
        moveableSquares.add(pos2);
        moveableSquares.add(pos3);
        moveableSquares.add(pos4);



    }
    boolean isOccupied = false;
    public boolean notOutOfBounds(int[] posarr){
        boolean available = false;
        if(!isOccupied) {
            for (int i = 0; i < posarr.length; i++) {
                if (posarr[i] > 0 && posarr[i] < player.getBoard().getSQUARE_ARRAY_HEIGHT() && posarr[i] < player.getBoard().getSQUARE_ARRAY_WIDTH())
                    available = true;
                else available = false;
                System.out.println("xd");
            }
        }
        return available;
    }
}
