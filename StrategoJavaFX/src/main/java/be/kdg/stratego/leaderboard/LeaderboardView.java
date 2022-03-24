package be.kdg.stratego.leaderboard;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * View responsible for leaderboard
 */
public class LeaderboardView extends GridPane {
    Button backToMainMenuButton;
    Button resetHighScoresButton;
    Background background;
    TextArea highScores;
    TextArea leaderBoardTitle;
    ImageView highScoreImage;

    /**
     * Constructor which calls the initialise nodes and layout nodes method as those are needed immediately.
     */
    public LeaderboardView() {
        this.initialiseNodes();
        this.layoutNodes();
    }
    /**
     * Initialises the nodes on this view
     */
    private void initialiseNodes(){
        backToMainMenuButton = new Button("Exit");
        resetHighScoresButton = new Button("Reset");
        highScores = new TextArea(getHighScoreString() + "\n");
        highScoreImage = new ImageView(new Image("nodeFrame.png"));
        leaderBoardTitle = new TextArea("Leaderboard");
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
        GridPane.setConstraints(leaderBoardTitle,15,1,10,2);
        GridPane.setConstraints(highScores,15,2,25,25);
        GridPane.setConstraints(backToMainMenuButton, 24, 18, 10, 2);
        GridPane.setConstraints(resetHighScoresButton, 2, 18, 10, 2);
        this.getChildren().addAll(highScores,backToMainMenuButton,resetHighScoresButton,leaderBoardTitle);
        backToMainMenuButton.setId("homeScreenButton");
        resetHighScoresButton.setId("homeScreenButton");
        highScores.setId("leaderboardTextArea");
        highScores.setEditable(false);
        leaderBoardTitle.setId("leaderboardTextArea");
        leaderBoardTitle.setEditable(false);
    }

    /**
     * Method that gives back a String to put in the highscore text area
     * @return
     */
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
