
package be.kdg.stratego.homescreenview;



import be.kdg.stratego.arrangepiecesscreen.ArrangePiecesPresenter;
import be.kdg.stratego.arrangepiecesscreen.ArrangePiecesView;
import be.kdg.stratego.leaderboard.LeaderboardPresenter;
import be.kdg.stratego.leaderboard.LeaderboardView;
import be.kdg.stratego.model.GameModel;
import be.kdg.stratego.selectgamemode.SelectGameModePresenter;
import be.kdg.stratego.selectgamemode.SelectGameModeView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Class responsible for presenting the home screen.
 */
public class HomescreenPresenter {

     GameModel gameModel;
     HomescreenView view;

    /**
     * Constructor which takes a model and a view to present the home screen
     */
    public HomescreenPresenter(GameModel gameModel, HomescreenView view) {
        this.gameModel = gameModel;
        this.view = view;
        this.addEventHandlers();
    }

    /**
     * Adds event handlers for the different buttons on the homescreen.
     */
    private void addEventHandlers() {
        view.getPlayButton().setOnAction(event -> {
            SelectGameModeView gameModeView = new SelectGameModeView();
            SelectGameModePresenter gamePresenter = new SelectGameModePresenter(gameModel, gameModeView);
            view.getScene().setRoot(gameModeView);
            gameModeView.getScene().getWindow();
        });
        view.getLayoutPiecesButton().setOnAction(event -> {
            ArrangePiecesView aView = new ArrangePiecesView();
            ArrangePiecesPresenter aPresenter = new ArrangePiecesPresenter(gameModel,aView);
            view.getScene().setRoot(aView);
            aView.getScene().getWindow();
        });
        view.getLeaderBoardButton().setOnAction(event -> {
            LeaderboardView lView = new LeaderboardView();
            LeaderboardPresenter lPresenter = new LeaderboardPresenter(gameModel,lView);
            view.getScene().setRoot(lView);
            lView.getScene().getWindow();
        });
        view.getEndGameButton().setOnAction(event -> System.exit(1));

    }

    public void addWindowEventHandlers() {
        view.getScene().getWindow().setOnCloseRequest(windowEvent -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Are you sure you want to quit?");
            alert.setTitle("Careful!");
            alert.getButtonTypes().clear();
            ButtonType yes = new ButtonType("Yes");
            ButtonType no = new ButtonType("No");
            alert.getButtonTypes().addAll(yes, no);
            alert.showAndWait();
            if (alert.getResult() == null || alert.getResult().equals(no)) windowEvent.consume();
        });


    }


}

