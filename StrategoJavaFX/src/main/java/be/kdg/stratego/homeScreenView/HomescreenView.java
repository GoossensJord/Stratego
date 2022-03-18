package be.kdg.stratego.homeScreenView;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class HomescreenView extends GridPane {
    Button playClassic;
    Button playRandomFill;
    Button endGame;
    Background background;
    ImageView title;
    ImageView titleCreators;


    /**
     * Constructor which initialises and does the layout for the nodes
     */
    public HomescreenView(){
        this.initialiseNodes();
        this.layoutNodes();
    }
    /**
     * Method to initialise the nodes.
     */
    private void initialiseNodes(){
        playClassic = new Button("Play Classic");
        playRandomFill = new Button("Play RandomFill");
        endGame = new Button("Quit");
        Image homeBackgroundImage = new Image("/homeScreenTwo.png");
        BackgroundSize backgroundSize = new BackgroundSize(100,100,true,true,true,true);
        BackgroundImage backgroundImage = new BackgroundImage(homeBackgroundImage,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,backgroundSize);
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
        //this.setGridLinesVisible(true);

        GridPane.setConstraints(playClassic, 2, 18, 10, 2);
        GridPane.setConstraints(playRandomFill, 10, 18, 10, 2);
        GridPane.setConstraints(endGame, 18, 18, 10, 2);
        GridPane.setConstraints(title,1,1,1,1);
        GridPane.setConstraints(titleCreators,1,5,1,1);
        this.setGridLinesVisible(false);


        this.setBackground(background);
        this.getChildren().addAll(playClassic, playRandomFill, endGame, title,titleCreators);
        this.getStylesheets().add("style.css");
        playClassic.setId("homeScreenButton");
        playRandomFill.setId("homeScreenButton");
        endGame.setId("homeScreenButton");
    }

    public Button getPlayClassic() {
        return playClassic;
    }

    public Button getPlayRandomFill() {
        return playRandomFill;
    }

    public Button getEndGame() {
        return endGame;
    }


}
