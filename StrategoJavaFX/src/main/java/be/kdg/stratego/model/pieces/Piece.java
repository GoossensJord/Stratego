package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.player.*;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Superclass Piece, responsible for holding all the information and methods related to a piece.
 */
public class Piece {

    protected Rank rank;
    protected Player player;
    protected boolean isMovable;
    protected boolean canAttack;
    protected int x;
    protected int y;


    /**
     * Constructor that initialises a rank, player and xy coordinates on the board
     */
    public Piece(Rank r, Player player, int x, int y) {
        this.rank = r;
        this.player = player;
        this.isMovable = true;
        this.canAttack = true;
        this.x = x;
        this.y = y;
    }


    /**
     * A method that returns a List of Intiger arrays by checking one square in each direction to see if it is available, if so it adds it to the list.
     */
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


    /**
     * A method that returns a List of Intiger arrays by checking one square in each direction to see if it is available to attack, if so it adds it to the list.
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Piece piece = (Piece) o;

        if (isMovable != piece.isMovable) return false;
        if (canAttack != piece.canAttack) return false;
        if (x != piece.x) return false;
        if (y != piece.y) return false;
        if (rank != piece.rank) return false;
        return Objects.equals(player, piece.player);
    }

    @Override
    public int hashCode() {
        int result = rank != null ? rank.hashCode() : 0;
        result = 31 * result + (player != null ? player.hashCode() : 0);
        result = 31 * result + (isMovable ? 1 : 0);
        result = 31 * result + (canAttack ? 1 : 0);
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }

    public int getRankPower() {
        return this.rank.power;
    }

    public Rank getRank() {
        return rank;
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
