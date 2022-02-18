package be.kdg.applicatienaam.view;

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
    TextField tfNotifs;
    GridPane board;
    Button btnStartGame;

    public ApplicatieNaamView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        btnStartTurn = new Button("Start Button");
        btnEndTurn = new Button("End Button");
        tfNotifs = new TextField();
        board = new GridPane();
        btnStartGame = new Button("Start Game");
    }

    private void layoutNodes() {

        for (int i = 0; i < 4; i++) {
            this.getColumnConstraints().add(new ColumnConstraints(200));
            this.getRowConstraints().add(new RowConstraints(200));
        }
        this.setGridLinesVisible(false);
        board.setGridLinesVisible(true);

        for (int i = 0; i < 10; i++) {
            board.getColumnConstraints().add(new ColumnConstraints(60));
            board.getRowConstraints().add(new RowConstraints(60));
        }
        GridPane.setConstraints(board, 0, 1, 4, 4);
        GridPane.setConstraints(btnStartTurn, 0, 0);
        GridPane.setConstraints(btnEndTurn, 3, 3);
        GridPane.setConstraints(btnStartTurn,0,0);
        this.getChildren().addAll(board, btnStartTurn, btnEndTurn, tfNotifs,btnStartGame);
    }
// implementatie van de nodige
// package-private Getters

    public Button getBtnStartGame() {
        return btnStartGame;
    }

    public void setPosition(String n , int x, int y){
        Label z = new Label(n);
        board.add(z,x,y);
    }
}