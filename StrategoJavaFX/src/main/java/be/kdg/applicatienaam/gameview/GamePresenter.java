package be.kdg.applicatienaam.gameview;

import be.kdg.applicatienaam.model.GameModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


import java.util.List;


public class GamePresenter {
     GameModel model;
     GameView view;
     int imageChange = 0;



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


                view.getBtnStartGame().setDisable(true);
                view.getBoard().setDisable(true);
                view.getEndTurn().setDisable(true);
            }
        });
        view.getStartTurn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fillOnePlayer(imageChange);
                if (imageChange == 1) {
                    imageChange--;
                } else imageChange++;
                view.getStartTurn().setDisable(true);
                view.getEndTurn().setDisable(true);
                view.getBoard().setDisable(false);


            }
        });
        view.getEndTurn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                waitingTimeTurnChange();
                view.getStartTurn().setDisable(false);
                view.getEndTurn().setDisable(true);
                view.getBoard().setDisable(true);

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
                //else view.setPicture(model.getBoard()[i][j].getPiece().getImage(), i, j);
                else view.setPicture(view.getEnemyimage(), i, j);
            }
        }
    }
    private void fillOnePlayer(int id){
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
            view.setPicture(view.getEnemyimage(),otherPlayerPiece[0],otherPlayerPiece[1]);
        }


    }
    private void waitingTimeTurnChange(){
        List<int[]> allPieces = model.piecesOnePlayer(0);
        allPieces.addAll(model.piecesOnePlayer(1));

        for (int [] allPiece: allPieces) {
            view.removeFromGridpane(allPiece[0],allPiece[1]);
            view.setPicture(view.getEnemyimage(),allPiece[0],allPiece[1]);
        }

    }


}
