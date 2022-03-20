package be.kdg.stratego.selectgamemode;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class SelectGameModeView extends GridPane {
    Button fillBoardRandomlyButton;
    Button loadInPieceLayoutButton;
    Button loadSavedGameButton;
    Button returnToMenuButton;
    Background background;
    ImageView randomFillButtonFrame;
    ImageView loadPiecesFrame;
    ImageView loadSaveGameFrame;
    TextField askPlayerNameTextField;
    TextField playerNameOne;
    TextField playerNameTwo;
    Button setNamesButton;


    public SelectGameModeView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        fillBoardRandomlyButton = new Button("  Random  ");
        loadInPieceLayoutButton = new Button("Load Setup");
        loadSavedGameButton = new Button(" Load Save ");
        returnToMenuButton = new Button("Quit");
        Image selechtGameModeBackgroundImage = new Image("/homeScreenTwo.png");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(selechtGameModeBackgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        background = new Background(backgroundImage);
        randomFillButtonFrame = new ImageView(new Image("nodeFrame.png"));
        loadPiecesFrame = new ImageView(new Image("nodeFrame.png"));
        loadSaveGameFrame = new ImageView(new Image("nodeFrame.png"));
        askPlayerNameTextField = new TextField("Enter your names");
        askPlayerNameTextField.setEditable(false);
        playerNameOne = new TextField("Enter name player one");
        playerNameTwo = new TextField("Enter name player two");
        setNamesButton = new Button("Confirm names");
    }

    private void layoutNodes() {
        for (int i = 0; i < 48; i++) {
            this.getRowConstraints().add(new RowConstraints(20));
        }
        for (int i = 0; i < 76; i++) {
            this.getColumnConstraints().add(new ColumnConstraints(20));
        }
        GridPane.setConstraints(setNamesButton,2,8,10,2);
        GridPane.setConstraints(playerNameTwo,2,6,10,2);
        GridPane.setConstraints(playerNameOne, 2, 4, 10, 2);
        GridPane.setConstraints(askPlayerNameTextField, 2, 2, 10, 2);
        GridPane.setConstraints(randomFillButtonFrame, 2, 20, 20, 4);
        GridPane.setConstraints(loadPiecesFrame, 26, 20, 20, 4);
        GridPane.setConstraints(loadSaveGameFrame, 52, 20, 20, 4);
        GridPane.setConstraints(fillBoardRandomlyButton, 6, 10, 20, 20);
        GridPane.setConstraints(loadInPieceLayoutButton, 30, 18, 20, 4);
        GridPane.setConstraints(loadSavedGameButton, 56, 18, 20, 4);
        GridPane.setConstraints(returnToMenuButton, 60, 36, 20, 4);

        this.getChildren().addAll(fillBoardRandomlyButton, loadInPieceLayoutButton, loadSavedGameButton, returnToMenuButton, randomFillButtonFrame, loadPiecesFrame, loadSaveGameFrame, askPlayerNameTextField,playerNameOne,playerNameTwo,setNamesButton);
        fillBoardRandomlyButton.setId("selectGameModeButton");
        fillBoardRandomlyButton.setDisable(true);
        loadInPieceLayoutButton.setId("selectGameModeButton");
        loadInPieceLayoutButton.setDisable(true);
        loadSavedGameButton.setId("selectGameModeButton");
        loadSavedGameButton.setDisable(true);
        returnToMenuButton.setId("selectGameModeButton");

        this.setBackground(background);
        this.getStylesheets().add("style.css");
    }

    public Button getFillBoardRandomlyButton() {
        return fillBoardRandomlyButton;
    }

    public Button getLoadInPieceLayoutButton() {
        return loadInPieceLayoutButton;
    }

    public Button getLoadSavedGameButton() {
        return loadSavedGameButton;
    }

    public Button getReturnToMenuButton() {
        return returnToMenuButton;
    }

    public Button getSetNamesButton() {
        return setNamesButton;
    }
}
