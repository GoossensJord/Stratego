package be.kdg.stratego.model.pieces;


import be.kdg.stratego.model.player.Player;


import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for Scout, extends Piece
 */
@SuppressWarnings("SuspiciousNameCombination")
public class Scout extends Piece {

    /**
     * Constructor for Scout, initialises its rank, its player and the x y value on the board.
     */
    public Scout(Rank scout, Player player, int x, int y) {
        super(Rank.SCOUT, player, x, y);

    }

    /**
     * A method that combines all the attack lists and returns it as one list.
     */
    public List<int[]> allAttacks() {

        List<int[]> allAttacks = new ArrayList<>(xAttackable());
        allAttacks.addAll(yAttackable());
        return allAttacks;

    }
    /**
     * A method that combines all the move lists and returns it as one list.
     */
    public List<int[]> allMoves() {

        List<int[]> allmoves = new ArrayList<>(xMoveable());
        allmoves.addAll(yMoveable());

        return allmoves;


    }

    /**
     * A method that checks both left and right of the Scout, it goes through the squares one by one, once it finds a square that is occupied by an enemy it will add it to the arraylist, only doing this once per direction. It also has to break if it encounters an ally piece without adding it to the attack list
     * @return Returns a list of all attack possibilities left and right
     */
    public List<int[]> yAttackable() {
        List<int[]> attacksLeft = new ArrayList<>();
        List<int[]> attacksRight = new ArrayList<>();
        int rightJumpCounter = 0;
        int leftJumpCounter = 0;
        int yval = this.x;
        for (int i = yval + 1; i < 10; i++) {
            Piece p1 = this.getPlayer().getBoard().getBoardMaker()[i][this.y].getPiece();
            //noinspection SuspiciousNameCombination
            if (!this.getPlayer().getBoard().spaceAvailable(i, this.y) && !this.getPlayer().equals(p1.getPlayer())) {

                    attacksRight.add(new int[]{i, this.y});
                    break;

            }
            if(!this.getPlayer().getBoard().spaceAvailable(i, this.y) && this.getPlayer().getId() == p1.getPlayer().getId()) rightJumpCounter++;

        }
        for (int i = yval - 1; i >= 0; i--) {
            Piece p1 = this.getPlayer().getBoard().getBoardMaker()[i][this.y].getPiece();
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

    /**
     * A method that checks both up and down of the Scout, it goes through the squares one by one, once it finds a square that is occupied by an enemy it will add it to the arraylist, only doing this once per direction. It also has to break if it encounters an ally piece without adding it to the attack list
     * @return Returns a list of all attack possibilities up and down.
     */
    public List<int[]> xAttackable() {
        List<int[]> attacksUp = new ArrayList<>();
        List<int[]> attacksDown = new ArrayList<>();
        int positiveJumpCounter = 0;
        int negativeJumpCounter = 0;
        int xval = this.y;
        for (int i = xval + 1; i < 10; i++) {
            Piece p1 = this.getPlayer().getBoard().getBoardMaker()[this.x][i].getPiece();

            //noinspection SuspiciousNameCombination
            if (!this.getPlayer().getBoard().spaceAvailable(this.x, i) && !this.getPlayer().equals(p1.getPlayer())) {

                    attacksUp.add(new int[]{this.x, i});
                    break;
                }
            if(!this.getPlayer().getBoard().spaceAvailable(this.x, i) && this.getPlayer().getId() == p1.getPlayer().getId()) positiveJumpCounter++;
        }
        for (int i = xval - 1; i >= 0; i--) {
            Piece p1 = this.getPlayer().getBoard().getBoardMaker()[this.x][i].getPiece();

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

    /**
     * A method that checks both left and right of the Scout, it goes through the squares one by one, adding each available square until it finds an occupied one.
     * @return Returns a list of all move possibilities left and right
     */
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

    /**
     * A method that checks both up and down of the Scout, it goes through the squares one by one, adding each available square until it finds an occupied one.
     * @return Returns a list of all move possibilities up and down
     */
    public List<int[]> xMoveable() {
        List<int[]> moves = new ArrayList<>();
        int yval = this.y;

        for (int i = yval + 1; i < 10; i++) {
            if (this.getPlayer().getBoard().spaceAvailable(this.x, i)) moves.add(new int[]{this.x, i});
            else break;
        }
        for (int i = yval - 1; i >= 0; i--) {
            if (this.getPlayer().getBoard().spaceAvailable(this.x, i)) moves.add(new int[]{this.x, i});
            else break;
        }
        return moves;
    }
}
