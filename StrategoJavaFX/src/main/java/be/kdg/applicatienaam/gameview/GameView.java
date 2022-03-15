package be.kdg.applicatienaam.gameview;

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

public class GameView extends GridPane {
    Button btnUpdateView;
    Button btnSetPieces;
    Label tfNotifs;
    GridPane board;
    Button btnStartGame;
    Button endTurn;
    Button startTurn;
    Background background;
    TextField playerName;
    TextField notifications;
    List<ImageView> allPieces;
    List<Rectangle> coloredRectangles;
    Image enemyimage;
    ListView pieceList;
    public GameView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        btnUpdateView = new Button("update view");
        btnSetPieces = new Button("jord");
        endTurn = new Button("End turn");
        startTurn = new Button("Start turn");
        tfNotifs = new Label();
        board = new GridPane();
        btnStartGame = new Button("Fill Board");
        Image backgroundImage = new Image("/StrategoBoard.jpeg");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImageSetter = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        background = new Background(backgroundImageSetter);
        playerName = new TextField();
        notifications = new TextField();
        allPieces = new ArrayList<>();
        coloredRectangles = new ArrayList<>();
        pieceList = new ListView<String>();
        enemyimage = new Image("enemy.png");

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
        //board.setPadding(new Insets(10,10,10,10));
        GridPane.setConstraints(btnStartGame, 40, 35, 5, 3);
        GridPane.setConstraints(startTurn,45,35,5,3);
        GridPane.setConstraints(endTurn,50,35,5,3);
        GridPane.setConstraints(btnUpdateView, 55, 35, 5, 3);
        GridPane.setConstraints(btnSetPieces, 60, 35, 5, 3);
        GridPane.setConstraints(tfNotifs, 55, 30, 5, 3);
        GridPane.setConstraints(notifications, 45, 10, 20, 3);
        GridPane.setConstraints(pieceList , 60,10,100,15);
        notifications.setPrefHeight(200);
        notifications.setPrefHeight(2000);
        pieceList.setPrefHeight(200);
        pieceList.setPrefHeight(2000);
        this.getChildren().addAll(board, btnUpdateView, btnSetPieces, tfNotifs, btnStartGame, playerName, notifications,pieceList, startTurn, endTurn);
        board.setRotate(-90);
    }
// implementatie van de nodige

    public Label getTfNotifs() {
        return tfNotifs;
    }
// package-private Getters

    public Button getBtnStartGame() {
        return btnStartGame;
    }

    public void setPosition(String n, int x, int y) {
        Label z = new Label(n);
        z.setTextFill(Color.BLACK);
        board.add(z, x, y);
        GridPane.setHalignment(z, HPos.CENTER);
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

    public void removeFromGridpane(int x, int y) {
        for (Node image : allPieces) {
            if (image instanceof ImageView) {
                if (image.getId().equals(x + "" + y)) {
                    board.getChildren().remove(image);
                }
            }
        }

    }

    public void lightUpRectangles(List<int[]> moveArr) {
        int[] pos = new int[2];
        for (int i = 0; i < moveArr.size(); i++) {
            pos[0] = moveArr.get(i)[0];
            pos[1] = moveArr.get(i)[1];
            Rectangle rect = new Rectangle(78, 78);
            rect.setFill(Color.GREEN);
            rect.setOpacity(0.3);
            board.add(rect, pos[0], pos[1]);
            coloredRectangles.add(rect);
        }
    }

    public void lightUpRectanglesAttack(List<int[]> moveArr) {
        int[] pos = new int[2];
        for (int i = 0; i < moveArr.size(); i++) {
            pos[0] = moveArr.get(i)[0];
            pos[1] = moveArr.get(i)[1];
            Rectangle rect = new Rectangle(78, 78);
            rect.setFill(Color.RED);
            rect.setOpacity(0.4);
            board.add(rect, pos[0], pos[1]);
            coloredRectangles.add(rect);
        }
    }

    public void dimSquare() {
        for (Rectangle r : coloredRectangles) {
            board.getChildren().remove(r);
        }
    }

    public GridPane getBoard() {
        return board;
    }

    public Button getBtnSetPieces() {
        return btnSetPieces;
    }

    public Button getBtnUpdateView() {
        return btnUpdateView;
    }

    public TextField getNotifications() {
        return notifications;
    }

    public void setListItems(List<String> items){
        pieceList.setItems(FXCollections.observableList(items));
    }

    public ListView<String> getListView(){
        return  pieceList;
    }

    public Button getEndTurn() {
        return endTurn;
    }

    public Button getStartTurn() {
        return startTurn;
    }

    public Image getEnemyimage() {
        return enemyimage;
    }
}