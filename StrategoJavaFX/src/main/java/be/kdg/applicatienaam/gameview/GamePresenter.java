package be.kdg.applicatienaam.gameview;

import be.kdg.applicatienaam.model.GameModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class GamePresenter {
    private GameModel model;
    private GameView view;

    public GamePresenter(GameModel model, GameView view) {
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
        view.getBtnEndTurn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("lol");
            }
        });
        view.getBoard().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {


            int x = (int) (e.getX() / 78);
            int y = (int) (e.getY() / 78);

            int[] moveArr = model.getMoves(model.choosePiece(x, y));
            if (moveArr != null && (moveArr[0] != 0 || moveArr[1] !=0)) {
                view.getNotifications().setText("Moves for piece " + (9-x) + " " + y + " available!");
                view.lightUp(coordConverter(moveArr));
                coordConverter(moveArr);
                System.out.println("chosen piece \t" + model.choosePiece((int) e.getX() / 78, (int) e.getY() / 78).toString());

            } else
                view.getNotifications().setText("No moves for piece " + (9-x) + " " + y + " available");


            //System.out.println("first convert \t" + x + " " + y);
            //System.out.println("chosen coords on gridpan\t" + e.getX() / 78 + " " + e.getY() / 78);


            //view.lightUp(moveArr);
        });
    }


    private void updateView() {
        fillBoardWithImages();
    }

    private void fillBoardWithImages() {
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < 10; j++) {
                if (model.getBoard()[i][j].getPiece() == null) view.setPosition("", i, j);
                    //   else view.setPosition(model.getBoard()[i][j].getPiece().toString(), i, j);
                else view.setPicture(model.getBoard()[i][j].getPiece().getImage(), i, j);
            }
        }
    }

    private int[] coordConverter(int[] moveArr) {
        int[] temparr = new int[2];
        int convertedX = 9 - moveArr[0];
        int convertedY = 9 - moveArr[1];
        temparr[0] = convertedX;
        temparr[1] = convertedY;
        System.out.println("Movearr " + temparr[0] + " " + temparr[1]);

        return temparr;
    }

    public void moveImage() {

    }

}
