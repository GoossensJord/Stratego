package be.kdg.applicatienaam;

import be.kdg.applicatienaam.homeScreenView.HomescreenPresenter;
import be.kdg.applicatienaam.homeScreenView.HomescreenView;
import be.kdg.applicatienaam.model.StrategoModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        HomescreenView view = new HomescreenView();
        StrategoModel model = new StrategoModel();
        HomescreenPresenter presenter = new HomescreenPresenter(model,view);
        primaryStage.setScene(new Scene(view));
        primaryStage.setMaximized(true);
        presenter.addWindowEventHandlers();

        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}