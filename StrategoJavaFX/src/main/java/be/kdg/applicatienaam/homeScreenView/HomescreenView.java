package be.kdg.applicatienaam.homeScreenView;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class HomescreenView extends GridPane {
    TextField strategoName;
    Button playClassic;
    Button playRandomFill;
    Button endGame;
    Background background;

    public HomescreenView(){
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes(){
        strategoName = new TextField("Stratego");
        playClassic = new Button("Play Classic");
        playRandomFill = new Button("Play RandomFill");
        endGame = new Button("Quit");
        Image homeBackgroundImage = new Image("/homeScreen.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(100,100,true,true,true,false);
        BackgroundImage backgroundImage = new BackgroundImage(homeBackgroundImage,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,backgroundSize);
        background = new Background(backgroundImage);
    }
    private void layoutNodes(){
        for (int i = 0; i < 10; i++) {
            this.getRowConstraints().add(new RowConstraints(80));
        }
        for (int i = 0; i < 19; i++) {
            this.getColumnConstraints().add(new ColumnConstraints(80));
        }
        this.setGridLinesVisible(false);

        GridPane.setConstraints(strategoName,1,0,3,1);
        GridPane.setConstraints(playClassic,9,1,2,1);
        GridPane.setConstraints(playRandomFill,9,3,2,1);
        GridPane.setConstraints(endGame,9,5,5,5);

        this.setBackground(background);
        this.getChildren().addAll(strategoName, playClassic, playRandomFill, endGame);


    }


}
