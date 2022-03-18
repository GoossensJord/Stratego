package be.kdg.stratego.model;

import be.kdg.stratego.model.board.Square;
import be.kdg.stratego.model.pieces.Piece;
import be.kdg.stratego.model.player.Player;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class GameSaveState {
    private static Square[][] boardState;
    private static Player playerTurn;
    private static Player idlePlayer;
    private static final Path savePath = Paths.get("Save.txt");

    public GameSaveState() {
    }

    public static void saveSetup(List<Piece> pieceList, String name)

            throws IOException {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(savePath.toFile())));
        for (Piece p : pieceList) {
            pw.format("%s ,%d,%d %n", p.getRank(), p.getX(), p.getY());
        }
    }
    /**
     * Order of save = setup name , repeat(Piece name (rank), piece X position, piece Y position)
     * */
        /*

    private static String pieceSetupStringMaker(List<Piece> pieceList, String nameSetup) {
        StringBuilder sb = new StringBuilder("{ setupName:"+ nameSetup + ",");
        for (int i = 0; i < pieceList.size(); i++) {
            Piece pToSave = pieceList.get(i);
            sb.append(pToSave.getRank() + "," );
            sb.append(pToSave.getX() + ",");
            sb.append(pToSave.getY() + ",\n");
        }
        sb.append("}");
        return sb.toString();
    }*/

    public static void setBoardState(Square[][] bState) {
        boardState = bState;
    }

    public static void setPlayerTurn(Player p) {
        setIdlePlayer(playerTurn);
        playerTurn = p;
    }

    private static void setIdlePlayer(Player p) {
        idlePlayer = p;
    }

    public static Player getPlayerTurn() {
        return playerTurn;
    }

    public static int switchTurnByid() {
        if (playerTurn.getId() == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void loadSetup() {

    }

    public static void saveGame() {

    }

    public static void loadGame() {
    }
    //todo saven
    //todo laden

}