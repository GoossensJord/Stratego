package be.kdg.stratego.model;

import be.kdg.stratego.model.board.Board;
import be.kdg.stratego.model.board.BoardMaker;
import be.kdg.stratego.model.board.Square;
import be.kdg.stratego.model.pieces.Flag;
import be.kdg.stratego.model.pieces.Piece;
import be.kdg.stratego.model.pieces.Rank;
import be.kdg.stratego.model.pieces.Scout;
import be.kdg.stratego.model.player.Player;
import be.kdg.stratego.model.GameSaveState;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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

    public List<Piece> getPlayerPiecesById(int id) {
        return getPlayerByID(id).getPiecesList();
    }

    public List<int[]> getAttacks(Piece p) {
        if (p == null) return null;
        if (p instanceof Scout) return ((Scout) p).allAttacks();
        if (p.getAttacks(p.getX(), p.getY()) != null) {
            return p.getAttacks(p.getX(), p.getY());
        }
        return null;
    }

    /**
     * Takes two pieces in it's parameter to decide the outcome of their battle.
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
    public boolean gameWin(Piece p) {
        return p instanceof Flag;
    }

    /**
     * Method which uses the x and y from the parameters to get a piece from the backend board.
     */
    public Piece choosePiece(int x, int y) {
        return board.getBord()[x][y].getPiece();
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
        boolean win = gameWin(boardMaker.getSquaresBoard()[attack[0]][attack[1]].getPiece());
        board.makeAttack(attack, p);
        return win;
    }

    public void makePieceByString(String pieceString, int x, int y) {
        for (Rank r : Rank.values()) {
            if (r.getName().equals(pieceString)) {
                Piece p = new Piece(r, pl, x, y);
                boardMaker.manualListChecker(p);
                //System.out.println(boardMaker.manualPieceSelection(p));
                break;
            }
        }
    }

    public List<int[]> piecesOnePlayer(int id) {
        return boardMaker.getPiecesOnePlayer(id);
    }

    public Player getPlayerByID(int id) {
        if (pl.getId() == id) return pl;
        else return pl2;
    }

    public boolean positionChecker(int x, int y) {
        System.out.println(GameSaveState.getPlayerTurn().getId());
        if (board.spaceAvailable(x, y)) {
            if (GameSaveState.getPlayerTurn().getId() == 0 && x <= 3) return true;
            else if (GameSaveState.getPlayerTurn().getId() == 1 && x >= 6) return true;
        }
        System.out.print("magnie");
        return false;
    }

    public void setPlayerName(String name, String nameTwo) {
        pl.setName(name);
        pl2.setName(nameTwo);
    }
    public void clearBoardOfPieces(){
        boardMaker.clearBoard();
    }

    public void setScore(Player player){
        try{

            FileWriter fw = new FileWriter("highScores.txt", true);
            fw.write(player.getName() + " - "+ boardMaker.getScore(player.getId())+"\n");
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}