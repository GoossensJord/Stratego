package be.kdg.applicatienaam.homeScreenView;

import be.kdg.applicatienaam.ArrangePiecesModel;
import be.kdg.applicatienaam.arrangePiecesScreen.ArrangePiecesPresenter;
import be.kdg.applicatienaam.arrangePiecesScreen.ArrangePiecesView;
import be.kdg.applicatienaam.gameview.GamePresenter;
import be.kdg.applicatienaam.gameview.GameView;
import be.kdg.applicatienaam.model.GameModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.WindowEvent;

public class HomescreenPresenter {

    private GameModel gameModel;
    private HomescreenView view;
    private ArrangePiecesModel arrangePiecesModel;

    public HomescreenPresenter(GameModel gameModel, HomescreenView view) {
        this.gameModel = gameModel;
        this.view = view;
        this.addEventHandlers();
    }

    private void addEventHandlers() {
        view.getPlayRandomFill().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameView gameView = new GameView();
                GamePresenter gamePresenter = new GamePresenter(gameModel, gameView);
                view.getScene().setRoot(gameView);
                gameView.getScene().getWindow();
            }
        });
        view.getPlayClassic().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrangePiecesView arrangePiecesView = new ArrangePiecesView();
                ArrangePiecesPresenter arrangePiecesPresenter = new ArrangePiecesPresenter(arrangePiecesModel,arrangePiecesView);
                view.getScene().setRoot(arrangePiecesView);
                arrangePiecesView.getScene().getWindow();
            }
        });
        view.getEndGame().setOnAction(new EventHandler<ActionEvent>() {
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
                alert.getButtonTypes().addAll(no, yes);
                alert.showAndWait();
                if (alert.getResult() == null || alert.getResult().equals(no)) windowEvent.consume();
            }
        });


    }


    private void updateView() {

    }


}
