package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameSaveState;
import be.kdg.stratego.model.player.Player;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;


public class Scout extends Piece {

    protected List<ArrayList<int[]>> movableSquares;

    public Scout(Rank scout, Player player, int x, int y) {
        super(Rank.SCOUT, player, x, y);
        this.image = new Image("Scout.png");

        movableSquares = new ArrayList<>();
        for (int i = 0; i < 4; i++) movableSquares.add(new ArrayList<>());

    }

    public List<int[]> allAttacks() {

        List<int[]> allAttacks = new ArrayList<>(xAttackable());
        allAttacks.addAll(yAttackable());
        return allAttacks;

    }

    public List<int[]> allMoves() {

        List<int[]> allmoves = new ArrayList<>(xMoveable());
        allmoves.addAll(yMoveable());

        return allmoves;


    }

    public List<int[]> yAttackable() {
        List<int[]> attacksLeft = new ArrayList<>();
        List<int[]> attacksRight = new ArrayList<>();
        int rightJumpCounter = 0;
        int leftJumpCounter = 0;
        int yval = this.x;
        for (int i = yval + 1; i < 10; i++) {
            Piece p1 = this.getPlayer().getBoard().getBord()[i][this.y].getPiece();
            //noinspection SuspiciousNameCombination
            if (!this.getPlayer().getBoard().spaceAvailable(i, this.y) && !this.getPlayer().equals(p1.getPlayer())) {

                    attacksRight.add(new int[]{i, this.y});
                    break;

            }
            if(!this.getPlayer().getBoard().spaceAvailable(i, this.y) && this.getPlayer().getId() == p1.getPlayer().getId()) rightJumpCounter++;

        }
        for (int i = yval - 1; i >= 0; i--) {
            Piece p1 = this.getPlayer().getBoard().getBord()[i][this.y].getPiece();
            //noinspection SuspiciousNameCombination
            if (!this.getPlayer().getBoard().spaceAvailable(i, this.y) && !this.getPlayer().equals(p1.getPlayer())) {

                    attacksLeft.add(new int[]{i, this.y});
                    break;

            }
            if(!this.getPlayer().getBoard().spaceAvailable(i, this.y) && this.getPlayer().getId() == p1.getPlayer().getId()) leftJumpCounter++;

        }
        if(rightJumpCounter > 0) attacksRight = new ArrayList<>();
        if(leftJumpCounter > 0) attacksLeft = new ArrayList<>();
        attacksRight.addAll(attacksLeft);
        return attacksRight;
    }


    public List<int[]> xAttackable() {
        List<int[]> attacksUp = new ArrayList<>();
        List<int[]> attacksDown = new ArrayList<>();
        int positiveJumpCounter = 0;
        int negativeJumpCounter = 0;
        int xval = this.y;
        for (int i = xval + 1; i < 10; i++) {
            Piece p1 = this.getPlayer().getBoard().getBord()[this.x][i].getPiece();

            //noinspection SuspiciousNameCombination
            if (!this.getPlayer().getBoard().spaceAvailable(this.x, i) && !this.getPlayer().equals(p1.getPlayer())) {

                    attacksUp.add(new int[]{this.x, i});
                    break;
                }
            if(!this.getPlayer().getBoard().spaceAvailable(this.x, i) && this.getPlayer().getId() == p1.getPlayer().getId()) positiveJumpCounter++;
        }
        for (int i = xval - 1; i >= 0; i--) {
            Piece p1 = this.getPlayer().getBoard().getBord()[this.x][i].getPiece();

            //noinspection SuspiciousNameCombination
            if (!this.getPlayer().getBoard().spaceAvailable(this.x, i) && !this.getPlayer().equals(p1.getPlayer())) {

                    attacksDown.add(new int[]{this.x, i});
                    break;

            }
            if(!this.getPlayer().getBoard().spaceAvailable(this.x, i) && this.getPlayer().getId() == p1.getPlayer().getId()) negativeJumpCounter++;

        }
        if(negativeJumpCounter >0) attacksDown = new ArrayList<>();
        if(positiveJumpCounter >0) attacksUp = new ArrayList<>();
        attacksDown.addAll(attacksUp);
        return attacksDown;
    }


    public List<int[]> yMoveable() {
        List<int[]> moves = new ArrayList<>();
        int xval = this.x;

        for (int i = xval + 1; i < 10; i++) {
            //noinspection SuspiciousNameCombination
            if (this.getPlayer().getBoard().spaceAvailable(i, this.y)) moves.add(new int[]{i, this.y});
            else break;
        }
        for (int i = xval - 1; i >= 0; i--) {
            //noinspection SuspiciousNameCombination
            if (this.getPlayer().getBoard().spaceAvailable(i, this.y)) moves.add(new int[]{i, this.y});
            else break;
        }
        return moves;

    }


    public List<int[]> xMoveable() {
        List<int[]> moves = new ArrayList<>();
        int yval = this.y;

        for (int i = yval + 1; i < 10; i++) {
            //noinspection SuspiciousNameCombination
            if (this.getPlayer().getBoard().spaceAvailable(this.x, i)) moves.add(new int[]{this.x, i});
            else break;
        }
        for (int i = yval - 1; i >= 0; i--) {
            //noinspection SuspiciousNameCombination
            if (this.getPlayer().getBoard().spaceAvailable(this.x, i)) moves.add(new int[]{this.x, i});
            else break;
        }
        return moves;
    }
}
