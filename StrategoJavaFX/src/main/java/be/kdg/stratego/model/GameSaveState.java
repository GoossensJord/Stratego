package be.kdg.stratego.model;

import be.kdg.stratego.model.board.Square;
import be.kdg.stratego.model.pieces.Piece;
import be.kdg.stratego.model.player.Player;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class responsible for the current state of the game and it's saves.
 */
public class GameSaveState {

    private static Player playerTurn;
    private static Player idlePlayer;
    private static Path savePath;
    private static List<String> setupStringList = new ArrayList<>();
    private static HashMap<String, List<int[]>> setupHashMap = new HashMap<>();
    private static String saveGameString;

    public GameSaveState() {

    }

    /**
     * A method to save the setup you can create in arrange pieces
     * @param pieceList Uses the List of pieces set by the user.
     * @param name The name the player wishes to give to the save.
     */
    public static void saveSetup(List<Piece> pieceList, String name)

            throws IOException {
        setupHashMap = new HashMap<>();
        filechecker();
        for (Piece p : pieceList) {
            if (setupHashMap.containsKey(p.getRank().getName()))
                setupHashMap.get(p.getRank().getName()).add(new int[]{p.getX(), p.getY()});
            else {
                setupHashMap.put(p.getRank().getName(), new ArrayList<>());
                setupHashMap.get(p.getRank().getName()).add(new int[]{p.getX(), p.getY()});
            }
        }
        writeSavedSetupsToFile(name);
        //loadSetupStringList()
    }

    /**
     * Checks the correct file path determining on which player's turn it is.
     */
    private static void filechecker() {


        if (playerTurn.getId() == 0) savePath = Paths.get("Save.txt");
        else savePath = Paths.get("SaveP2.txt");


    }

    /**
     * Method to write away the saved setups to a file
     * @param name Name of the file to write to.
     */
    private static void writeSavedSetupsToFile(String name) throws IOException {

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(savePath.toFile(), true)));
        pw.format("%s:{%n", name);
        for (String s : setupHashMap.keySet()
        ) {
            pw.format("%s:", s);
            for (int i = 0; i < setupHashMap.get(s).size(); i++) {
                pw.format("-%d.%d", setupHashMap.get(s).get(i)[0], setupHashMap.get(s).get(i)[1]);
            }
            pw.format(",%n");
        }
        pw.format("%s %n", "};");

        pw.close();

    }

    /**
     * A method that saves the game to a file.
     * @param board The save needs the exact board layout to correctly save.
     */
    public static void writeSavedGameToFile(Square[][] board) throws IOException {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("saveGame.txt")));
        StringBuilder sb = new StringBuilder();
        for (Square[] squares : board) {
            for (Square square : squares) {
                if (square != null) {
                    if (square.getIsOccupied()) {
                        sb.append(square.getPiece().getRank().getName()).append("-").append(square.getPiece().getX()).append('.').append(square.getPiece().getY()).append("-").append(square.getPiece().getPlayer().getId());
                        sb.append(":");
                    }
                } else continue;
            }
        }
        for (String s: sb.toString().split(":")) {
            pw.format("%s %n",s);
        }
        pw.format("|%s",getPlayerTurn().getId());
        pw.close();
    }


    /**
     * Loads the saved game out of the file
     * @return returns a list of strings containing all the save information.
     */
    public static List<String> loadSaveGame(){
        List<String> out = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("saveGame.txt"));
            while(sc.hasNext()){

                out.add(sc.nextLine());

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return out;
    }
    /**
     * Goes through the file with a regex adding appropriate parts of the text file to the setupstringlist.
     */
    public static void loadSetupStringList() {
        setupStringList = new ArrayList<>();
        filechecker();
        Pattern pat = Pattern.compile("[A-Za-z]:\\{", Pattern.CASE_INSENSITIVE);
        boolean matchingregex;
        try {
            Scanner sc = new Scanner(new File(String.valueOf(savePath)));
            while (sc.hasNext()) {
                String pieceStr = sc.nextLine();
                Matcher m = pat.matcher(pieceStr);
                matchingregex = m.find();
                if (matchingregex) setupStringList.add(pieceStr.substring(0, pieceStr.length() - 2));
            }

        } catch (FileNotFoundException f) {
            f.getMessage();
        }

    }

    /**
     * Loads the saved game from the file
     */
    public static void loadSave(String save, boolean game) {
        filechecker();
        StringBuilder sb = new StringBuilder();
        setupHashMap.clear();

        try {
            Scanner sc = new Scanner(new File(String.valueOf(savePath)));
            while (sc.hasNext()) {
                sb.append(sc.nextLine());
            }

        } catch (FileNotFoundException f) {
            f.getMessage();
        }
        System.out.println(sb);

        String[] setupTitle = sb.toString().split("};");
        for (String s : setupTitle) {
            if (s.contains(save)) {
                String setupString = s.split(":\\{")[1];
                String[] piecesStr = setupString.split(",");
                System.out.println(setupString);

                for (String value : piecesStr) {
                    String pieceStr = value.split(":-")[0];
                    String[] posarr = value.split(":")[1].split("-");
                    for (String item : posarr) {

                        String[] pos = item.split("\\.");

                        if (pos.length == 2) {
                            int[] intarr = new int[]{Integer.parseInt(pos[0]), Integer.parseInt(pos[1])};
                            if (setupHashMap.containsKey(pieceStr))
                                setupHashMap.get(pieceStr).add(intarr);
                            else {
                                setupHashMap.put(pieceStr, new ArrayList<>());
                                setupHashMap.get(pieceStr).add(intarr);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Method to change the player turn
     */
    public static void switchTurn() {
        Player temp = playerTurn;
        playerTurn = idlePlayer;
        idlePlayer = temp;

    }

    /**
     * Method that clears the setup hashmap
     */
    public static void clearHashMap() {
        setupHashMap.clear();
    }


    public static HashMap<String, List<int[]>> getSetupHashMap() {
        return setupHashMap;
    }

    public static void setBoardState(Square[][] bState) {
    }

    public static void setPlayerTurn(Player p) {
        setIdlePlayer(playerTurn);
        playerTurn = p;
    }

    public static void setIdlePlayer(Player p) {
        idlePlayer = p;
    }

    public static Player getPlayerTurn() {
        return playerTurn;
    }

    public static List<String> getSetupStringList() {

        loadSetupStringList();
        return setupStringList;
    }


    public static Player getIdlePlayer() {
        return idlePlayer;
    }
}