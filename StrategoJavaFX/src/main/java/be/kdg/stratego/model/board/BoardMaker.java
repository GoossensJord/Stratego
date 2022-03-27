package be.kdg.stratego.model.board;

import be.kdg.stratego.model.GameSaveState;
import be.kdg.stratego.model.pieces.Piece;
import be.kdg.stratego.model.pieces.Rank;
import be.kdg.stratego.model.player.Player;
import be.kdg.stratego.model.player.PlayerData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class responsible for making the board
 */
public class BoardMaker {

    private final int SQUARE_ARRAY_WIDTH = 10;
    private final int SQUARE_ARRAY_HEIGHT = 10;

    protected Square[][] squaresBoard;

    PlayerData playerData = new PlayerData();

    List<Piece> piecesPlayerOne;
    List<Piece> piecesPlayerTwo;

    /**
     * Constructor that initialises the square board and the lists of player pieces
     */
    public BoardMaker() {
        squaresBoard = new Square[SQUARE_ARRAY_HEIGHT + 1][SQUARE_ARRAY_WIDTH + 1];
        piecesPlayerOne = new ArrayList<>();
        piecesPlayerTwo = new ArrayList<>();
    }

    /**
     * fills the lists of pieces per player
     */
    public void makePieces(Player playerOne, Player playerTwo) {
        piecesPlayerOne = playerData.createPieceList(playerOne);
        piecesPlayerTwo = playerData.createPieceList(playerTwo);
    }

    /**
     * A method that makes a list of all the pieces one player needs to fill in their board, used later for manual placement
     * @return A list of strings containing all necessary pieces to place on the board.
     */
    public List<String> getListView() {
        List<String> out = new ArrayList<>();
        for (Rank r : Rank.values()) {
            for (int i = 0; i < r.getAmount(); i++) {
                out.add(r.getName());
            }
        }
        return out;
    }

    /**
     * Shuffles the list of pieces for each player
     */
    public void shufflePieces() {
        Collections.shuffle(piecesPlayerOne);
        Collections.shuffle(piecesPlayerTwo);

    }

    /**
     * Returns the piece list depending on the player ID given
     */
    public List<Piece> getPieceListByID(int id){
        if(id == 0) return piecesPlayerOne;
        else return piecesPlayerTwo;
    }

    /**
     * Manually select a piece from the listview adding it to the board.
     * @param p the selected piece
     */
    public void manualPieceSelection(Piece p) {
        if (playerData.addPieceToPieceList(p) != null && !squaresBoard[p.getX()][p.getY()].getIsOccupied()) {
            if (p.getPlayer() == null) GameSaveState.getPlayerTurn().getPiecesList().add(playerData.addPieceToPieceList(p));
            else p.getPlayer().getPiecesList().add(playerData.addPieceToPieceList(p));
            squaresBoard[p.getX()][p.getY()].setPiece(p);
        }
    }

    /**
     * Method for manually assigning pieces.
     */
    public void placePieces() {
        int counter = 0;
        for (int i = 3; i >= 0; i--) {
            for (int j = 0; j < 10; j++) {
                arrangePiecesBottomPlayer(piecesPlayerOne.get(counter), i, j);
                piecesPlayerOne.get(counter).setX(i);
                piecesPlayerOne.get(counter++).setY(j);

            }
        }
        int counterTwo = 0;
        for (int i = 9; i >= 6; i--) {
            for (int j = 0; j <= 9; j++) {
                arrangePiecesTopPlayer(piecesPlayerTwo.get(counterTwo), i, j);
                piecesPlayerTwo.get(counterTwo).setX(i);
                piecesPlayerTwo.get(counterTwo++).setY(j);
            }
        }
    }

    /**
     * places a piece for starting top player
     */
    public void arrangePiecesTopPlayer(Piece piece, int x, int y) {
        squaresBoard[x][y].setPiece(piece);
        squaresBoard[x][y].setOccupied(true);
    }

    /**
     * places a piece for starting bottom player
     */
    public void arrangePiecesBottomPlayer(Piece piece, int x, int y) {
        squaresBoard[x][y].setPiece(piece);
        squaresBoard[x][y].setOccupied(true);

    }

    /**
     * Gets pieces one player to display on the view during turn change
     */
    public List<int[]> getPiecesOnePlayer(int id) {
        List<int[]> pieces = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (squaresBoard[i][j].getIsOccupied()) {
                    if (squaresBoard[i][j].getPiece().getPlayer().getId() == id) pieces.add(new int[]{i, j});


                }
            }
        }

        return pieces;
    }

    /**
     * Calculates the score of the winning player for the leaderboard, it adds up all the power levels of the players remaining pieces.
     * @return Returns an Intiger that holds the sum of all remaining pieces power levels.
     */
    public int getScore(int id) {
        int score = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (squaresBoard[i][j].getIsOccupied()) {
                    if (squaresBoard[i][j].getPiece().getPlayer().getId() == id) score +=squaresBoard[i][j].getPiece().getRankPower();
                }
            }
        }
        return score;
    }

    /**
     * Clears the board of pieces and empties the piece lists of each player
     */
    public void clearBoardById(int id){
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                if (squaresBoard[i][j].getPiece() != null){
                    if (squaresBoard[i][j].getPiece().getPlayer().getId() == id) squaresBoard[i][j] = new Square();
                }
            }
        }
        if (id == 1) piecesPlayerTwo = new ArrayList<>();
        else piecesPlayerOne = new ArrayList<>();

    }

    /**
     * Method to clear the board and the player piece lists
     */
    public void clearFullBoardAndPlayerLists(){
        squaresBoard = new Square[SQUARE_ARRAY_HEIGHT + 1][SQUARE_ARRAY_WIDTH + 1];

        piecesPlayerTwo = new ArrayList<>();
        piecesPlayerOne = new ArrayList<>();
    }
    public int getSQUARE_ARRAY_WIDTH() {
        return SQUARE_ARRAY_WIDTH;
    }

    public int getSQUARE_ARRAY_HEIGHT() {
        return SQUARE_ARRAY_HEIGHT;
    }

    public Square[][] getSquaresBoard() {
        return squaresBoard;
    }
}
