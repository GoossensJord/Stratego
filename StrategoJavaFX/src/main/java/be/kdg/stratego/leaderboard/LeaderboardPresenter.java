package be.kdg.stratego.leaderboard;

import be.kdg.stratego.LeaderBoardModel;
import be.kdg.stratego.homescreenview.HomescreenPresenter;
import be.kdg.stratego.homescreenview.HomescreenView;
import be.kdg.stratego.model.GameModel;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
/**
 * Class responsible for presenting the leaderboard
 */
public class LeaderboardPresenter {

    GameModel gameModel;
    LeaderboardView view;
    LeaderBoardModel leaderBoardModel = new LeaderBoardModel();
    /**
     * Constructor which takes a model and a view to present the leaderboard screen
     */
    public LeaderboardPresenter(GameModel gameModel, LeaderboardView view) {
        this.gameModel = gameModel;
        this.view = view;
        this.addEventHandlers();

    }


    /**
     * Adds event handlers for the different buttons.
     */
    private void addEventHandlers(){
        view.getBackToMainMenuButton().setOnAction(event -> {
            HomescreenView homeView = new HomescreenView();
            HomescreenPresenter homePresenter = new HomescreenPresenter(gameModel,homeView);
            view.getScene().setRoot(homeView);
            homeView.getScene().getWindow();

        });
        view.getResetHighScoresButton().setOnAction(event -> {

            try {
                PrintWriter pw = new PrintWriter("highScores.txt");
                pw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        });
        view.getUpdateLeaderboard().setOnAction(event -> view.getHighScores().setText(leaderBoardModel.getHighScoreString() + "\n"));
    }
}
