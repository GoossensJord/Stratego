package be.kdg.applicatienaam.model.pieces;

import be.kdg.applicatienaam.model.player.Player;


public class Bomb extends Piece {


    public Bomb(Rank r, Player player, int x, int y) {

        super(r, player, x, y);
        this.isMovable = false;
        this.canAttack = false;
    }


}