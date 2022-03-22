package be.kdg.stratego.gameview;

import be.kdg.stratego.homescreenview.HomescreenPresenter;
import be.kdg.stratego.homescreenview.HomescreenView;
import be.kdg.stratego.model.GameModel;
import be.kdg.stratego.model.GameSaveState;
import javafx.scene.input.MouseEvent;


import java.util.List;


public class GamePresenter {
    GameModel model;
    GameView view;

    public GamePresenter(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
    }

    /**
     * adds following event handlers: Start game, which fills the board. Start turn, which shows the current players pieces, end turn which hides all the pieces and links the board eventhandler
     */
    private void addEventHandlers() {
        view.getBtnStartGame().setOnAction(actionEvent -> {
            GameSaveState.setPlayerTurn(model.getPlayerByID(0));
            GameSaveState.setIdlePlayer(model.getPlayerByID(1));
            //model.fillRandomly();
            fillBoardWithImages();
            view.getBtnStartGame().setDisable(true);
            view.getBoard().setDisable(true);
            view.getStartTurn().setDisable(false);
        });
        view.getStartTurn().setOnAction(event -> {
            fillOnePlayer(GameSaveState.getPlayerTurn().getId());
            view.getStartTurn().setDisable(true);
            view.getEndTurn().setDisable(true);
            view.getBoard().setDisable(false);


        });
        view.getEndTurn().setOnAction(event -> {
            GameSaveState.switchTurn();
            hidePieces();
            view.getStartTurn().setDisable(false);
            view.getEndTurn().setDisable(true);
            view.getBoard().setDisable(true);

        });
        view.getBackToMainMenuButton().setOnAction(event -> {
            //model.clearBoardOfPieces();
            HomescreenView homeView = new HomescreenView();
            HomescreenPresenter homePresenter = new HomescreenPresenter(model,homeView);
            view.getScene().setRoot(homeView);
            homeView.getScene().getWindow();

        });
        view.getBoard().addEventHandler(MouseEvent.MOUSE_CLICKED, new boardEventHandler(model, view));
    }

    /**
     * Fills the board with the image linked to the piece
     */
    private void fillBoardWithImages() {
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < 10; j++) {
                if (model.getBoard()[i][j].getPiece() == null) view.setPosition("", i, j);
                    //else view.setPicture(model.getBoard()[i][j].getPiece().getImage(), i, j);
                else view.setPicture(view.getEnemyimage(), i, j);
            }
        }
    }

    /**
     * Fills the board with the image linked to the piece of one player
     * @param id Determines the player.
     */
    private void fillOnePlayer(int id) {
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < 10; j++) {
                view.removeFromGridpane(i, j);
            }
        }
        List<int[]> pieces = model.piecesOnePlayer(id);
        for (int[] piece : pieces) {
            view.setPicture(model.getBoard()[piece[0]][piece[1]].p.getImage(), piece[0], piece[1]);
        }
        List<int[]> piecesOtherPlayer = model.piecesOnePlayer(1 - id);
        for (int[] otherPlayerPiece : piecesOtherPlayer) {
            view.setPicture(view.getEnemyimage(), otherPlayerPiece[0], otherPlayerPiece[1]);
        }


    }

    /**
     * Hides all the pieces for turn change.
     */
    private void hidePieces() {
        List<int[]> allPieces = model.piecesOnePlayer(0);
        allPieces.addAll(model.piecesOnePlayer(1));

        for (int[] allPiece : allPieces) {
            view.removeFromGridpane(allPiece[0], allPiece[1]);
            view.setPicture(view.getEnemyimage(), allPiece[0], allPiece[1]);
        }
    }
}
