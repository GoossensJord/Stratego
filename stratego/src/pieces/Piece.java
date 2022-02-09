package pieces;


import player.Player;

import java.util.ArrayList;
import java.util.List;

public class Piece {

    protected Rank rank;
    protected Player player;
    private boolean deadOrAlive;
    protected boolean isMovable;
    protected boolean canAttack;
    protected List<int[]> movableSquares;
    protected int x;
    protected int y;


    public Piece(Rank r, Player player, int x, int y) {
        this.rank = r;
        this.player = player;
        this.deadOrAlive = true;
        this.isMovable = true;
        this.canAttack = true;
        this.movableSquares = new ArrayList<>();
        this.x = x;
        this.y = y;
    }


    public Piece() {
    }

    public Piece attack(Piece piece) {
        if (piece.getRankPower() < this.getRankPower()) {
            piece.setDeadOrAlive(false);
            System.out.println("\n"+this.getPlayer().getName() + " won the fight!\n");
            return this;
        } else {
            this.setDeadOrAlive(false);
            System.out.println("\n"+piece.getPlayer().getName() + " won the fight!\n");
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

    public boolean getDeadOrAlive() {
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

    public Player getPlayer() {
        return player;
    }

    public boolean isMovable() {
        return isMovable;
    }

    public boolean isCanAttack() {
        return canAttack;
    }
}
