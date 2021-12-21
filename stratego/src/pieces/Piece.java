package pieces;

import board.Board;
import board.Player;
import board.Square;

import java.util.ArrayList;
import java.util.List;

public class Piece {
    Rank rank;
    private int[] position;
    boolean deadOrAlive;
    boolean isMoveable;
    boolean canAttack;
    protected List<int[]> moveableSquares;
    Player player;
    protected int boardHeight;
    protected int boardWidth;
    protected int x;
    protected int y;
    public Piece(Rank r, Player player) {
        this.rank = r;
        this.position = new int[2];
        this.deadOrAlive = true;
        this.isMoveable = true;
        this.canAttack = true;
        this.moveableSquares = new ArrayList<>();
        this.player = player;
        this.x = position[0];
        this.y = position[1];
    }

    public Piece() {
    }



    public void setPosition(int[] position) {
        this.position = position;
    }
    public void makeMoves(){
        System.out.println("\nWhere would you like to move?");

        int[] pos1 = {this.position[0], this.position[1] + 1};
        int[] pos2 = {this.position[0], this.position[1] - 1};
        int[] pos3 = {this.position[0] + 1, this.position[1]};
        int[] pos4 = {this.position[0] - 1, this.position[1]};

        moveableSquares.add(pos1);
        moveableSquares.add(pos2);
        moveableSquares.add(pos3);
        moveableSquares.add(pos4);
    }
    public void getMoveableSquares() {

        for (int i = 0; i < moveableSquares.size(); i++) {
            if (outOfBoundsOrOccupied(moveableSquares.get(i))) moveableSquares.set(i,null);
            else{
                for (int j = 0; j < moveableSquares.get(i).length; j++) {
                    System.out.print(moveableSquares.get(i)[j]);
                }
                System.out.println();
            }

        }
    }
    public void move(){
        makeMoves();
        getMoveableSquares();
    }
    public boolean outOfBoundsOrOccupied(int[] posarr) {
        if (true) {
            for (int i = 0; i < posarr.length; i++) {
                boolean inBoundsHeight = posarr[i] >= 0 && posarr[i] <= boardHeight;
                boolean inBoundsWidth = posarr[i] >= 0 && posarr[i] <= boardWidth ;
                if(!inBoundsHeight||!inBoundsWidth) return true;
            }
        }
        return false;
    }
    public Piece attack(Piece piece){
        if(piece.getRankPower() < this.getRankPower()) return this;
        else return piece;
    }
    private int getRankPower(){
        return this.rank.power;
    }
    public Rank getRank() {
        return rank;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString(){
        String out = this.rank.getName().substring(0,2);
        return out;
    }
}
