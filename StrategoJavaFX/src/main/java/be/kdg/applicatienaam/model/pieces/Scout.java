package be.kdg.applicatienaam.model.pieces;

import be.kdg.applicatienaam.model.player.Player;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
//todo attack on first move
//moves any distance horizontal & vertical (no leaping over)
public class Scout extends Piece {
    private boolean firstMove = true;
    private boolean firstAttack = true;
    protected List<ArrayList<int[]>> movableSquares;

    public Scout(Rank scout, Player player, int x, int y) {
        super(Rank.SCOUT, player, x, y);
        this.image = new Image("Scout.png");

        movableSquares = new ArrayList<>();
        for (int i = 0; i < 4; i++) movableSquares.add(new ArrayList<>());

    }
    public List<int[]> allAttacks(){
        if(firstAttack){
            this.firstAttack = false;
            return firstAttack();
        }
        else{
            List<int[]> allAttacks = new ArrayList<>(xAttackable());
            allAttacks.addAll(yAttackable());
            return allAttacks;
        }
    }

    public List<int[]> allMoves() {
        if (firstMove) {
            this.firstMove = false;
            return firstmove();
        } else {
            List<int[]> allmoves = new ArrayList<>(xMoveable());
            allmoves.addAll(yMoveable());

            return allmoves;
        }

    }
    public List<int[]> firstAttack(){
        List <int[]> startAttacks = new ArrayList<>();
       if(this.getPlayer().getId() == 1) startAttacks.add(new int[]{this.x + 3, this.y});
       else startAttacks.add(new int[]{this.x - 3 , this.y});
       return startAttacks;
    }
    public List<int[]> yAttackable(){
        List<int[]> attacks = new ArrayList<>();
        int yval = this.x;
        for (int i = yval+1; i < 10 ; i++) {
            if(!this.getPlayer().getBoard().spaceAvailable(i,this.y) && !this.getPlayer().equals(this.getPlayer().getBoard().getBord()[i][this.y].getPiece().getPlayer())){
                attacks.add(new int[] {i,this.y});
                break;
            }
        }
        for (int i = yval-1; i >= 0 ; i--) {
            if(!this.getPlayer().getBoard().spaceAvailable(i,this.y) && !this.getPlayer().equals(this.getPlayer().getBoard().getBord()[i][this.y].getPiece().getPlayer())){
                attacks.add(new int[] {i,this.y});
                break;
            }
        }
        return attacks;
    }
    public List<int[]> xAttackable(){
        List<int[]> attacks = new ArrayList<>();
        int xval = this.x;
        for (int i = xval+1; i < 10 ; i++) {
            if(!this.getPlayer().getBoard().spaceAvailable(this.x,i) && !this.getPlayer().equals(this.getPlayer().getBoard().getBord()[this.x][i].getPiece().getPlayer())){
                attacks.add(new int[] {this.x,i});
                break;
            }
        }
        for (int i = xval-1; i >= 0 ; i--) {
            if(!this.getPlayer().getBoard().spaceAvailable(this.x,i) && !this.getPlayer().equals(this.getPlayer().getBoard().getBord()[this.x][i].getPiece().getPlayer())){
                attacks.add(new int[] {this.x,i});
                break;
            }
        }
        return attacks;
    }

    private List<int[]> firstmove() {
        List<int[]> startmoves = new ArrayList<>();
        if (this.getPlayer().getId() == 1) {
            if (!super.player.getBoard().getBord()[x+1][y].getIsOccupied()) {
                startmoves.add(new int[]{this.x + 1, this.y});
                startmoves.add(new int[]{this.x + 2, this.y});
            }
        } else {
            if (!super.player.getBoard().getBord()[x-1][y].getIsOccupied()) {
                startmoves.add(new int[]{this.x - 1, this.y});
                startmoves.add(new int[]{this.x - 2, this.y});
            }
        }
        return startmoves;
    }

    public List<int[]> yMoveable() {
        List<int[]> moves = new ArrayList<>();
        int xval = this.x;

        for (int i = xval+1; i < 10; i++) {
            if (this.getPlayer().getBoard().spaceAvailable(i, this.y)) moves.add(new int[]{i, this.y});
            else break;
        }
        for (int i = xval - 1; i >= 0; i--) {
            if (this.getPlayer().getBoard().spaceAvailable(i, this.y)) moves.add(new int[]{i, this.y});
            else break;
        }
        return moves;

    }


    public List<int[]> xMoveable() {
        List<int[]> moves = new ArrayList<>();
        int yval = this.y;

        for (int i = yval+1; i < 10; i++) {
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
