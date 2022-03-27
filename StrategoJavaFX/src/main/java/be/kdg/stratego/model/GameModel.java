package be.kdg.stratego.model;

import be.kdg.stratego.model.board.Board;
import be.kdg.stratego.model.board.BoardMaker;
import be.kdg.stratego.model.board.Square;
import be.kdg.stratego.model.pieces.*;
import be.kdg.stratego.model.player.Player;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Class responsible for holding all model method, does a lot of back-end calls, used by presenters.
 */
public class GameModel {
    private GameSaveState GSS;
    private final BoardMaker boardMaker;
    private final Board board;
    private final Player pl;
    private final Player pl2;

    /**
     * Constructor for GameModel. Initialises a board and a boardmaker, two players and a GameSaveState.
     */
    public GameModel() {
        boardMaker = new BoardMaker();
        board = new Board(boardMaker);
        pl = new Player(0, "Jord", board);
        pl2 = new Player(1, "Michiel", board);
        //  GSS = new GameSaveState(pl,board.getBord());
    }

    /**
     * fills the board with a list of predetermined pieces that are shuffled for each player
     */
    public void fillRandomly() {
        boardMaker.makePieces(pl, pl2);
        boardMaker.shufflePieces();
        boardMaker.placePieces();
    }

    /**
     * Returns a list of int arrays which include the possible moves for this piece, depending on wether or not the piece is a scout it will call a different method.
     */
    public List<int[]> getMoves(Piece p) {
        if (p == null) return null;
        if (p instanceof Scout) return ((Scout) p).allMoves();
        else if (p.availableSquares(p.getX(), p.getY()) != null) {
            return p.availableSquares(p.getX(), p.getY());
        }
        return null;
    }

    /**
     * Returns a list of int arrays which include the possible attacks for this piece, depending on wether or not the piece is a scout it will call a different method.
     */

    public List<String> getAllPiecesString() {
        return boardMaker.getListView();
    }

    /**
     * Returns the piece list of one player depending on the ID given
     */
    public List<Piece> getPlayerPiecesById(int id) {
        return getPlayerByID(id).getPiecesList();
    }

    /**
     * A method for determining the attack, depending on wether the piece is a scout or not.
     */
    public List<int[]> getAttacks(Piece p) {
        if (p == null) return null;
        if (p instanceof Scout) return ((Scout) p).allAttacks();
        if (p.getAttacks(p.getX(), p.getY()) != null) {
            return p.getAttacks(p.getX(), p.getY());
        }
        return null;
    }

    /**
     * Takes two pieces in its parameter to decide the outcome of their battle.
     *
     * @return Returns the winning piece
     */
    public boolean isMatchupWinner(Piece p, Piece p1) {
        if (p.getRank().equals(Rank.MINER) && p1.getRank().equals(Rank.BOMB)) return true;
        if (p.getRank().equals(Rank.SPY) && p1.getRank().equals(Rank.MARSHAL)) return true;
        if (p.getRankPower() == p1.getRankPower()) return true;
        return p.getRankPower() > p1.getRankPower();

    }

    /**
     * Boolean which returns true if piece given in parameter is Flag
     */
    public boolean isGameWin(Piece p) {
        return p instanceof Flag;
    }

    /**
     * Method which uses the x and y from the parameters to get a piece from the backend board.
     */
    public Piece choosePiece(int x, int y) {
        return board.getBoardMaker()[x][y].getPiece();
    }

    public Square[][] getBoard() {
        return boardMaker.getSquaresBoard();
    }

    /**
     * Method that calls the backend board method to make the move
     */
    public void makeChosenMove(int[] move, Piece p) {
        board.makeMove(move, p);
    }

    /**
     * Method that calls the backend board method to make the attack. Uses the method gameWin to determine if the defeated piece was a flag.
     */
    public boolean makeChosenAttack(int[] attack, Piece p) {
        boolean win = isGameWin(boardMaker.getSquaresBoard()[attack[0]][attack[1]].getPiece());
        board.makeAttack(attack, p);
        return win;
    }

    /**
     * Creates a piece by using a String name
     */
    public void makePieceByString(String pieceString, int x, int y, Player pl) {
        Piece p = pieceMakerManualAssigning(pieceString, x, y, pl);
        boardMaker.manualPieceSelection(p);
    }

