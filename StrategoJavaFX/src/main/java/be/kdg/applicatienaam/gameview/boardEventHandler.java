package be.kdg.applicatienaam.gameview;

import be.kdg.applicatienaam.model.GameModel;
import be.kdg.applicatienaam.model.pieces.Piece;
import be.kdg.applicatienaam.model.pieces.Rank;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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


    public boardEventHandler(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.midMove = false;
        this.midMovearr = new ArrayList<>();
        this.midAttackarr = new ArrayList<>();
        prevPosPiece = new int[2];
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        boolean openSpace;
        int x = (int) (mouseEvent.getX() / 78);
        int y = (int) (mouseEvent.getY() / 78);

        if (!midMove) {
            selectPiece(x, y);
        } else {
            openSpace = makePlay(x, y);
            if (openSpace) view.removeFromGridpane(prevPosPiece[0], prevPosPiece[1]);
        }
    }

    private void selectPiece(int x, int y) {

        p = model.choosePiece(x, y);

        prevPosPiece = new int[]{x, y};

        List<int[]> moveArr = model.getMoves(p);
        List<int[]> attackArr = model.getAttacks(p);

        if (attackArr != null) {
            for (int i = 0; i < attackArr.size(); i++) {
                view.lightUpRectanglesAttack(attackArr);
            }
            midAttackarr = attackArr;
        }

        if (moveArr != null) {
            for (int i = 0; i < moveArr.size(); i++) {
                view.lightUpRectangles(moveArr);
            }
            view.getNotifications().setText(model.getBoard()[x][y].getPiece().toString() + " choose one of the lit up squares");
            midMovearr = moveArr;
            this.midMove = true;

        } else view.getNotifications().setText("No moves for " + model.getBoard()[x][y].getPiece().toString() + " " + x + " " + y + " available");
    }

    private boolean makePlay(int x, int y) {

        boolean moveable = false;
        boolean attackable = false;
        this.midMove = false;
        int[] move = new int[2];
        int[] attack = new int[2];

        for (int[] moves : midMovearr) {
            if (moves[0] == x && moves[1] == y) {
                move = new int[]{x, y};
                moveable = true;
                break;
            }
        }
        for (int[] attacks : midAttackarr) {
            if (attacks[0] == x && attacks[1] == y) {
                attack = new int[]{x, y};
                attackable = true;
                break;
            }
        }

        if (moveable && !model.getBoard()[move[0]][move[1]].getIsOccupied()) {
            view.getNotifications().setText(model.getBoard()[prevPosPiece[0]][prevPosPiece[1]].getPiece().toString() + " moved to square " + (9 - move[0]) + " " + move[1]);

            model.makeChosenMove(move, p);
            view.dimSquare();
            view.setPicture(p.getImage(), x, y);

            return true;

        } else if (attackable) {

            if (model.getBoard()[attack[0]][attack[1]].getPiece().getRankPower() < p.getRankPower()
                    || (p.getRank().equals(Rank.MINER) && model.getBoard()[attack[0]][attack[1]].getPiece().getRank().equals(Rank.BOMB))
                    || (p.getRank().equals(Rank.SPY) && model.getBoard()[attack[0]][attack[1]].getPiece().getRank().equals(Rank.MARSHAL))) {
                view.getNotifications().setText(p.toString() + " won against " + model.getBoard()[attack[0]][attack[1]].toString());
                view.removeFromGridpane(attack[0], attack[1]);
                view.setPicture(p.getImage(), x, y);
            } else if (model.getBoard()[attack[0]][attack[1]].getPiece().getRankPower() > p.getRankPower())
                view.getNotifications().setText(model.getBoard()[attack[0]][attack[1]].toString() + " won against " + p.toString());


            view.removeFromGridpane(p.getX(),p.getY());
            model.makeChosenAttack(attack, p);
            view.dimSquare();
            return true;
        } else {
            view.getNotifications().setText("Can't move here");
            view.dimSquare();
            return false;
        }

    }
}
