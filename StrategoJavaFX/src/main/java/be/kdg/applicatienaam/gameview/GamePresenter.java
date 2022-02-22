package be.kdg.applicatienaam.gameview;

import be.kdg.applicatienaam.model.ApplicatieNaamModel;
import be.kdg.applicatienaam.model.pieces.Bomb;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class GamePresenter {
    private ApplicatieNaamModel model;
    private GameView view;

    public GamePresenter(ApplicatieNaamModel model, GameView view) {
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
            //System.out.println(e.getX()/78);
            //System.out.println(e.getY()/78);
            //X val = 9-getX
            //y vval = X
            int y = (int)(e.getY()/78);
            int x = 9-(int)(e.getX()/78);
            System.out.println(x + " " + y);
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
