package be.kdg.stratego.loadSetup;

import be.kdg.stratego.model.GameModel;
import be.kdg.stratego.model.GameSaveState;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class LoadSetupPresenter {
    private GameModel model;
    private LoadSetupView view;
    public LoadSetupPresenter(GameModel model, LoadSetupView view){
        this.model = model;
        this.view = view;
        addEventHandlers();
    }

    private void addEventHandlers(){
        view.getLoadSetupsBtn().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.setListItems(GameSaveState.getSetupStringList());
            }
        });
        view.getCommonListView().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //view.setListItems(GameSaveState.getSetupList()
                view.setListItems(new ArrayList<>());
                view.setListItems(GameSaveState.getSetupStringList());
            }
        });
        view.getListView().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    String pieceStr = mouseEvent.getPickResult().toString().split("\"")[1];
                    view.getNotifications().setText(pieceStr + " selected");
                    GameSaveState.loadSave(pieceStr);
                }catch (ArrayIndexOutOfBoundsException aiob){
                    System.out.println("smth foktop");
                }

                fillBoardWithImages();

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

}
