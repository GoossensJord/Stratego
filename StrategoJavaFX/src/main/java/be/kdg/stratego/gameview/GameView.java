package be.kdg.stratego.gameview;

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


/**
 * Class responsible for the laout of the game itself.
 */
public class GameView extends GridPane {

    GridPane board;
    Button btnStartGame;
    Button endTurn;
    Button startTurn;
    Button backToMainMenuButton;
    Background background;
    TextArea notifications;
    List<ImageView> allPieces;
    List<Rectangle> coloredRectangles;
    Image enemyimage;
    ImageView textFieldImage;
    ListView pieceList;


    /**
     * Constructor which initialises and does the layout for the nodes
     */
    public GameView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    /**
     * Method to initialise the nodes.
     */
    private void initialiseNodes() {

        endTurn = new Button("End Turn");
        startTurn = new Button("Start Turn");
        board = new GridPane();
        btnStartGame = new Button("Fill Board");
        backToMainMenuButton = new Button("Exit Game");


        Image backgroundImage = new Image("/StrategoBoard.jpeg");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImageSetter = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        background = new Background(backgroundImageSetter);
        notifications = new TextArea();
        allPieces = new ArrayList<>();
        coloredRectangles = new ArrayList<>();
        pieceList = new ListView<String>();
        enemyimage = new Image("enemy.png");
        textFieldImage = new ImageView(new Image("nodeFrame.png"));

    }

    /**
     * Method for laying out the nodes
     */
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


        GridPane.setConstraints(textFieldImage, 43, 4, 17, 23);
        GridPane.setConstraints(notifications, 45, 7, 12, 13);
        GridPane.setConstraints(board, 0, 18, 4, 4);
        GridPane.setConstraints(btnStartGame, 40, 35, 10, 3);
        GridPane.setConstraints(startTurn, 48, 35, 10, 3);
        GridPane.setConstraints(endTurn, 56, 35, 10, 3);
        GridPane.setConstraints(backToMainMenuButton, 64, 35, 10, 3);



        notifications.setPrefHeight(200);
        notifications.setPrefHeight(2000);

        this.getChildren().addAll(board, btnStartGame, notifications, startTurn, endTurn, backToMainMenuButton, textFieldImage);
        board.setRotate(-90);
        this.getStylesheets().add("style.css");
        btnStartGame.setId("gameButton");
        startTurn.setId("gameButton");
        endTurn.setId("gameButton");
        backToMainMenuButton.setId("gameButton");
        notifications.setId("gameNotification");
        notifications.setEditable(false);
        notifications.setWrapText(true);
        startTurn.setDisable(true);
        endTurn.setDisable(true);
        this.setId("gameBackground");
    }


    /**
     * fills the squares without a piece, thus without a picture with a string
     */
    public void setPosition(String n, int x, int y) {
        Label z = new Label(n);
        z.setTextFill(Color.BLACK);
        board.add(z, x, y);
        GridPane.setHalignment(z, HPos.CENTER);
    }

    /**
     * Fills the squares with the appropriate image and adds it to the gridpane.
     */
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

    /**
     * Removes an image from the gridpane, this method is called upon when one loses a piece.
     */
    public void removeFromGridpane(int x, int y) {
        for (Node image : allPieces) {
            if (image instanceof ImageView) {
                if (image.getId().equals(x + "" + y)) {
                    board.getChildren().remove(image);
                }
            }
        }
    }

    /**
     * Method to light up all moveable squares on the board
     *
     * @param moveArr Requires a list of moves to light them up on the board
     */
    public void lightUpRectanglesMoves(List<int[]> moveArr) {
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

    /**
     * Method to light up all attackable squares on the board
     *
     * @param attackArr Requires a list of attacks to light them up on the board
     */
    public void lightUpRectanglesAttack(List<int[]> attackArr) {
        int[] pos = new int[2];
        for (int i = 0; i < attackArr.size(); i++) {
            pos[0] = attackArr.get(i)[0];
            pos[1] = attackArr.get(i)[1];
            Rectangle rect = new Rectangle(78, 78);
            rect.setFill(Color.RED);
            rect.setOpacity(0.3);
            board.add(rect, pos[0], pos[1]);
            coloredRectangles.add(rect);
        }
    }

    /**
     * Method to dim all the lit up squares on the board
     */
    public void dimSquare() {
        for (Rectangle r : coloredRectangles) {
            board.getChildren().remove(r);
        }
    }


    public Button getBtnStartGame() {
        return btnStartGame;
    }

    public GridPane getBoard() {
        return board;
    }


    public TextArea getNotifications() {
        return notifications;
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

    public Button getBackToMainMenuButton() {
        return backToMainMenuButton;
    }


}