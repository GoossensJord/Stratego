package be.kdg.applicatienaam.arrangePiecesScreen;

import be.kdg.applicatienaam.model.GameModel;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


public class ArrangePiecesPresenter {
    private GameModel model;
    private ArrangePiecesView view;

    public ArrangePiecesPresenter(GameModel model, ArrangePiecesView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
    }

    private void addEventHandlers() {
        view.getListView().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getBoard().setDisable(false);
                String pieceStr = mouseEvent.getPickResult().toString().split(",")[0];
                pieceStr = pieceStr.substring(pieceStr.length() - 4);
                view.getNotifications().setText(pieceStr + " selected");
                //view.removeItemFromListView()
            }
        });
        view.getBtnSetPieces().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setItems();
            }
        });
        view.getBoard().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int x = (int) (mouseEvent.getX() / 78);
                int y = (int) (mouseEvent.getY() / 78);
                String pieceStr = view.getNotifications().getText().substring(1, 3);

                model.makePieceByString(pieceStr, x, y);
                fillBoardWithImages();
                view.dimSquare(x, y);
                view.removeListItem(pieceStr);
                view.getBoard().setDisable(true);
            }
        });
        view.getPlayer1().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getPlayer2().setDisable(true);
                view.getBtnSetPieces().setDisable(false);
                view.lightUpRectangles(1);
            }
        });
        view.getPlayer2().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getPlayer1().setDisable(true);
                view.getBtnSetPieces().setDisable(false);
                view.lightUpRectangles(2);
            }
        });
    }

    private void fillBoardWithImages() {
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < 10; j++) {
                if (model.getBoard()[i][j].getPiece() == null) view.setPosition("", i, j);
                else view.setPicture(model.getBoard()[i][j].getPiece().getImage(), i, j);
            }
        }
    }

    private void setItems() {
        view.setListItems(model.getAllPiecesString());
    }
}



