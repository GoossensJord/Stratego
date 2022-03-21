package be.kdg.stratego.leaderboard;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LeaderboardView extends GridPane {
    Button backToMainMenuButton;
    Button resetHighScoresButton;
    Background background;
    TextArea highScores;
    ImageView highScoreImage;

    public LeaderboardView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes(){
        backToMainMenuButton = new Button("Exit");
        resetHighScoresButton = new Button("Reset");
        highScores = new TextArea(getHighScoreString());
        highScoreImage = new ImageView(new Image("nodeFrame.png"));
        Image homeBackgroundImage = new Image("/homeScreenTwo.png");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(homeBackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        background = new Background(backgroundImage);

    }
    private void layoutNodes(){
        for (int i = 0; i < 20; i++) {
            this.getRowConstraints().add(new RowConstraints(40));
        }
        for (int i = 0; i < 38; i++) {
            this.getColumnConstraints().add(new ColumnConstraints(40));
        }
        this.getStylesheets().add("style.css");
        this.setBackground(background);
        GridPane.setConstraints(highScores,15,5,10,10);
        GridPane.setConstraints(backToMainMenuButton, 24, 18, 10, 2);
        GridPane.setConstraints(resetHighScoresButton, 2, 18, 10, 2);
        this.getChildren().addAll(highScores,backToMainMenuButton,resetHighScoresButton);
        backToMainMenuButton.setId("homeScreenButton");
        resetHighScoresButton.setId("homeScreenButton");
        highScores.setId("textArea");
    }

    private String getHighScoreString() {
        StringBuilder highScoreString = new StringBuilder();
        try {
            File highScores = new File("highScores.txt");
            Scanner sc = new Scanner(highScores);

            while (sc.hasNextLine()) {
                highScoreString.append(sc.nextLine());
                highScoreString.append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found error");
            e.printStackTrace();
        }
        return highScoreString.toString();
    }
    public TextArea getHighScores() {
        return highScores;
    }

    public Button getBackToMainMenuButton() {
        return backToMainMenuButton;
    }

    public Button getResetHighScoresButton() {
        return resetHighScoresButton;
    }
}
