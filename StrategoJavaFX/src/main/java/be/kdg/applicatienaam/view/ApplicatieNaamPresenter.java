package be.kdg.applicatienaam.view;

import be.kdg.applicatienaam.model.ApplicatieNaamModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;

public class ApplicatieNaamPresenter {
    private ApplicatieNaamModel model;
    private ApplicatieNaamView view;

    public ApplicatieNaamPresenter(ApplicatieNaamModel model, ApplicatieNaamView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();

        this.updateView();
    }

    private void addEventHandlers() {
        view.getBtnStartGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                model.playStratego();
                updateView();
            }
        });
    }

    private void updateView() {
        fillBoardWithImages();
    }

    private void fillBoardWithImages() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                view.setPosition(model.getBoard()[i][j].toString(), i, j);
            }
        }
    }

    public void addWindowEventHandlers() {
// Window event handlers (anon. inner klassen)
// Koppeling via view.getScene().getWindow()
    }
}
