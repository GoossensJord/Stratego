package be.kdg.applicatienaam;

import be.kdg.applicatienaam.model.ApplicatieNaamModel;
import be.kdg.applicatienaam.gameview.GamePersenter;
import be.kdg.applicatienaam.gameview.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        ApplicatieNaamModel model = new ApplicatieNaamModel();
        GameView view = new GameView();
        GamePersenter presenter = new GamePersenter(model, view);
        primaryStage.setScene(new Scene(view));
        presenter.addWindowEventHandlers();
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}