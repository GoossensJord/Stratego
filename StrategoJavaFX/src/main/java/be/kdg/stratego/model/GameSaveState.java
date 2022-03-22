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

public class GameSaveState {
    private static Square[][] boardState;
    private static Player playerTurn;
    private static Player idlePlayer;
    private static Path savePath;
    private static List<String> setupStringList = new ArrayList<>();
    private static HashMap<String, List<int[]>> setupHashMap = new HashMap<>();

    public GameSaveState() {

    }


    public static void saveSetup(List<Piece> pieceList, String name)

            throws IOException {
        filechecker();
        for (Piece p : pieceList) {
            if (setupHashMap.containsKey(p.getRank().getName()))
                setupHashMap.get(p.getRank().getName()).add(new int[]{p.getX(), p.getY()});
            else {
                setupHashMap.put(p.getRank().getName(), new ArrayList<>());
                setupHashMap.get(p.getRank().getName()).add(new int[]{p.getX(), p.getY()});
            }
        }
        writeToFile(name);
        //loadSetupStringList();
    }
    private static void filechecker(){
        if (playerTurn.getId()==0) savePath = Paths.get("Save.txt");
        else savePath = Paths.get("SaveP2.txt");
    }
    private static void writeToFile(String name) throws IOException {
        filechecker();
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
     * gets all the names from the save file
     */
    public static void loadSetupStringList() {
        setupStringList.clear();
        filechecker();
        Pattern pat = Pattern.compile("[A-Za-z]:\\{", Pattern.CASE_INSENSITIVE);
        boolean matchingregex = true;
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

    public static void loadSave(String save) {
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
        for (int i = 0; i < setupTitle.length; i++) {
            if (setupTitle[i].contains(save)) {
                String toLoad = setupTitle[i];
                String setupString = toLoad.split(":\\{")[1];
                String[] piecesStr = setupString.split(",");
                System.out.println(setupString);

                for (int j = 0; j < piecesStr.length; j++) {
                    String pieceStr = piecesStr[j].split(":-")[0];
                    String[] posarr = piecesStr[j].split(":")[1].split("-");
                    for (int k = 0; k < posarr.length; k++) {

                        String[] pos = posarr[k].split("\\.");

                        if (pos.length == 2) {
                            int[] intarr = new int[]{Integer.valueOf(pos[0]), Integer.valueOf(pos[1])};
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

    public static HashMap<String, List<int[]>> getSetupHashMap() {

        return setupHashMap;
    }

    /**
     * Order of save = setup name , repeat(Piece name (rank), piece X position, piece Y position)
     */
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

    public static void switchTurn() {
        playerTurn = idlePlayer;
        idlePlayer = playerTurn;

    }


    public static void saveGame() {

    }

    public static void loadGame() {
    }
    //todo saven
    //todo laden

}