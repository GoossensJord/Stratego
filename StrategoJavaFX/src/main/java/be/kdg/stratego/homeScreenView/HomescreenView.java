package be.kdg.stratego.homeScreenView;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class HomescreenView extends GridPane {
    Button playClassic;
    Button playRandomFill;
    Button endGame;
    Background background;

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
        Image homeBackgroundImage = new Image("/homeScreen.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(100,100,true,true,true,false);
        BackgroundImage backgroundImage = new BackgroundImage(homeBackgroundImage,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,backgroundSize);
        background = new Background(backgroundImage);
    }

    /**
     * Method for laying out the nodes
     */

    private void layoutNodes(){
        for (int i = 0; i < 10; i++) {
            this.getRowConstraints().add(new RowConstraints(80));
        }
        for (int i = 0; i < 19; i++) {
            this.getColumnConstraints().add(new ColumnConstraints(80));
        }
        //this.setGridLinesVisible(true);

        GridPane.setConstraints(playClassic,1,9,1,1);
        GridPane.setConstraints(playRandomFill,3,9,2,1);
        GridPane.setConstraints(endGame,5,9,1,1);

        this.setBackground(background);
        this.getChildren().addAll(playClassic, playRandomFill, endGame);

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
