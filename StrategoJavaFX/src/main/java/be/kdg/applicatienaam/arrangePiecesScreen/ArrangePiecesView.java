package be.kdg.applicatienaam.arrangePiecesScreen;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
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

public class ArrangePiecesView extends GridPane {

    GridPane board;
    Background background;
    TextField notifications;
    Button btnSetPieces;
    Button player1;
    Button player2;
    ListView pieceList;
    List<ImageView> allPieces;
    List<Rectangle> coloredRectangles;
    List<String> itemList;

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
        btnSetPieces = new Button("setPieces");
        notifications = new TextField();
        pieceList = new ListView<String>();
        allPieces = new ArrayList<>();
        coloredRectangles = new ArrayList<>();
        itemList = new ArrayList<>();
        player1 = new Button("Player 1");
        player2 = new Button("Player 2");

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
        GridPane.setConstraints(player2, 50, 20, 4, 4);
        GridPane.setConstraints(player1,45,20,4 ,4);
        GridPane.setConstraints(btnSetPieces,47,25,4,4);
        GridPane.setConstraints(notifications, 45, 30, 5, 5);
        GridPane.setConstraints(pieceList, 60, 10, 100, 15);

        pieceList.setPrefHeight(200);
        pieceList.setPrefHeight(2000);

        this.getChildren().addAll(board,player1,player2, btnSetPieces, notifications, pieceList);
        board.setDisable(true);
        btnSetPieces.setDisable(true);
        board.setRotate(-90);
    }


    public GridPane getBoard() {
        return board;
    }

    public Button getBtnSetPieces() {
        return btnSetPieces;
    }


    public ListView<String> getListView() {
        return pieceList;
    }

    public void setListItems(List<String> items) {
        itemList = items;
        pieceList.setItems(FXCollections.observableList(items));
    }

    public void removeListItem(String s){
        for (int i = 0; i < itemList.size(); i++) {
            if(s.equals(itemList.get(i))) {
                itemList.remove(i);
                break;
            }

        }
        setListItems(itemList);
    }

    public TextField getNotifications() {
        return notifications;
    }

    public Button getPlayer1() {
        return player1;
    }

    public Button getPlayer2() {
        return player2;
    }

    public void setPicture(Image image, int x, int y) {

        ImageView imageview = new ImageView(image);
        board.setHalignment(imageview, HPos.CENTER);

        imageview.setFitWidth(78);
        imageview.setFitHeight(54);
        imageview.setRotate(90);

        imageview.setId(x + "" + y);
        board.add(imageview, x, y);
        allPieces.add(imageview);
    }

    public void setPosition(String n, int x, int y) {
        Label z = new Label(n);
        z.setTextFill(Color.BLACK);
        board.add(z, x, y);
        GridPane.setHalignment(z, HPos.CENTER);
    }

    public void lightUpRectangles(int playerID) {
        int x = 0;
        int y = 0;
        int start = 0;
        if (playerID == 1) {
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

    public void dimSquare(int x, int y) {
        for (Rectangle r : coloredRectangles) {
            if (r.getId().equals(x + "" + y)) {
                board.getChildren().remove(r);
            }
        }
    }

}