    /**
     * Creates a new piece when manually placing them on the board to create a layout.
     * @param str The string name of the piece.
     * @param x The X coordinate on the board.
     * @param y The Y Coordinate on the board.
     * @param pl The player that gets assigned to the piece.
     * @return Returns the made piece.
     */
    private Piece pieceMakerManualAssigning(String str, int x, int y, Player pl) {
        Piece p = null;
        switch (str) {
            case "Bomb":
                p = new Bomb(Rank.BOMB, pl, x, y);
                break;
            case "Flag":
                p = new Flag(Rank.FLAG, pl, x, y);
                break;
            case "Marshal":
                p = new Marshal(Rank.MARSHAL, pl, x, y);
                break;
            case "Miner":
                p = new Miner(Rank.MINER, pl, x, y);
                break;
            case "Scout":
                p = new Scout(Rank.SCOUT, pl, x, y);
                break;
            case "Spy":
                p = new Scout(Rank.SPY, pl, x, y);
                break;
            case "General":
                p = new Piece(Rank.GENERAL, pl, x, y);
                break;
            case "Colonel":
                p = new Piece(Rank.COLONEL, pl, x, y);
                break;
            case "Major":
                p = new Piece(Rank.MAJOR, pl, x, y);
                break;
            case "Luitenant":
                p = new Piece(Rank.LUITENANT, pl, x, y);
                break;
            case "Captain":
                p = new Piece(Rank.CAPTAIN, pl, x, y);
                break;
            case "Sergeant":
                p = new Piece(Rank.SERGEANT, pl, x, y);
                break;
        }
        return p;
    }




    /**
     * calls the board method to clear the board.
     */
    public void clearBoardById(int id) {
        board.clearSetupSideById(id);
    }
    public void clearBoard(){
        board.clearBoard();
    }

    /**
     * Returns the coordinates piece list of one player depending on the ID given
     */
    public List<int[]> piecesOnePlayer(int id) {
        return boardMaker.getPiecesOnePlayer(id);
    }

    /**
     * Returns the player by ID.
     *
     * @param id Each player has an ID, by asking for it in the parameter we can get the player itself.
     * @return returns the appropriate player.
     */
    public Player getPlayerByID(int id) {
        if (pl.getId() == id) return pl;
        else return pl2;
    }

    /**
     * Checks if the position for manual placement of a piece in create layout is valid depending on the player ID
     * @return Returns true if position is possible.
     */
    public boolean positionCheckerManualAssigningPiece(int x, int y) {
        //noinspection SuspiciousNameCombination
        if (board.spaceAvailable(x, y)) {
            if (GameSaveState.getPlayerTurn().getId() == 0 && x <= 3) return true;
            else return GameSaveState.getPlayerTurn().getId() == 1 && x >= 6;
        }

        return false;
    }

    /**
     * A method that gets the current score by adding up all the remaining pieces power of that player and writes it to a file.
     */
    public void setScore(Player player) {
        try {

            FileWriter fw = new FileWriter("highScores.txt", true);
            fw.write(boardMaker.getScore(player.getId()) + " - " + player.getName() + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method for loading in a save
     */
    public void loadSave() {

        HashMap<String, List<int[]>> setupHashMap = GameSaveState.getSetupHashMap();
        for (String s : setupHashMap.keySet()) {
            for (int i = 0; i < setupHashMap.get(s).size(); i++) {
                makePieceByString(s, setupHashMap.get(s).get(i)[0], setupHashMap.get(s).get(i)[1],GameSaveState.getPlayerTurn());
            }
        }
    }

    /**
     * A method to set the player names in the gamemode select screen
     */
    public void setPlayerName(String name, String nameTwo) {
        pl.setName(name);
        pl2.setName(nameTwo);
    }

    /**
     * Method that loads in the save from a text file.
     */
    public void loadSaveGame() {
        List<String> strList = GameSaveState.loadSaveGame();

        for (String s : strList) {
            if (s.contains("|")) {
                try {
                    int playerId = Integer.parseInt(s.substring(0, 1));
                    GameSaveState.setPlayerTurn(getPlayerByID(playerId));
                    GameSaveState.setIdlePlayer(getPlayerByID(Math.abs(playerId - 1)));
                    break;
                } catch (NumberFormatException ex){
                    System.out.println("Fout in loadsavegame string");
                }


            }
            try {
                String[] split = s.split("-");
                String name = split[0];
                String pos = split[1];
                int xval = Integer.parseInt(pos.substring(0, 1));
                int yval = Integer.parseInt(pos.substring(2, 3));
                Player p = getPlayerByID(Integer.parseInt(split[2].substring(0, split[2].length() - 1)));
                makePieceByString(name, xval, yval, p);
            }
            catch (ArrayIndexOutOfBoundsException ae){
                System.out.println("Fout in loadsavegame array");
            }
        }
    }

    /**
     * Boolean to determine wether or not you can start the game with your created layout, it checks the remaining pieces to be placed by each player
     * @return returns true if both are bigger or equal to one
     */
    public boolean startGame() {
        return getPlayerPiecesById(1).size() >= 1 && getPlayerPiecesById(0).size() >= 1;
    }
}