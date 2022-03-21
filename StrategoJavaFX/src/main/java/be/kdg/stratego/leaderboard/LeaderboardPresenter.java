package be.kdg.stratego.leaderboard;

import be.kdg.stratego.homescreenview.HomescreenPresenter;
import be.kdg.stratego.homescreenview.HomescreenView;
import be.kdg.stratego.model.GameModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class LeaderboardPresenter {

    GameModel gameModel;
    LeaderboardView view;

    public LeaderboardPresenter(GameModel gameModel, LeaderboardView view) {
        this.gameModel = gameModel;
        this.view = view;
        this.addEventHandlers();
    }

    private void addEventHandlers(){
        view.getBackToMainMenuButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomescreenView homeView = new HomescreenView();
                HomescreenPresenter homePresenter = new HomescreenPresenter(gameModel,homeView);
                view.getScene().setRoot(homeView);
                homeView.getScene().getWindow();

            }
        });
    }
}
