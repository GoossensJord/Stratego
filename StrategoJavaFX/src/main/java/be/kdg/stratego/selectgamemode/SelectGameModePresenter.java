package be.kdg.stratego.selectgamemode;

import be.kdg.stratego.gameview.GamePresenter;
import be.kdg.stratego.gameview.GameView;
import be.kdg.stratego.homescreenview.HomescreenPresenter;
import be.kdg.stratego.homescreenview.HomescreenView;
import be.kdg.stratego.loadSetup.LoadSetupPresenter;
import be.kdg.stratego.loadSetup.LoadSetupView;
import be.kdg.stratego.model.GameModel;
import javafx.scene.input.MouseEvent;

/**
 * Class responsible for selecting a game mode
 */
public class SelectGameModePresenter {

    GameModel gameModel;
    SelectGameModeView gameModeView;

    /**
     * Constructor which takes a model and a view to present the game mode select screen
     */
    public SelectGameModePresenter(GameModel gameModel, SelectGameModeView gameModeView){
        this.gameModel = gameModel;
        this.gameModeView = gameModeView;
        addEventHandlers();
    }

    /**
     * Adds event handlers for the different buttons on the homescreen.
     */
    private void addEventHandlers(){
        gameModeView.getFillBoardRandomlyButton().setOnAction(event -> {
            GameView gameView = new GameView();
            GamePresenter gamePresenter = new GamePresenter(gameModel,gameView);
            gameModeView.getScene().setRoot(gameView);
            gameView.getScene().getWindow();
            gameModel.fillRandomly();

        });
        gameModeView.getReturnToMenuButton().setOnAction(event -> {
            HomescreenView homeView = new HomescreenView();
            HomescreenPresenter homePresenter = new HomescreenPresenter(gameModel,homeView);
            gameModeView.getScene().setRoot(homeView);
            homeView.getScene().getWindow();
        });
        gameModeView.getSetNamesButton().setOnAction(event -> {
            gameModeView.getFillBoardRandomlyButton().setDisable(false);
            gameModeView.getLoadInPieceLayoutButton().setDisable(false);
            gameModeView.getLoadSavedGameButton().setDisable(false);
            gameModeView.getSetNamesButton().setDisable(true);
            gameModel.setPlayerName(gameModeView.getPlayerNameOne().getText(),gameModeView.getPlayerNameTwo().getText());

        });
        gameModeView.getLoadInPieceLayoutButton().setOnAction(actionEvent -> {
            LoadSetupView lsView = new LoadSetupView();
            LoadSetupPresenter lsPresenter = new LoadSetupPresenter(gameModel,lsView);
            gameModeView.getScene().setRoot(lsView);
            lsView.getScene().getWindow();
        });
        gameModeView.getLoadSavedGameButton().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            gameModel.clearBoard();
            GameView gameView = new GameView();
            GamePresenter gamePresenter = new GamePresenter(gameModel,gameView);
            gameModeView.getScene().setRoot(gameView);
            gameView.getScene().getWindow();
            gameModel.loadSaveGame();
        });
    }
}
