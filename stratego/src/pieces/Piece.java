package pieces;

import board.Player;

import java.util.ArrayList;
import java.util.List;

public class Piece {

    protected Rank rank;
    private boolean deadOrAlive;
    protected boolean isMoveable;
    protected boolean canAttack;
    protected List<int[]> moveableSquares;
    protected int x;
    protected int y;

    public Piece(Rank r, Player player, int x, int y) {
        this.rank = r;
        this.deadOrAlive = true;
        this.isMoveable = true;
        this.canAttack = true;
        this.moveableSquares = new ArrayList<>();
        this.x = x;
        this.y = y;
    }

    public Piece() {
    }

    public Piece attack(Piece piece) {
        if (piece.getRankPower() < this.getRankPower()){
            piece.setDeadOrAlive(false);
            return this;
        }
        else {
            this.setDeadOrAlive(false);
            return piece;
        }
    }

    public int getRankPower() {
        return this.rank.power;
    }

    public Rank getRank() {
        return rank;
    }

    public void setDeadOrAlive(boolean deadOrAlive) {
        this.deadOrAlive = deadOrAlive;
    }

    public boolean getDeadOrAlive(){
        return this.deadOrAlive;
    }

    public String toString() {
        return this.rank.getName().substring(0, 2);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
