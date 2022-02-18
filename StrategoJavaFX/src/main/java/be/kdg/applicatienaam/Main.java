package be.kdg.applicatienaam;

import be.kdg.applicatienaam.model.ApplicatieNaamModel;
import be.kdg.applicatienaam.view.ApplicatieNaamPresenter;
import be.kdg.applicatienaam.view.ApplicatieNaamView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        ApplicatieNaamModel model = new ApplicatieNaamModel();
        ApplicatieNaamView view = new ApplicatieNaamView();
        ApplicatieNaamPresenter presenter = new ApplicatieNaamPresenter(model, view);
        primaryStage.setScene(new Scene(view));
        presenter.addWindowEventHandlers();
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}