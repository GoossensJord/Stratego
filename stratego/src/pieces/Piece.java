package pieces;

import board.Board;
import board.Player;
import board.Square;

import java.util.ArrayList;
import java.util.List;

public class Piece {

    Rank rank;
    boolean deadOrAlive;
    boolean isMoveable;
    boolean canAttack;
    protected List<int[]> moveableSquares;
    protected int x;
    protected int y;

    public Piece(Rank r, Player player, int x, int y) {

        this.rank = r;
        this.deadOrAlive = true;
        this.isMoveable = true;
        this.canAttack = true;
        this.moveableSquares = new ArrayList<>();

    }

    public Piece() {
    }

    public List<int[]> getMoves(){
        System.out.println("\nWhere would you like to move?");

        int[] pos1 = {this.x, this.y+1};
        int[] pos2 = {this.x, this.y - 1};
        int[] pos3 = {this.x + 1, this.y};
        int[] pos4 = {this.x - 1, this.y};

        moveableSquares.add(pos1);
        moveableSquares.add(pos2);
        moveableSquares.add(pos3);
        moveableSquares.add(pos4);

        return this.moveableSquares;
    }
// Removes non moveable squares from array, implementation moved/has to move to board.
//    public List<int[]> getMoveableSquares() {
//
//        for (int i = 0; i < moveableSquares.size(); i++) {
//            if (outOfBoundsOrOccupied(moveableSquares.get(i))) moveableSquares.set(i,null);
//            else{
//                for (int j = 0; j < moveableSquares.get(i).length; j++) {
//                    System.out.print(moveableSquares.get(i)[j]);
//                }
//                System.out.println();
//            }
//            return moveableSquares;
//        }
//    }

//    public Piece attack(Piece piece){
//        if(piece.getRankPower() < this.getRankPower()) return this;
//        else return piece;
//    }


    public Rank getRank() {
        return rank;
    }

    public String toString(){
        String out = this.rank.getName().substring(0,2);
        return out;
    }
}
