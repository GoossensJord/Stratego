package be.kdg.stratego.loadSetup;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import java.util.ArrayList;
import java.util.List;

public class LoadSetupView extends GridPane {

    GridPane board;
    Background background;
    TextField notifications;
    Button btnSetPieces;
    Button loadSetupsBtn;
    Button returnToMenuButton;
    Button btnUseSetup;
    Button player1;
    Button player2;
    ListView commonListView;
    List<String> setupListString;
    boolean setupList;
    Button resetBtn;
    List<ImageView> allPieces;
    Button confirmSetup;

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

        notifications = new TextField();
        commonListView = new ListView<String>();
        btnSetPieces = new Button("setPieces");
        btnUseSetup = new Button("Use this setup");
        returnToMenuButton = new Button("Quit");
        loadSetupsBtn = new Button("Get all saved setups");
        setupListString = new ArrayList<>();
        setupList = false;
        resetBtn = new Button("reset setup");
        allPieces = new ArrayList<>();
        player1 = new Button("Player 1");
        player2 = new Button("Player 2");
        confirmSetup = new Button("Confirm this setup");
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

        GridPane.setConstraints(loadSetupsBtn, 50, 15, 4, 4);
        GridPane.setConstraints(resetBtn, 40, 15, 4, 4);
        GridPane.setConstraints(returnToMenuButton, 60, 35, 10, 2);
        GridPane.setConstraints(player2, 50, 20, 4, 4);
        GridPane.setConstraints(player1, 45, 20, 4, 4);
        GridPane.setConstraints(confirmSetup, 40, 20, 4, 4);

        commonListView.setPrefHeight(200);
        commonListView.setPrefHeight(2000);
        notifications.setMinWidth(2000);
        this.getChildren().addAll(board, resetBtn, notifications, commonListView, loadSetupsBtn, returnToMenuButton, player1, player2, confirmSetup);

        board.setDisable(true);
        btnSetPieces.setDisable(true);
        commonListView.setDisable(true);
        loadSetupsBtn.setDisable(true);
        board.setRotate(-90);
    }


    void setListItems(List<String> items) {
        setupListString = items;
        commonListView.setItems(FXCollections.observableList(items));
    }

    public ListView getCommonListView() {
        return commonListView;
    }

    public Button getLoadSetupsBtn() {
        return loadSetupsBtn;
    }

    public Button getConfirmSetup() {
        return confirmSetup;
    }

    public ListView getListView() {
        return commonListView;
    }

    public TextField getNotifications() {
        return notifications;
    }

    void setPicture(Image image, int x, int y) {

        ImageView imageview = new ImageView(image);
        board.setHalignment(imageview, HPos.CENTER);

        imageview.setFitWidth(78);
        imageview.setFitHeight(54);
        imageview.setRotate(90);

        imageview.setId(x + "" + y);
        board.add(imageview, x, y);
        allPieces.add(imageview);
    }

    void setPosition(String n, int x, int y) {
        Label z = new Label(n);
        z.setTextFill(Color.BLACK);
        board.add(z, x, y);
        GridPane.setHalignment(z, HPos.CENTER);
    }

    public Button getResetBtn() {
        return resetBtn;
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

    Button getPlayer1() {
        return player1;
    }

    Button getPlayer2() {
        return player2;
    }



}


