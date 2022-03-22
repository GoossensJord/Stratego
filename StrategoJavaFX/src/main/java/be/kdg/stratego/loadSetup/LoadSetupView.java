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
    ListView commonListView;
    List<String> setupListString;
    boolean setupList;


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
        GridPane.setConstraints(returnToMenuButton, 60, 35, 10, 2);

        commonListView.setPrefHeight(200);
        commonListView.setPrefHeight(2000);
        notifications.setMinWidth(2000);
        this.getChildren().addAll(board, notifications, commonListView, loadSetupsBtn, returnToMenuButton);
        board.setDisable(true);
        btnSetPieces.setDisable(true);
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
    }
    void setPosition(String n, int x, int y) {
        Label z = new Label(n);
        z.setTextFill(Color.BLACK);
        board.add(z, x, y);
        GridPane.setHalignment(z, HPos.CENTER);
    }

}


