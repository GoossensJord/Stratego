package be.kdg.stratego.gameview;

import be.kdg.stratego.model.GameModel;
import be.kdg.stratego.model.pieces.Piece;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class boardEventHandler implements EventHandler<MouseEvent> {
    private boolean midMove;
    private List<int[]> midMovearr;
    private List<int[]> midAttackarr;
    private int[] prevPosPiece;
    private GameModel model;
    private GameView view;
    private Piece p;
    private int currentPlayer = 0;


    public boardEventHandler(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.midMove = false;
        this.midMovearr = new ArrayList<>();
        this.midAttackarr = new ArrayList<>();
        prevPosPiece = new int[2];
    }

    public void handle(MouseEvent mouseEvent) {
        boolean openSpace;


        int x = (int) (mouseEvent.getX() / 78);
        int y = (int) (mouseEvent.getY() / 78);

        //  model.makePieceByString(view.getNotifications().getText().substring(1,3),x,y);

        if (!midMove) {
            selectPiece(x, y);
        } else {
            openSpace = choosePlay(x, y);
            if (openSpace) view.removeFromGridpane(prevPosPiece[0], prevPosPiece[1]);
        }

    }


    private void selectPiece(int x, int y) {


        p = model.choosePiece(x, y);
        if (p != null) {
            if (currentPlayer != p.getPlayer().getId()) {
                view.getNotifications().setText("Not this piece's turn!");
                return;
            }
        }
        prevPosPiece = new int[]{x, y};

        List<int[]> moveArr = model.getMoves(p);
        List<int[]> attackArr = model.getAttacks(p);

        midMove = lightUp(moveArr,attackArr);
        if(midMove) view.getNotifications().setText(model.getBoard()[x][y].getPiece().getRank().getName() + " choose one of the lit up squares");

        if (model.getBoard()[x][y].getPiece() == null) view.getNotifications().setText("No piece here");
        else if(!midMove) view.getNotifications().setText("No moves for " + model.getBoard()[x][y].getPiece().getRank().getName()+ " available");
    }


    private boolean lightUp(List<int[]> moves, List<int[]> attacks){
        if (attacks != null) {
            for (int i = 0; i < attacks.size(); i++) {
                view.lightUpRectanglesAttack(attacks);
            }
            midAttackarr = attacks;
        }

        if (moves != null) {
            for (int i = 0; i < moves.size(); i++) {
                view.lightUpRectangles(moves);
            }
            midMovearr = moves;
            return true;

        }
        return false;
    }



    private boolean choosePlay(int x, int y) {
        boolean moveable = moveableOrAttackable(midMovearr, x , y);
        boolean attackable = moveableOrAttackable(midAttackarr, x , y);
        int[] move = getMovesOrAttacks(midMovearr, x , y);
        int[] attack = getMovesOrAttacks(midAttackarr, x , y);
        this.midMove = false;

        if (moveable && !model.getBoard()[move[0]][move[1]].getIsOccupied()) return makeMove(p,prevPosPiece,move);
        else if (attackable) makeAttack(p, model.getBoard()[attack[0]][attack[1]].getPiece(), attack);

        else {
            view.getNotifications().setText("Can't move here");
            view.dimSquare();
            return false;
        }
        return true;
    }


    private int[] getMovesOrAttacks(List<int[]> moveOrAttackOptions, int x, int y){
        int [] play;
        for(int[] plays : moveOrAttackOptions){
            if(plays[0] == x && plays[1] == y){
                 play = new int[]{x,y};
                return play;
            }
        }
        return null;
    }


    private boolean moveableOrAttackable(List<int[]> moveOrAttackOptions, int x, int y){
        for(int[] plays : moveOrAttackOptions){
            if(plays[0] == x && plays[1] == y){
                return true;
            }
        }
        return false;
    }


    private boolean makeMove(Piece p, int[] oldPosition, int[]move){
        view.getNotifications().setText(model.getBoard()[oldPosition[0]][oldPosition[1]].getPiece().toString() + " moved to square " + (9 - move[0]) + " " + move[1]);
        model.makeChosenMove(move, p);
        model.piecesOnePlayer(1);
        view.dimSquare();
        view.setPicture(p.getImage(), move[0], move[1]);
        if (currentPlayer == 1) {
            currentPlayer--;
        } else currentPlayer++;
        view.getEndTurn().setDisable(false);
        view.getBoard().setDisable(true);
        return true;

    }


    private boolean makeAttack(Piece p, Piece p2, int [] attack){
        if (model.isMatchupWinner(p, p2)) {
            view.getNotifications().setText("You won! enemy lost " + p2.getRank().getName());
            view.removeFromGridpane(p2.getX(), p2.getY());
            view.setPicture(p.getImage(), attack[0], attack[1]);
        }

        else view.getNotifications().setText("You lost against the enemy's " + p2.getRank().getName());
        view.removeFromGridpane(p.getX(), p.getY());
        if(model.makeChosenAttack(attack, p)){
            view.getNotifications().setText("YOU WON THE GAME!!");
        }
        view.dimSquare();
        if (currentPlayer == 1) {
            currentPlayer--;
        } else currentPlayer++;
        view.getEndTurn().setDisable(false);
        view.getBoard().setDisable(true);
        return true;
    }
}