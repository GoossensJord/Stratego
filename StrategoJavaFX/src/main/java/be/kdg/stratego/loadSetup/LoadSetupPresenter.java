package be.kdg.stratego.loadSetup;

import be.kdg.stratego.gameview.GamePresenter;
import be.kdg.stratego.gameview.GameView;
import be.kdg.stratego.homescreenview.HomescreenPresenter;
import be.kdg.stratego.homescreenview.HomescreenView;
import be.kdg.stratego.model.GameModel;
import be.kdg.stratego.model.GameSaveState;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

/**
 * Class responsible for presenting the load setup screen
 */
public class LoadSetupPresenter {
    private GameModel model;
    private LoadSetupView view;

    /**
     * Constructor for the load setup presenter using a GameModel and a LoadSetupView
     * @param model The game model
     * @param view The load setup view
     */
    public LoadSetupPresenter(GameModel model, LoadSetupView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
    }

    /**
     * Adds event handlers to the different input options
     */
    private void addEventHandlers() {
        view.getLoadSetupsBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> view.setListItems(GameSaveState.getSetupStringList()));

        view.getPlayer1().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            view.getPlayer2().setDisable(true);
            view.getPlayer1().setDisable(true);
            GameSaveState.setPlayerTurn(model.getPlayerByID(0));
            GameSaveState.setIdlePlayer(model.getPlayerByID(1));
            view.getLoadSetupsBtn().setDisable(false);
            view.getCommonListView().setDisable(false);

            view.getNotifications().setText("Player one, choose your layout");
        });
        view.getPlayer2().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            view.getPlayer1().setDisable(true);
            view.getPlayer2().setDisable(true);
            GameSaveState.setPlayerTurn(model.getPlayerByID(1));
            GameSaveState.setIdlePlayer(model.getPlayerByID(0));
            view.getNotifications().setText(GameSaveState.getPlayerTurn().getName());
            view.getLoadSetupsBtn().setDisable(false);
            view.getCommonListView().setDisable(false);


        });

        view.getListView().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            view.getConfirmSetup().setDisable(false);
            view.getResetBtn().setDisable(false);
            try {
                String pieceStr = mouseEvent.getPickResult().toString().split("\"")[1];
                view.getNotifications().setText(pieceStr + " selected");
                GameSaveState.loadSave(pieceStr, false);
            } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                System.out.println("Array out of bounds");
            }
            model.loadSave();
            fillBoardWithImages();
        });

        view.getConfirmSetup().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            GameSaveState.switchTurn();
            view.setListItems(new ArrayList<>());
            view.getListView().setDisable(false);
            view.getResetBtn().setDisable(true);
            view.getConfirmSetup().setDisable(true);
            if (model.startGame()) {
                view.getStartGame().setDisable(false);
                view.getLoadSetupsBtn().setDisable(true);
            }
        });
        view.getResetBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            model.clearBoardById(GameSaveState.getPlayerTurn().getId());
            clearGridById(GameSaveState.getPlayerTurn().getId());
        });
        view.getStartGame().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            GameView gameView = new GameView();
            GamePresenter gamePresenter = new GamePresenter(model, gameView);
            view.getScene().setRoot(gameView);
            gameView.getScene().getWindow();
        });
        view.getReturnToMenuButton().setOnAction(event -> {
            model.clearBoard();
            HomescreenView homeView = new HomescreenView();
            HomescreenPresenter homePresenter = new HomescreenPresenter(model, homeView);
            view.getScene().setRoot(homeView);
            homeView.getScene().getWindow();
        });
    }

    /**
     * Clears the grid of one player
     * @param id Used to check the player ID, depending on the ID a different side of the board gets cleared.
     */
    private void clearGridById(int id) {
        int x;
        int y;
        int start = 0;
        if (id == 0) {
            x = 3;
        } else {
            start = 6;
            x = 9;
        }
        y = 9;

        for (int i = start; i <= x; i++) {
            for (int j = 0; j <= y; j++) {
                view.removeFromGridpane(i, j);
            }
        }

    }

    /**
     * fills the board with images
     */
    private void fillBoardWithImages() {
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < 10; j++) {
                if (model.getBoard()[i][j].getPiece() == null) view.fillSquareWithEmptyString(i, j);
                else view.fillSquareWithPicture(model.getBoard()[i][j].getPiece().getImage(), i, j);
            }
        }
    }

}
