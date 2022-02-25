package be.kdg.applicatienaam.gameview;

import be.kdg.applicatienaam.model.ApplicatieNaamModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class GamePresenter {
    private ApplicatieNaamModel model;
    private GameView view;

    public GamePresenter(ApplicatieNaamModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBtnStartGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                model.playStratego();
                updateView();
            }
        });
        view.getBoard().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            //X val = 9-getX
            //y vval = X
            int y = (int) (e.getY() / 78);
            int x = 9 - (int) (e.getX() / 78);

            List<int[]> moveArr = model.getMoves(model.choosePiece((int) e.getX() / 78, (int) e.getY() / 78));
            //view.lightUp(coordConverter(moveArr));
            coordConverter(moveArr);
            System.out.println("first convert \t" + x + " " + y);
            System.out.println("chosen coords on gridpan\t" + e.getX() / 78 + " " + e.getY() / 78);
            System.out.println("chosen piece \t" + model.choosePiece((int) e.getX() / 78, (int) e.getY() / 78).toString());
            //view.lightUp(moveArr);
        });
    }

    private void updateView() {
        fillBoardWithImages();
    }

    private void fillBoardWithImages() {
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < 10; j++) {
                if (model.getBoard()[i][j].getPiece() == null) view.setPosition("empty square", i, j);
                else view.setPosition(model.getBoard()[i][j].getPiece().toString(), i, j);
                //else view.setPicture(model.getBoard()[i][j].getPiece().getImage(), i, j);
            }
        }
    }

    private List<int[]> coordConverter(List<int[]> moveArr) {
        List<int[]> temparr = new ArrayList<>();
        for (int i = 0; i < moveArr.size(); i++) {

            int temp = 9 - moveArr.get(i)[0];
            temparr.add(new int[]{temp, moveArr.get(i)[1]});
            System.out.println("Movearr " + temparr.get(i)[0] + " " + temparr.get(i)[1]);
        }
        return temparr;
    }

    public void addWindowEventHandlers() {
// Window event handlers (anon. inner klassen)
// Koppeling via view.getScene().getWindow()
    }
}
