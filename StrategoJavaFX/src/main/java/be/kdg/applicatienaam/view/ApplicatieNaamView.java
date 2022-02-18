package be.kdg.applicatienaam.view;

import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class ApplicatieNaamView extends GridPane {
    Button btnStartTurn;
    Button btnEndTurn;
    Label tfNotifs;
    GridPane board;
    Button btnStartGame;

    public ApplicatieNaamView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        btnStartTurn = new Button("Start Turn");
        btnEndTurn = new Button("End Turn");
        tfNotifs = new Label();
        board = new GridPane();
        btnStartGame = new Button("Fill Board");
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

        for (int i = 0; i < 10; i++) {
            board.getColumnConstraints().add(new ColumnConstraints(75));
            board.getRowConstraints().add(new RowConstraints(75));
        }
        GridPane.setConstraints(board, 1, 17, 4, 4);
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