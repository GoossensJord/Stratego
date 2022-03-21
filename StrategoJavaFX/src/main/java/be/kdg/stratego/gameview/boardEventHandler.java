package be.kdg.stratego.gameview;

import be.kdg.stratego.model.GameModel;
import be.kdg.stratego.model.pieces.Piece;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class boardEventHandler has as responsibility to process all mouse inputs on the board
 */
@SuppressWarnings("ConstantConditions")
public class boardEventHandler implements EventHandler<MouseEvent> {
    private boolean midMove;
    private List<int[]> midMovearr;
    private List<int[]> midAttackarr;
    private int[] prevPosPiece;
    private GameModel model;
    private GameView view;
    private Piece p;
    private int currentPlayer = 0;

    /**
     * Constructor that initialises the view and model. Also sets midMove to false and initialises the lists / arrays.
     */
    public boardEventHandler(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.midMove = false;
        this.midMovearr = new ArrayList<>();
        this.midAttackarr = new ArrayList<>();
        prevPosPiece = new int[2];
    }

    /**
     * Handles all mouse inputs, converts the x and y values to use in the model
     */
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

    /**
     * uses the x and y values from the handle method to select a piece, also checks if that piece is valid for use.
     */
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

        midMove = lightUp(moveArr, attackArr);
        if (midMove)
            view.getNotifications().setText(model.getBoard()[x][y].getPiece().getPlayer().getName() + "'s " + model.getBoard()[x][y].getPiece().getRank().getName() + " choose one of the lit up squares");

        if (model.getBoard()[x][y].getPiece() == null) view.getNotifications().setText("No piece here");
        else if (!midMove)
            view.getNotifications().setText("No moves for " + model.getBoard()[x][y].getPiece().getPlayer().getName() + "'s " + model.getBoard()[x][y].getPiece().getRank().getName() + " available");
    }

    /**
     * Processes values gotten from the model for what squares can be lit up.
     *
     * @param moves   List of possible moves to light up.
     * @param attacks List of possible attacks to light up.
     * @return The return value of this method is used to determine if you're in the middle of a move, because if both lists are empty you havent started a move.
     */
    private boolean lightUp(List<int[]> moves, List<int[]> attacks) {
        if (attacks != null) {
            for (int i = 0; i < attacks.size(); i++) {
                view.lightUpRectanglesAttack(attacks);
            }
            midAttackarr = attacks;
        }

        if (moves != null) {
            for (int i = 0; i < moves.size(); i++) {
                view.lightUpRectanglesMoves(moves);
            }
            midMovearr = moves;
            return true;

        }
        return false;
    }


    /**
     * if not in midmove, this method is called upon in the handle method. Allows the players input to be interpreted as a move or an attack.
     *
     * @return returns a boolean which determines if the space is open, if no moves or attacks can be made the space is not open thus returns false.
     */
    private boolean choosePlay(int x, int y) {
        boolean moveable = moveableOrAttackable(midMovearr, x, y);
        boolean attackable = moveableOrAttackable(midAttackarr, x, y);
        int[] move = getMovesOrAttacks(midMovearr, x, y);
        int[] attack = getMovesOrAttacks(midAttackarr, x, y);
        this.midMove = false;

        if (moveable && !model.getBoard()[move[0]][move[1]].getIsOccupied()) return makeMove(p, prevPosPiece, move);
        else if (attackable) makeAttack(p, model.getBoard()[attack[0]][attack[1]].getPiece(), attack);
        else{
            view.getNotifications().setText("");
            view.dimSquare();
            return false;
        }
        return true;
    }

    /**
     * A method that goes through the possible values until one is found that matches the x and y
     *
     * @return returns an int array of the x and y value that will be used.
     */
    private int[] getMovesOrAttacks(List<int[]> moveOrAttackOptions, int x, int y) {
        int[] play;
        for (int[] plays : moveOrAttackOptions) {
            if (plays[0] == x && plays[1] == y) {
                play = new int[]{x, y};
                return play;
            }
        }
        return null;
    }

    /**
     * A method that goes through the possible values until one is found that matches the x and
     *
     * @return returns true if a combination is found that matches the x and y else returns false.
     */
    private boolean moveableOrAttackable(List<int[]> moveOrAttackOptions, int x, int y) {
        for (int[] plays : moveOrAttackOptions) {
            if (plays[0] == x && plays[1] == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * A method to finalize the move, asks the model to make the chosen move and the view to change the board accordingly, is called upon in the chooseplay method.
     *
     * @return return value used in chooseplay to determine wether or not the space is open.
     */
    private boolean makeMove(Piece p, int[] oldPosition, int[] move) {
        view.getNotifications().setText(model.getBoard()[oldPosition[0]][oldPosition[1]].getPiece().getPlayer().getName() + "'s " + model.getBoard()[oldPosition[0]][oldPosition[1]].getPiece().getRank().getName() + " moved to square " + (9 - move[0]) + ":" + move[1]);
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

    /**
     * A method to finalize the attack, asks the model to make the chosen attack and the view to change the board accordingly.
     */
    private void makeAttack(Piece p, Piece p2, int[] attack) {
        if (model.isMatchupWinner(p, p2)) {
            view.getNotifications().setText(p.getPlayer().getName() + "'s " + p.getRank().getName() + " won against " + p2.getRank().getName());
            view.removeFromGridpane(p2.getX(), p2.getY());
            view.setPicture(p.getImage(), attack[0], attack[1]);
        } else
            view.getNotifications().setText(p.getPlayer().getName() + "'s " + p.getRank().getName() + " lost against " + p2.getRank().getName());
        view.removeFromGridpane(p.getX(), p.getY());
        if (model.makeChosenAttack(attack, p)) {

            model.setScore(p.getPlayer());
            view.getNotifications().setText(p.getPlayer().getName() + " WINS");
            view.dimSquare();
            view.getBoard().setDisable(true);
            view.getStartTurn().setDisable(true);
            view.getEndTurn().setDisable(true);
            return;

        }
        view.dimSquare();
        if (currentPlayer == 1) {
            currentPlayer--;
        } else currentPlayer++;
        view.getEndTurn().setDisable(false);
        view.getBoard().setDisable(true);
    }


}
