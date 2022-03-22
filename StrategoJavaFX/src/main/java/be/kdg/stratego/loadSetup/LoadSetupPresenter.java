package be.kdg.stratego.loadSetup;

import be.kdg.stratego.gameview.GamePresenter;
import be.kdg.stratego.gameview.GameView;
import be.kdg.stratego.model.GameModel;
import be.kdg.stratego.model.GameSaveState;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class LoadSetupPresenter {
    private GameModel model;
    private LoadSetupView view;

    public LoadSetupPresenter(GameModel model, LoadSetupView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getLoadSetupsBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.setListItems(GameSaveState.getSetupStringList());
            }
        });
        view.getPlayer1().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getPlayer2().setDisable(true);
                view.getPlayer1().setDisable(true);
                GameSaveState.setPlayerTurn(model.getPlayerByID(0));
                GameSaveState.setIdlePlayer(model.getPlayerByID(1));
                view.getLoadSetupsBtn().setDisable(false);
                view.getCommonListView().setDisable(false);
            }
        });
        view.getPlayer2().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getPlayer1().setDisable(true);
                view.getPlayer2().setDisable(true);
                GameSaveState.setPlayerTurn(model.getPlayerByID(1));
                view.getNotifications().setText(GameSaveState.getPlayerTurn().getName());
                view.getLoadSetupsBtn().setDisable(false);
                view.getCommonListView().setDisable(false);
            }
        });

        view.getCommonListView().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
        view.getResetBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                model.clearBoard();
                clearGrid();
            }
        });
        view.getListView().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    String pieceStr = mouseEvent.getPickResult().toString().split("\"")[1];
                    view.getNotifications().setText(pieceStr + " selected");
                    GameSaveState.loadSave(pieceStr);
                } catch (ArrayIndexOutOfBoundsException aiob) {
                    System.out.println("smth foktop");
                }
                model.loadSave();
                fillBoardWithImages();
            }
        });
        view.getConfirmSetup().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                GameSaveState.switchTurn();
                view.setListItems(new ArrayList<>());
            }
        });
        view.getStartGame().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                GameView gameView = new GameView();
                GamePresenter gamePresenter = new GamePresenter(model,gameView);
                view.getScene().setRoot(gameView);
                gameView.getScene().getWindow();
            }
        });
    }

    private void clearGrid() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                view.removeFromGridpane(i, j);
            }
        }

    }

    private void fillBoardWithImages() {
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < 10; j++) {
                if (model.getBoard()[i][j].getPiece() == null) view.setPosition("", i, j);
                else view.setPicture(model.getBoard()[i][j].getPiece().getImage(), i, j);
            }
        }
    }

}
