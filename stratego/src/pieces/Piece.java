package pieces;

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

    public Piece(Rank r, Player player) {
        this.rank = r;
        this.position = new int[2];
        this.deadOrAlive = true;
        this.isMoveable = true;
        this.canAttack = true;
        moveableSquares = new ArrayList<>();
        this.player = player;
    }

    public Piece() {
        this.position = new int[2];
        this.deadOrAlive = true;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public void move() {
        System.out.println("\nWhere would you like to move?");

        int[] pos1 = {this.position[0], this.position[1] + 1};
        int[] pos2 = {this.position[0], this.position[1] - 1};
        int[] pos3 = {this.position[0] + 1, this.position[1]};
        int[] pos4 = {this.position[0] - 1, this.position[1]};


        moveableSquares.add(pos1);
        moveableSquares.add(pos2);
        moveableSquares.add(pos3);
        moveableSquares.add(pos4);
        System.out.println("Choose one of the following squares.");
        for (int i = 0; i < moveableSquares.size(); i++) {
            if (notOutOfBoundsOrOccupied(moveableSquares.get(i)) == null) moveableSquares.remove(moveableSquares.get(i));
            else{
                for (int j = 0; j < moveableSquares.get(i).length; j++) {
                    System.out.print(moveableSquares.get(i)[j]);
                }
                System.out.println();

                //System.out.println(moveableSquares.get(i));
            }
        }

    }

    boolean isOccupied = false;

    public int[] notOutOfBoundsOrOccupied(int[] posarr) {
        if (!isOccupied) {
            for (int i = 0; i < posarr.length; i++) {
                if (posarr[i] > 0 && posarr[i] < player.getBoard().getSQUARE_ARRAY_HEIGHT() &&
                        posarr[i] < player.getBoard().getSQUARE_ARRAY_WIDTH()) {
                    return posarr;
                }
//                System.out.println("xd");
            }
        }
        return null;
    }
}
