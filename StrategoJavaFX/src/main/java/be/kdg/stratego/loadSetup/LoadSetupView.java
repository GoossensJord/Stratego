package be.kdg.stratego.loadSetup;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


import java.util.ArrayList;
import java.util.List;

public class LoadSetupView extends GridPane {

    GridPane board;
    Background background;
    TextArea notifications;
    Button btnSetPieces;
    Button loadSetupsBtn;
    Button returnToMenuButton;
    Button btnUseSetup;
    Button player1;
    Button player2;
    Button confirmSetup;
    Button startGame;
    Button resetBtn;
    ListView<String> commonListView;
    List<String> setupListString;
    boolean setupList;
    List<ImageView> allPieces;


    public LoadSetupView() {
        this.initialiseNodes();
        this.layoutNodes();
    }


    private void initialiseNodes() {
        board = new GridPane();
        Image backgroundImage = new Image("/StrategoBoard.jpeg");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImageSetter = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        background = new Background(backgroundImageSetter);

        notifications = new TextArea();
        commonListView = new ListView<>();
        btnSetPieces = new Button("Set Pieces");
        btnUseSetup = new Button("Use Setup");
        returnToMenuButton = new Button("Quit");
        loadSetupsBtn = new Button("Saved setups");
        setupListString = new ArrayList<>();
        setupList = false;
        allPieces = new ArrayList<>();
        player1 = new Button("Player 1");
        player2 = new Button("Player 2");
        confirmSetup = new Button("Confirm Setup");
        startGame = new Button("Start Game");
        resetBtn = new Button("reset setup");

    }


    private void layoutNodes() {

        for (int i = 0; i < 40; i++) {
            this.getRowConstraints().add(new RowConstraints(20));
        }
        for (int i = 0; i < 71; i++) {
            this.getColumnConstraints().add(new ColumnConstraints(20));
        }
        this.setGridLinesVisible(false);
        board.setGridLinesVisible(true);
        board.setBackground(background);


        for (int i = 0; i < 10; i++) {
            board.getColumnConstraints().add(new ColumnConstraints(78));
            board.getRowConstraints().add(new RowConstraints(78));
        }
        GridPane.setConstraints(board, 0, 18, 4, 4);


        GridPane.setConstraints(notifications, 45, 30, 5, 5);
        GridPane.setConstraints(commonListView, 60, 10, 100, 15);
        GridPane.setConstraints(loadSetupsBtn,56,35,10,3);
        GridPane.setConstraints(returnToMenuButton, 64, 35, 10, 3);
        GridPane.setConstraints(player2, 50, 15, 10, 3);
        GridPane.setConstraints(player1,42,15,10 ,3);
        GridPane.setConstraints(confirmSetup, 50, 20, 10, 3);
        GridPane.setConstraints(resetBtn,42,20,10,3);
        GridPane.setConstraints(startGame, 48, 35, 10, 3);

        this.getStylesheets().add("style.css");
        this.setId("gameBackground");
        player1.setId("selectGameModeButtonSmall");
        player2.setId("selectGameModeButtonSmall");
        btnSetPieces.setId("selectGameModeButtonSmall");
        loadSetupsBtn.setId("selectGameModeButtonSmall");
        returnToMenuButton.setId("selectGameModeButtonSmall");
        btnUseSetup.setId("selectGameModeButtonSmall");
        confirmSetup.setId("selectGameModeButtonSmall");
        startGame.setId("selectGameModeButtonSmall");
        resetBtn.setId("selectGameModeButtonSmall");



        commonListView.setPrefHeight(200);
        commonListView.setPrefHeight(2000);
        notifications.setMinWidth(2000);
        this.getChildren().addAll(board,  notifications, resetBtn ,commonListView, loadSetupsBtn, returnToMenuButton, player1, player2, confirmSetup, startGame);

        board.setDisable(true);
        btnSetPieces.setDisable(true);
        commonListView.setDisable(true);
        loadSetupsBtn.setDisable(true);
        startGame.setDisable(true);
        confirmSetup.setDisable(true);
        resetBtn.setDisable(true);
        board.setRotate(-90);
    }


    void setListItems(List<String> items) {
        setupListString = items;
        commonListView.setItems(FXCollections.observableList(items));
    }

    public ListView<String> getCommonListView() {
        return commonListView;
    }

    public Button getLoadSetupsBtn() {
        return loadSetupsBtn;
    }

    public Button getConfirmSetup() {
        return confirmSetup;
    }

    public ListView<String> getListView() {
        return commonListView;
    }

    public Button getStartGame() {
        return startGame;
    }

    public TextArea getNotifications() {
        return notifications;
    }

    void setPicture(Image image, int x, int y) {

        ImageView imageview = new ImageView(image);
        setHalignment(imageview, HPos.CENTER);

        imageview.setFitWidth(78);
        imageview.setFitHeight(54);
        imageview.setRotate(90);

        imageview.setId(x + "" + y);
        board.add(imageview, x, y);
        allPieces.add(imageview);
    }

    void setPosition(int x, int y) {
        Label z = new Label("");
        z.setTextFill(Color.BLACK);
        board.add(z, x, y);
        GridPane.setHalignment(z, HPos.CENTER);
    }

    public void removeFromGridpane(int x, int y) {
        for (Node image : allPieces) {
            if (image instanceof ImageView) {
                if (image.getId().equals(x + "" + y)) {
                    board.getChildren().remove(image);
                }
            }
        }
    }

    public Button getResetBtn() {
        return resetBtn;
    }

    Button getPlayer1() {
        return player1;
    }

    Button getPlayer2() {
        return player2;
    }

    public Button getReturnToMenuButton() {
        return returnToMenuButton;
    }
}


