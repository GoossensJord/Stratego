package be.kdg.stratego;

import be.kdg.stratego.homescreenview.HomescreenPresenter;
import be.kdg.stratego.homescreenview.HomescreenView;
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
        Scene scene = new Scene(view);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        presenter.addWindowEventHandlers();
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}