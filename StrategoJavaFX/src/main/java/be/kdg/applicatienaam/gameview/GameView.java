package be.kdg.applicatienaam.gameview;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class GameView extends GridPane {
    Button btnStartTurn;
    Button btnEndTurn;
    Label tfNotifs;
    GridPane board;
    Button btnStartGame;
    Background background;


    public GameView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        btnStartTurn = new Button("Start Turn");
        btnEndTurn = new Button("End Turn");
        tfNotifs = new Label();
        board = new GridPane();
        btnStartGame = new Button("Fill Board");
        Image backgroundImage = new Image("file:src/main/resources/StrategoBoard.jpeg");
        BackgroundSize backgroundSize = new BackgroundSize(100,100,true,true,true,false);
        BackgroundImage backgroundImageSetter = new BackgroundImage(backgroundImage,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,backgroundSize);
        background = new Background(backgroundImageSetter);
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
        this.setBackground(background);


        for (int i = 0; i < 10; i++) {
            board.getColumnConstraints().add(new ColumnConstraints(78));
            board.getRowConstraints().add(new RowConstraints(78));
        }
        GridPane.setConstraints(board, 0, 18, 4, 4);
        board.setPadding(new Insets(10,10,10,10));
        GridPane.setConstraints(btnStartGame, 40, 35,5,3);
        GridPane.setConstraints(btnStartTurn, 45, 35,5,3);
        GridPane.setConstraints(btnEndTurn, 50, 35,5,3);
        GridPane.setConstraints(tfNotifs, 55, 35,5,3);
        this.getChildren().addAll(board, btnStartTurn, btnEndTurn, tfNotifs, btnStartGame);
    }
// implementatie van de nodige
// package-private Getters

    public Button getBtnStartGame() {
        return btnStartGame;
    }

    public void setPosition(String n, int x, int y) {
        Label z = new Label(n);
        board.add(z, x, y);
        GridPane.setHalignment(z, HPos.CENTER);
    }
}