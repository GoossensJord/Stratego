package be.kdg.stratego.arrangepiecesscreen;

import be.kdg.stratego.homescreenview.HomescreenPresenter;
import be.kdg.stratego.homescreenview.HomescreenView;
import be.kdg.stratego.model.GameModel;
import be.kdg.stratego.model.GameSaveState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;

public class ArrangePiecesPresenter {
    private GameModel model;
    private ArrangePiecesView view;

    public ArrangePiecesPresenter(GameModel model, ArrangePiecesView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
    }

    private void addEventHandlers() {
        view.getListView().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getBoard().setDisable(false);
                String pieceStr = mouseEvent.getPickResult().toString().split(",")[0];
                pieceStr = pieceStr.substring(pieceStr.length() - 4);
                view.getNotifications().setText(pieceStr + " selected");
                //view.removeItemFromListView()
            }
        });
        view.getBtnSetPieces().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setItems();
                view.getBtnSetPieces().setDisable(true);

            }
        });
        view.getBoard().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int x = (int) (mouseEvent.getX() / 78);
                int y = (int) (mouseEvent.getY() / 78);
                String pieceStr = view.getNotifications().getText().substring(1, 3);

                if (model.positionChecker(x, y)) {
                    model.makePieceByString(pieceStr, x, y);
                    fillBoardWithImages();
                    view.dimSquare(x, y);
                    view.removeListItem(pieceStr);
                    view.getBoard().setDisable(true);
                } else view.getNotifications().setText("Invalid placing!");

                if (view.getListViewLength() == 35){
                    view.setListItems(new ArrayList<>());
                    view.getNotifications().setText("Save your setup");
                    view.getPlayer1().setDisable(true);
                    view.getPlayer2().setDisable(true);
                    view.getListView().setDisable(true);
                }

            }
        });
        view.getPlayer1().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getPlayer2().setDisable(true);
                view.getPlayer1().setDisable(true);
                view.getBtnSetPieces().setDisable(false);
                GameSaveState.setPlayerTurn(model.getPlayerByID(0));
                view.lightUpRectangles(0);

            }
        });
        view.getPlayer2().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getPlayer1().setDisable(true);
                view.getPlayer2().setDisable(true);
                view.getBtnSetPieces().setDisable(false);
                GameSaveState.setPlayerTurn(model.getPlayerByID(1));
                view.getNotifications().setText(GameSaveState.getPlayerTurn().toString());
                view.lightUpRectangles(1);
            }
        });
      /*  view.getSaveSetup().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                TextInputDialog temp = new TextInputDialog("test");
                temp.showAndWait();
                String setupName = temp.getContentText();
                view.getBtnSetPieces().setDisable(false);
                try {
                    GameSaveState.saveSetup(model.getPlayerPiecesById(GameSaveState.getPlayerTurn().getId()),setupName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                view.getListView().setDisable(false);
                GameSaveState.setPlayerTurn(model.getPlayerByID(GameSaveState.switchTurnByid()));
                view.lightUpRectangles(GameSaveState.getPlayerTurn().getId());
            }
        });*/
        view.getReturnToMenuButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomescreenView homeView = new HomescreenView();
                HomescreenPresenter homePresenter = new HomescreenPresenter(model,homeView);
                view.getScene().setRoot(homeView);
                homeView.getScene().getWindow();
            }
        });

    }

    private void fillBoardWithImages() {
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < 10; j++) {
                if (model.getBoard()[i][j].getPiece() == null) view.setPosition("", i, j);
                else view.setPicture(model.getBoard()[i][j].getPiece().getImage(), i, j);
            }
        }
    }


    private void setItems() {
        view.setListItems(model.getAllPiecesString());
    }
}



