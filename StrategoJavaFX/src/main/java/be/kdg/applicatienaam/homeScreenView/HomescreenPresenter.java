package be.kdg.applicatienaam.homeScreenView;

import be.kdg.applicatienaam.gameview.GameView;
import be.kdg.applicatienaam.homscreenModel.HomescreenModel;
import be.kdg.applicatienaam.model.ApplicatieNaamModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class HomescreenPresenter {

    private HomescreenModel model;
    private HomescreenView view;

    public HomescreenPresenter(HomescreenModel model, HomescreenView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
    }

    private void addEventHandlers() {

    }

    private void updateView() {

    }


}
