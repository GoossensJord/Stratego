package be.kdg.applicatienaam.gameview;

import be.kdg.applicatienaam.model.GameModel;
import be.kdg.applicatienaam.model.pieces.Piece;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;


public class GamePresenter {
     GameModel model;
     GameView view;
     int imageChange = 1;


    public GamePresenter(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBtnStartGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                model.fillRandomly();
                fillBoardWithImages();

                view.getNotifications().setText("Player 1, you're up");
                view.getBtnStartGame().setDisable(true);
            }
        });
        view.getStartTurn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fillOnePlayer(0);
                view.getStartTurn().setDisable(true);
            }
        });
        view.getEndTurn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fillOnePlayer(imageChange);
                if (imageChange == 1) {
                    imageChange--;
                } else imageChange++;

            }
        });
        view.getBoard().addEventHandler(MouseEvent.MOUSE_CLICKED, new boardEventHandler(model, view));

        view.getListView().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String pieceStr = mouseEvent.getPickResult().toString().split(",")[0];
                pieceStr = pieceStr.substring(pieceStr.length() - 4);
                view.getNotifications().setText(pieceStr + " selected");
                //view.removeItemFromListView()
            }
        });
      /* view.getBtnStartTurn().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updateView();
            }
        });*/
    }

    private void setItems() {
        view.setListItems(model.getAllPiecesString());
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
    private void fillOnePlayer(int id){
        Image enemyimage = new Image("enemy.png");
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < 10; j++) {
               view.removeFromGridpane(i,j);
            }
        }
        List<int[]> pieces = model.piecesOnePlayer(id);
        for (int[] piece: pieces) {
            view.setPicture(model.getBoard()[piece[0]][piece[1]].p.getImage(), piece[0],piece[1]);
        }
        List<int[]> piecesOtherPlayer = model.piecesOnePlayer(1-id);
        for (int[] otherPlayerPiece: piecesOtherPlayer) {
            view.setPicture(enemyimage,otherPlayerPiece[0],otherPlayerPiece[1]);
        }


    }


}
