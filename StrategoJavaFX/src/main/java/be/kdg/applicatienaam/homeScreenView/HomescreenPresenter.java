package be.kdg.applicatienaam.homeScreenView;

import be.kdg.applicatienaam.gameview.GamePresenter;
import be.kdg.applicatienaam.gameview.GameView;
import be.kdg.applicatienaam.model.StrategoModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;

public class HomescreenPresenter {

    private StrategoModel model;
    private HomescreenView view;

    public HomescreenPresenter(StrategoModel model, HomescreenView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
    }

    private void addEventHandlers() {
        view.getPlayRandomFill().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameView gameView = new GameView();
                GamePresenter gamePresenter = new GamePresenter(model, gameView);
                view.getScene().setRoot(gameView);
                gameView.getScene().getWindow();
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
