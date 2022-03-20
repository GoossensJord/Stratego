
package be.kdg.stratego.homescreenview;



//import be.kdg.stratego.arrangePiecesScreen.ArrangePiecesPresenter;
//import be.kdg.stratego.arrangePiecesScreen.ArrangePiecesView;
import be.kdg.stratego.SelectGameModeModel;
import be.kdg.stratego.arrangepiecesscreen.ArrangePiecesPresenter;
import be.kdg.stratego.arrangepiecesscreen.ArrangePiecesView;
import be.kdg.stratego.gameview.GamePresenter;
import be.kdg.stratego.gameview.GameView;
import be.kdg.stratego.model.GameModel;
import be.kdg.stratego.selectgamemode.SelectGameModePresenter;
import be.kdg.stratego.selectgamemode.SelectGameModeView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.WindowEvent;

public class HomescreenPresenter {

     GameModel gameModel;
     HomescreenView view;

    public HomescreenPresenter(GameModel gameModel, HomescreenView view) {
        this.gameModel = gameModel;
        this.view = view;
        this.addEventHandlers();
    }

    /**
     * Adds event handlers for the different buttons on the homescreen.
     */
    private void addEventHandlers() {
        view.getPlayButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SelectGameModeModel gameModeModel = new SelectGameModeModel();
                SelectGameModeView gameModeView = new SelectGameModeView();
                SelectGameModePresenter gamePresenter = new SelectGameModePresenter(gameModeModel, gameModeView);
                view.getScene().setRoot(gameModeView);
                gameModeView.getScene().getWindow();
            }
        });
        view.getLayoutPiecesButton().setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                ArrangePiecesView aView = new ArrangePiecesView();
                ArrangePiecesPresenter aPresenter = new ArrangePiecesPresenter(gameModel,aView);
                view.getScene().setRoot(aView);
                aView.getScene().getWindow();
            }
        });
        view.getEndGameButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(1);
            }
        });

    }

    public void addWindowEventHandlers() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Are you sure you want to quit?");
                alert.setTitle("Careful!");
                alert.getButtonTypes().clear();
                ButtonType yes = new ButtonType("Yes");
                ButtonType no = new ButtonType("No");
                alert.getButtonTypes().addAll(yes, no);
                alert.showAndWait();
                if (alert.getResult() == null || alert.getResult().equals(no)) windowEvent.consume();
            }
        });


    }


}

