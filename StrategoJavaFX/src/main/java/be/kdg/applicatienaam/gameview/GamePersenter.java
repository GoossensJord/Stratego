package be.kdg.applicatienaam.gameview;

import be.kdg.applicatienaam.model.ApplicatieNaamModel;
import be.kdg.applicatienaam.model.pieces.Bomb;
import be.kdg.applicatienaam.model.pieces.Rank;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class GamePersenter {
    private ApplicatieNaamModel model;
    private GameView view;

    public GamePersenter(ApplicatieNaamModel model, GameView view) {
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
        view.getBoard().addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
            System.out.println(e.getX()/78);
            System.out.println(e.getY()/78);

        });
    }

    private void updateView() {
        fillBoardWithImages();
    }

    private void fillBoardWithImages() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                view.setPosition(model.getBoard()[i][j].toString(), i, j);
                if(model.getBoard()[i][j].getPiece() instanceof Bomb) {
                    view.setPicture(model.getBoard()[i][j].getPiece().getImage(), i, j);
                }
            }
        }
    }

    public void addWindowEventHandlers() {
// Window event handlers (anon. inner klassen)
// Koppeling via view.getScene().getWindow()
    }
}
