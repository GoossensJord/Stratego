package be.kdg.applicatienaam;

import be.kdg.applicatienaam.gameview.GamePresenter;
import be.kdg.applicatienaam.gameview.GameView;
import be.kdg.applicatienaam.homeScreenView.HomescreenPresenter;
import be.kdg.applicatienaam.homeScreenView.HomescreenView;
import be.kdg.applicatienaam.homscreenModel.HomescreenModel;
import be.kdg.applicatienaam.model.ApplicatieNaamModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeScreenTestMain extends Application{
    @Override
    public void start(Stage primaryStage) {

        HomescreenView view = new HomescreenView();
        HomescreenModel model = new HomescreenModel();
        HomescreenPresenter presenter = new HomescreenPresenter(model,view);
        primaryStage.setScene(new Scene(view));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

