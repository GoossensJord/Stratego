package be.kdg.stratego;

import be.kdg.stratego.homeScreenView.HomescreenPresenter;
import be.kdg.stratego.homeScreenView.HomescreenView;
import be.kdg.stratego.model.GameModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        HomescreenView view = new HomescreenView();
        GameModel model = new GameModel();
        HomescreenPresenter presenter = new HomescreenPresenter(model,view);
        primaryStage.setScene(new Scene(view));
        primaryStage.setMaximized(true);
        presenter.addWindowEventHandlers();
//xd
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
        System.setProperty("javafx.sg.warn", "true");
    }
}