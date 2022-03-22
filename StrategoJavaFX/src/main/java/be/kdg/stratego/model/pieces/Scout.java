package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameSaveState;
import be.kdg.stratego.model.player.Player;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

//todo attack on first move
//moves any distance horizontal & vertical (no leaping over)
public class Scout extends Piece {
    private boolean firstMove = true;
    private boolean firstAttack = true;
    private int jumpCounter = 0;
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


    private boolean xJumpChecker(int xPosAttacking, int xPosGettingAttacked) {
        int xstart = xPosAttacking;
        int xend = xPosGettingAttacked;
        if (xPosGettingAttacked < xPosAttacking) {
            xstart = xPosGettingAttacked;
            xend = xPosAttacking;
        }

        for (int i = xstart; i <= xend; i++) {
            if (this.getPlayer().getBoard().getBord()[this.x][i].getPiece().getPlayer().getId() == GameSaveState.getPlayerTurn().getId())
                return true;
        }
        return false;
    }

    private boolean yJumpChecker(int yPosAttacking, int yPosGettingAttacked) {
        int ystart = yPosAttacking;
        int yend = yPosGettingAttacked;
        if (yPosGettingAttacked < yPosAttacking) {
            ystart = yPosGettingAttacked;
            yend = yPosAttacking;
        }

        for (int i = ystart; i <= yend; i++) {
            if (this.getPlayer().getBoard().getBord()[i][this.y].getPiece().getPlayer().getId() == GameSaveState.getPlayerTurn().getId())
                return true;
        }
        return false;
    }

    public List<int[]> yAttackable() {
        List<int[]> attacks = new ArrayList<>();
        int yval = this.x;
        for (int i = yval + 1; i < 10; i++) {
            Piece p1 = this.getPlayer().getBoard().getBord()[i][this.y].getPiece();
            //noinspection SuspiciousNameCombination
            if (!this.getPlayer().getBoard().spaceAvailable(i, this.y) && !this.getPlayer().equals(p1.getPlayer())) {
                if (yJumpChecker(this.x, i)) {
                    attacks.add(new int[]{i, this.y});
                    break;
                }
            }
        }
        for (int i = yval - 1; i >= 0; i--) {
            Piece p1 = this.getPlayer().getBoard().getBord()[i][this.y].getPiece();
            //noinspection SuspiciousNameCombination
            if (!this.getPlayer().getBoard().spaceAvailable(i, this.y) && !this.getPlayer().equals(p1.getPlayer())) {
                if (yJumpChecker(this.x, i)) {
                    attacks.add(new int[]{i, this.y});
                    break;
                }
            }

        }
        return attacks;
    }


    public List<int[]> xAttackable() {
        List<int[]> attacks = new ArrayList<>();
        int xval = this.y;
        for (int i = xval + 1; i < 10; i++) {
            Piece p1 = this.getPlayer().getBoard().getBord()[this.x][i].getPiece();

            //noinspection SuspiciousNameCombination
            if (!this.getPlayer().getBoard().spaceAvailable(this.x, i) && !this.getPlayer().equals(p1.getPlayer())) {
                if (xJumpChecker(i, this.y)) {
                    attacks.add(new int[]{this.x, i});
                    break;
                }
            }
        }
        for (int i = xval - 1; i >= 0; i--) {
            Piece p1 = this.getPlayer().getBoard().getBord()[this.x][i].getPiece();

            //noinspection SuspiciousNameCombination
            if (!this.getPlayer().getBoard().spaceAvailable(this.x, i) && !this.getPlayer().equals(p1.getPlayer())) {
                if (xJumpChecker(i, this.y)) {
                    attacks.add(new int[]{this.x, i});
                    break;
                }
            }
        }
        return attacks;
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
