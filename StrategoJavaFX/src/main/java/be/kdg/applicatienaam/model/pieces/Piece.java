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
    protected int x;
    protected int y;



    public Piece(Rank r, Player player, int x, int y) {
        this.rank = r;
        this.player = player;
        this.deadOrAlive = true;
        this.isMovable = true;
        this.canAttack = true;
        this.x = x;
        this.y = y;
    }

    public Piece() {
    }


    public List<int[]> availableSquares(int x, int y) {


        List<int[]> moveList = new ArrayList<>();
        if (this.isMovable) {
            if (this.getPlayer().getBoard().notOutOfBounds(x + 1, y) && !player.getBoard().getBord()[x + 1][y].getIsOccupied()) {
                moveList.add(new int[]{x + 1, y});

            }
            if (this.getPlayer().getBoard().notOutOfBounds(x, y + 1) && !player.getBoard().getBord()[x][y + 1].getIsOccupied()) {
                moveList.add(new int[]{x, y + 1});


            }
            if (this.getPlayer().getBoard().notOutOfBounds(x - 1, y) && !player.getBoard().getBord()[x - 1][y].getIsOccupied()) {
                moveList.add(new int[]{x-1, y});

            }
            if (this.getPlayer().getBoard().notOutOfBounds(x, y - 1) && !player.getBoard().getBord()[x][y - 1].getIsOccupied()) {
                moveList.add(new int[]{x, y-1});

            }
            return moveList;
        } else return null;
    }
    public List<int[]> getAttacks(int x, int y) {


        List<int[]> attackList = new ArrayList<>();
        if (this.isMovable) {
            if (this.getPlayer().getBoard().notOutOfBounds(x + 1, y) && player.getBoard().getBord()[x + 1][y].getIsOccupied() && !player.getBoard().getBord()[x][y].getPiece().getPlayer().equals(player.getBoard().getBord()[x + 1][y].getPiece().getPlayer())) {
                attackList.add(new int[]{x + 1, y});

            }
            if (this.getPlayer().getBoard().notOutOfBounds(x, y + 1) && player.getBoard().getBord()[x][y + 1].getIsOccupied() && !player.getBoard().getBord()[x][y].getPiece().getPlayer().equals(player.getBoard().getBord()[x][y +1].getPiece().getPlayer()))  {
                attackList.add(new int[]{x, y+1});


            }
            if (this.getPlayer().getBoard().notOutOfBounds(x - 1, y) && player.getBoard().getBord()[x - 1][y].getIsOccupied() && !player.getBoard().getBord()[x][y].getPiece().getPlayer().equals(player.getBoard().getBord()[x -1][y].getPiece().getPlayer())) {
                attackList.add(new int[]{x - 1, y});
            }
            if (this.getPlayer().getBoard().notOutOfBounds(x, y - 1) && player.getBoard().getBord()[x][y - 1].getIsOccupied() && !player.getBoard().getBord()[x][y].getPiece().getPlayer().equals(player.getBoard().getBord()[x][y-1].getPiece().getPlayer())) {
                attackList.add(new int[]{x , y-1});

            }
            return attackList;
        } else return null;
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


}
