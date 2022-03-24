package be.kdg.stratego.arrangepiecesscreen;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class ArrangePiecesView extends GridPane {

    GridPane board;
    Background background;
    TextArea notifications;
    Button btnSetPieces;
    Button player1;
    Button player2;
    Button saveSetup;
    Button loadSetupsBtn;
    Button returnToMenuButton;
    Button btnUseSetup;
    ListView commonListView;
    List<ImageView> allPieces;
    List<Rectangle> coloredRectangles;
    List<String> pieceListString;
    List<String> setupListString;
    boolean setupList;




    public ArrangePiecesView() {
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
        commonListView = new ListView<String>();
        allPieces = new ArrayList<>();
        coloredRectangles = new ArrayList<>();
        pieceListString = new ArrayList<>();
        player1 = new Button("Player 1");
        player2 = new Button("Player 2");
        saveSetup = new Button("Save Setup");
        btnSetPieces = new Button("Set Pieces");
        btnUseSetup = new Button("Use setup");
        returnToMenuButton = new Button("Quit");
        loadSetupsBtn = new Button("Saved Setups");
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
        GridPane.setConstraints(player2, 50, 20, 10, 4);
        GridPane.setConstraints(player1,42,20,10 ,4);
        GridPane.setConstraints(btnSetPieces,47,10,10,3);
        GridPane.setConstraints(btnUseSetup, 40,35 , 10,3);
        GridPane.setConstraints(saveSetup,48 ,35,10,3);
        GridPane.setConstraints(loadSetupsBtn,56,35,10,3);
        GridPane.setConstraints(returnToMenuButton, 64, 35, 10, 3);
        GridPane.setConstraints(notifications, 45, 30, 5, 5);
        GridPane.setConstraints(commonListView, 60, 10, 100, 15);



        this.getStylesheets().add("style.css");
        this.setId("gameBackground");
        player1.setId("selectGameModeButtonSmall");
        player2.setId("selectGameModeButtonSmall");
        saveSetup.setId("selectGameModeButtonSmall");
        btnSetPieces.setId("selectGameModeButtonSmall");
        btnUseSetup.setId("selectGameModeButtonSmall");
        returnToMenuButton.setId("selectGameModeButtonSmall");
        loadSetupsBtn.setId("selectGameModeButtonSmall");
        notifications.setId("textArea");
        commonListView.setPrefHeight(200);
        commonListView.setPrefHeight(2000);
        notifications.setMinWidth(2000);
        this.getChildren().addAll(board,player1,player2, btnSetPieces, notifications, commonListView,saveSetup, returnToMenuButton, loadSetupsBtn,btnUseSetup);
        board.setDisable(true);
        btnSetPieces.setDisable(true);
        board.setRotate(-90);
    }

    GridPane getBoard() {
        return board;
    }

    Button getSaveSetup() {
        return saveSetup;
    }

    Button getBtnSetPieces() {
        return btnSetPieces;
    }

    ListView<String> getListView() {
        return commonListView;
    }

    int getListViewLength(){
        return pieceListString.size();
    }

    TextArea getNotifications() {
        return notifications;
    }

    Button getPlayer1() {
        return player1;
    }

    Button getPlayer2() {
        return player2;
    }

    void setListItems(List<String> items) {
        if(setupList) setupListString = items;
        else pieceListString = items;
        commonListView.setItems(FXCollections.observableList(items));
    }
    public void setSetupList(boolean setupList) {
        this.setupList = setupList;
    }

    void removeListItem(String s){
        for (int i = 0; i < commonListView.getItems().size(); i++) {
            if(s.equals(commonListView.getItems().get(i))) {
                commonListView.getItems().remove(i);
                break;
            }
        }
        setListItems(pieceListString);
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

    void lightUpRectangles(int playerID) {
        int x;
        int y = 0;
        int start = 0;
        if (playerID == 0) {
            x = 3;
            y = 9;
        } else {
            start = 6;
            x = 9;
            y = 9;
        }

        for (int i = start; i <= x; i++) {
            for (int j = 0; j <= y; j++) {

                Rectangle rect = new Rectangle(78, 78);
                rect.setFill(Color.GREEN);
                rect.setOpacity(0.7);
                rect.setId(i + "" + j);
                coloredRectangles.add(rect);
                board.add(rect, i, j);
            }
        }
    }

    void dimSquare(int x, int y) {
        for (Rectangle r : coloredRectangles) {
            if (r.getId().equals(x + "" + y)) {
                board.getChildren().remove(r);
            }
        }
    }

    public Button getReturnToMenuButton() {
        return returnToMenuButton;
    }

    public Button getLoadSetupBtn() {
        return loadSetupsBtn;
    }
}


