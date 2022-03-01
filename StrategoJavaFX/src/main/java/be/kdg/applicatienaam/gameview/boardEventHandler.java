package be.kdg.applicatienaam.gameview;

import be.kdg.applicatienaam.model.GameModel;
import be.kdg.applicatienaam.model.pieces.Piece;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class boardEventHandler implements EventHandler<MouseEvent> {
    private boolean midMove;
    private List<int[]> midMovearr;
    private int[] prevPosPiece;
    private GameModel model;
    private GameView view;
    private Piece p;


    public boardEventHandler(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.midMove = false;
        this.midMovearr = new ArrayList<>();
        prevPosPiece = new int[2];
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        int x = (int) (mouseEvent.getX() / 78);
        int y = (int) (mouseEvent.getY() / 78);

        if (!midMove) {
           selectPiece(x,y);
        }
        else {
            movePiece(x,y);
            view.removeFromGridpane(prevPosPiece[0], prevPosPiece[1]);
        }
    }

    private void selectPiece(int x ,int y){

        p = model.choosePiece(x, y);

        prevPosPiece = new int[]{x, y};
        //view.getNotifications().setText( "x = " + x + "y = " + y + "Piece that should be here = " + model.getPiece(x,y).toString());
        List<int[]> moveArr = model.getMoves(p);

        if (moveArr != null) {
            //moveArr = coordConverter(moveArr);
            for (int i = 0; i < moveArr.size(); i++) {
                //view.getNotifications().setText("Moves for piece " + x + " " + y );
                view.lightUpRectangles(moveArr);
            }
            view.getNotifications().setText("choose one of the lit up squares");
            midMovearr = moveArr;
            this.midMove = true;

        } else view.getNotifications().setText("No moves for piece " + x + " " + y + " available");
    }

    private void movePiece(int x,int y){

        boolean moveable = false;

        int[] move = new int[2];

        for (int i = 0; i < midMovearr.size(); i++) {
            if (midMovearr.get(i)[0] == x && midMovearr.get(i)[1] == y) {
                move = new int[]{x, y};
                moveable = true;
            }
        }

        if (moveable) {
            model.makeChosenMove(move, p);
            view.getNotifications().setText("your mom" + moveable + " " + move[0] + " " + move[1]);
        }
        this.midMove = false;
        view.dimSquare();
        view.setPicture(p.getImage(),x,y);

    }



}
