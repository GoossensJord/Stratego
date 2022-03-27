package be.kdg.stratego.arrangepiecesscreen;

import be.kdg.stratego.gameview.GamePresenter;
import be.kdg.stratego.gameview.GameView;
import be.kdg.stratego.homescreenview.HomescreenPresenter;
import be.kdg.stratego.homescreenview.HomescreenView;
import be.kdg.stratego.model.GameModel;
import be.kdg.stratego.model.GameSaveState;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Class responsible for presenting the arrange piece screen
 */
public class ArrangePiecesPresenter {
    private GameModel model;
    private ArrangePiecesView view;

    /**
     * Constructor that takes a model and a view to present the arrange pieces screen.
     */
    public ArrangePiecesPresenter(GameModel model, ArrangePiecesView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
    }

    /**
     * Method that adds event handlers to the Nodes
     */
    private void addEventHandlers() {
        view.getListView().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            view.getBoard().setDisable(false);
            try {
                String pieceStr = mouseEvent.getPickResult().toString().split("'")[1];
                view.getNotifications().setText(pieceStr + " selected");

            } catch (ArrayIndexOutOfBoundsException aiob) {
                String pieceStr = mouseEvent.getPickResult().toString().split("\"")[1];
                view.getNotifications().setText(pieceStr + " selected");

            }

        });
        view.getBtnSetPieces().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            setPieceItems();
            view.setSetupList(false);
            view.getBtnSetPieces().setDisable(true);
            view.getBtnUseSetup().setDisable(true);
            view.getBtnSaveSetup().setDisable(true);
        });
        view.getBoard().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            int x = (int) (mouseEvent.getX() / 78);
            int y = (int) (mouseEvent.getY() / 78);
            String pieceStr = view.getNotifications().getText().split(" ")[0];

            if (model.positionCheckerManualAssigningPiece(x, y)) {
                model.makePieceByString(pieceStr, x, y,GameSaveState.getPlayerTurn());
                fillBoardWithImages();
                view.dimSquare(x, y);
                view.removeListItem(pieceStr);
                view.getBoard().setDisable(true);
            } else view.getNotifications().setText("Invalid placing!");

            if (view.getListViewLength() == 0) {
                view.setListItems(new ArrayList<>());
                view.getNotifications().setText("Save your setup");
                view.getBtnPlayer1().setDisable(true);
                view.getBtnPlayer2().setDisable(true);
                view.getListView().setDisable(true);
                view.getBtnSaveSetup().setDisable(false);
                view.getBtnUseSetup().setDisable(false);
                view.getBtnSaveSetup().setDisable(false);
            }
        });
        view.getBtnPlayer1().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            view.getBtnPlayer2().setDisable(true);
            view.getBtnPlayer1().setDisable(true);
            view.getBtnSetPieces().setDisable(false);
            GameSaveState.setPlayerTurn(model.getPlayerByID(0));
            GameSaveState.setIdlePlayer(model.getPlayerByID(1));
            view.lightUpRectangles(0);

        });
        view.getBtnPlayer2().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            view.getBtnPlayer1().setDisable(true);
            view.getBtnPlayer2().setDisable(true);
            view.getBtnSetPieces().setDisable(false);
            GameSaveState.setPlayerTurn(model.getPlayerByID(1));
            GameSaveState.setIdlePlayer(model.getPlayerByID(0));
            //view.getNotifications().setText(GameSaveState.getPlayerTurn().getName());
            view.lightUpRectangles(1);
        });
        view.getBtnSaveSetup().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {

            TextInputDialog temp = new TextInputDialog();
            temp.showAndWait();
            String setupName = temp.getEditor().getText();
            view.getBtnSetPieces().setDisable(false);
            try {
                GameSaveState.saveSetup(model.getPlayerPiecesById(GameSaveState.getPlayerTurn().getId()), setupName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!model.startGame()){
                view.getListView().setDisable(false);
                GameSaveState.switchTurn();
                view.lightUpRectangles(GameSaveState.getPlayerTurn().getId());
                GameSaveState.clearHashMap();
            }
            else {
                view.getBtnSaveSetup().setDisable(true);
                view.getListView().setDisable(true);
                view.getBtnStartGame().setDisable(false);
                view.getBtnSetPieces().setDisable(true);
            }


        });
        view.getBtnUseSetup().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            GameSaveState.switchTurn();
            view.lightUpRectangles(GameSaveState.getPlayerTurn().getId());
            view.getBtnSetPieces().setDisable(false);
            view.getListView().setDisable(false);
        });
        view.getReturnToMenuButton().setOnAction(event -> {
            model.clearBoard();
            HomescreenView homeView = new HomescreenView();
            HomescreenPresenter homePresenter = new HomescreenPresenter(model, homeView);
            view.getScene().setRoot(homeView);
            homeView.getScene().getWindow();
        });
        view.getBtnStartGame().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            GameView gameView = new GameView();
            GamePresenter gamePresenter = new GamePresenter(model, gameView);
            view.getScene().setRoot(gameView);
            gameView.getScene().getWindow();
        });
    }

    /**
     * Method that fills the board with images where a piece is present and else it fills it with an empty string
     */
    private void fillBoardWithImages() {
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < 10; j++) {
                if (model.getBoard()[i][j].getPiece() == null) view.setPosition(i, j);
                else view.setPicture(model.getBoard()[i][j].getPiece().getImage(), i, j);
            }
        }
    }

    /**
     * A method that calls the backend model to give back a string list of all pieces to present in the view
     */
    private void setPieceItems() {
        view.setListItems(model.getAllPiecesString());
    }
}



