package be.kdg.applicatienaam.model.pieces;
import be.kdg.applicatienaam.model.player.*;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Piece {

    protected Image image;
    protected Rank rank;
    protected Player player;
    private boolean deadOrAlive;
    protected boolean isMovable;
    protected boolean canAttack;
    protected List<ArrayList<int[]>> movableSquares;
    protected int x;
    protected int y;


    public Piece(Rank r, Player player, int x, int y) {
        this.rank = r;
        this.player = player;
        this.deadOrAlive = true;
        this.isMovable = true;
        this.canAttack = true;
        this.movableSquares = new ArrayList<ArrayList<int[]>>();
        this.x = x;
        this.y = y;
    }


    public Piece() {
    }

    public Piece attack(Piece piece) {
        if(piece.getRank().equals(Rank.FLAG)){
            System.out.println("\n\n\n\n");
            System.out.println("---------------------------------------------------------");
            System.out.println(this.getPlayer().getName().toUpperCase() + " CAPTURED THE FLAG AND WON THE GAME!");
            System.out.println("---------------------------------------------------------");
            System.exit(0);
        }
        if(piece.getRankPower() == this.getRankPower()){
            System.out.println("\n"+this.getPlayer().getName() + " won the fight!\n");
            return this;
        }
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

    public Image getImage() {
        return this.getRank().getImage();
    }
    public int[] availableSquares(int x, int y) {
        int counter = 0;
        int[] moveableSquares = new int[2];
        moveableSquares[0] = 10;
        moveableSquares[1] = 10;
        if (player.getBoard().notOutOfBounds(x + 1, y) && !player.getBoard().getBord()[x + 1][y].getIsOccupied()) {
            moveableSquares[0] = x + 1;
            moveableSquares[1] = y;
            System.out.println(++counter + " Move DOWN available");
        }
        if (player.getBoard().notOutOfBounds(x, y + 1) && !player.getBoard().getBord()[x][y + 1].getIsOccupied()) {
            moveableSquares[0] = x;
            moveableSquares[1] = y+1;
            System.out.println(++counter + " Move RIGHT available");
        }
        if (player.getBoard().notOutOfBounds(x, y - 1) && !player.getBoard().getBord()[x][y - 1].getIsOccupied()) {
            moveableSquares[0] = x;
            moveableSquares[1] = y-1;
            System.out.println(++counter + " Square LEFT available");
        }
        if (player.getBoard().notOutOfBounds(x - 1, y) && !player.getBoard().getBord()[x - 1][y].getIsOccupied()) {
            moveableSquares[0] = x - 1;
            moveableSquares[1] = y;
            System.out.println(++counter + " Square UP available");
        }
        if(this.isMovable) return moveableSquares;
        else return null;
    }
}
