package be.kdg.stratego.selectgamemode;

import be.kdg.stratego.SelectGameModeModel;
import be.kdg.stratego.gameview.GamePresenter;
import be.kdg.stratego.gameview.GameView;
import be.kdg.stratego.homescreenview.HomescreenPresenter;
import be.kdg.stratego.homescreenview.HomescreenView;
import be.kdg.stratego.model.GameModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SelectGameModePresenter {

    GameModel gameModel;
    SelectGameModeModel selectGameModeModel;
    SelectGameModeView gameModeView;

    public SelectGameModePresenter(SelectGameModeModel selectGameModel, SelectGameModeView gameModeView){
        this.selectGameModeModel = selectGameModel;
        this.gameModeView = gameModeView;
        addEventHandlers();
    }

    /**
     * Adds event handlers for the different buttons on the homescreen.
     */
    private void addEventHandlers(){
        gameModeView.getFillBoardRandomlyButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameView gameView = new GameView();
                GamePresenter gamePresenter = new GamePresenter(gameModel,gameView);
                gameModeView.getScene().setRoot(gameView);
                gameView.getScene().getWindow();

            }
        });
        gameModeView.getReturnToMenuButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomescreenView homeView = new HomescreenView();
                HomescreenPresenter homePresenter = new HomescreenPresenter(gameModel,homeView);
                gameModeView.getScene().setRoot(homeView);
                homeView.getScene().getWindow();
            }
        });
        gameModeView.getSetNamesButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameModeView.getFillBoardRandomlyButton().setDisable(false);
                gameModeView.getLoadInPieceLayoutButton().setDisable(false);
                gameModeView.getLoadSavedGameButton().setDisable(false);
            }
        });
    }
}
