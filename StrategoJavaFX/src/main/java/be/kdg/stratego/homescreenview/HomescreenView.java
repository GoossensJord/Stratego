package be.kdg.stratego.homescreenview;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * Class responsible for layout of homescreen
 */
public class HomescreenView extends GridPane {
    Button layoutPiecesButton;
    Button playButton;
    Button endGameButton;
    Background background;
    ImageView title;
    ImageView titleCreators;
    Button leaderBoardButton;


    /**
     * Constructor which initialises and does the layout for the nodes
     */
    public HomescreenView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    /**
     * Method to initialise the nodes.
     */
    private void initialiseNodes() {
        layoutPiecesButton = new Button("Make a layout");
        playButton = new Button("Play");
        endGameButton = new Button("Quit");
        leaderBoardButton = new Button("Leaderboard");

        Image homeBackgroundImage = new Image("/homeScreenTwo.png");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(homeBackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        background = new Background(backgroundImage);
        Image titleImage = new Image("/TitleName.jpg");
        title = new ImageView(titleImage);
        Image titleCreatorImage = new Image("titleCreators.jpg");
        titleCreators = new ImageView(titleCreatorImage);
    }

    /**
     * Method for laying out the nodes
     */

    private void layoutNodes() {
        for (int i = 0; i < 20; i++) {
            this.getRowConstraints().add(new RowConstraints(40));
        }
        for (int i = 0; i < 38; i++) {
            this.getColumnConstraints().add(new ColumnConstraints(40));
        }
        GridPane.setConstraints(leaderBoardButton, 16, 18, 10, 2);
        GridPane.setConstraints(playButton, 2, 18, 10, 2);
        GridPane.setConstraints(layoutPiecesButton, 8, 18, 10, 2);
        GridPane.setConstraints(endGameButton, 24, 18, 10, 2);
        GridPane.setConstraints(title, 1, 1, 1, 1);
        GridPane.setConstraints(titleCreators, 1, 5, 1, 1);
        this.setGridLinesVisible(false);


        this.setBackground(background);
        this.getChildren().addAll(layoutPiecesButton, playButton, endGameButton, title, titleCreators, leaderBoardButton);
        this.getStylesheets().add("style.css");
        layoutPiecesButton.setId("homeScreenButton");
        playButton.setId("homeScreenButton");
        endGameButton.setId("homeScreenButton");
        leaderBoardButton.setId("homeScreenButton");

    }

    Button getLayoutPiecesButton() {
        return layoutPiecesButton;
    }

    Button getPlayButton() {
        return playButton;
    }

    Button getEndGameButton() {
        return endGameButton;
    }

    Button getLeaderBoardButton() {
        return leaderBoardButton;
    }
}
