package be.kdg.applicatienaam.gameview;

import be.kdg.applicatienaam.model.GameModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class GamePresenter {
    private GameModel model;
    private GameView view;


    public GamePresenter(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBtnStartGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                model.fillRandomly();
                fillBoardWithImages();
                view.getNotifications().setText("Player 1, you're up");

                view.getBtnStartGame().setDisable(true);
            }
        });
        view.getBtnEndTurn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /*updateView();*/
            }
        });
        view.getBoard().addEventHandler(MouseEvent.MOUSE_CLICKED, new boardEventHandler(model, view));{
            updateView();
        };
    }

    private void updateView() {
        fillBoardWithImages();
    }

    private void fillBoardWithImages() {
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < 10; j++) {
                if (model.getBoard()[i][j].getPiece() == null) view.setPosition("", i, j);
                    //   else view.setPosition(model.getBoard()[i][j].getPiece().toString(), i, j);
                else view.setPicture(model.getBoard()[i][j].getPiece().getImage(), i, j);
            }
        }
    }
    private void test(){

    }


}
