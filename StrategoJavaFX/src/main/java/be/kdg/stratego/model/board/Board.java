package be.kdg.stratego.model.board;


import be.kdg.stratego.model.pieces.Piece;
import be.kdg.stratego.model.pieces.Rank;

import java.util.List;

/**
 * Contains the backend board and its related methods
 */

public class Board {
    private final BoardMaker boardMaker;

    /**
     * Creates a board, fills it with squares
     * @param boardMaker class board utilises boardmaker to create the board itself.
     */
    public Board(BoardMaker boardMaker) {
        this.boardMaker = boardMaker;
        fillWithSquares();
    }

    /**
     * Returns a boolean depending on if the square on x and y on the board is free
     */
    public boolean spaceAvailable(int heightIndex, int widthIndex) {
        return !boardMaker.squaresBoard[heightIndex][widthIndex].getIsOccupied();
    }

    /**
     * Returns a boolean depending on if the x and y are out of bounds
     */
    public boolean notOutOfBounds(int x, int y) {

        if (x >= 0 && y >= 0) {
            return x < boardMaker.getSQUARE_ARRAY_WIDTH() && y < boardMaker.getSQUARE_ARRAY_HEIGHT();
        } else {
            return false;
        }
    }

    /**
     * Fills the board with squares, on which pieces can be placed later
     */
    public void fillWithSquares() {

        for (int i = 0; i < boardMaker.getSQUARE_ARRAY_HEIGHT(); i++) {
            for (int j = 0; j < boardMaker.getSQUARE_ARRAY_WIDTH(); j++) {
                boardMaker.getSquaresBoard()[i][j] = new Square();
            }
        }
    }

    /**
     * Method to make a move, gives the piece a new place on the board and removes it from the old piece
     */
    public void makeMove(int[] move, Piece p) {
        int[] tempPos = new int[]{p.getX(), p.getY()};
        p.setX(move[0]);
        p.setY(move[1]);
        boardMaker.getSquaresBoard()[p.getX()][p.getY()].setPiece(p);
        boardMaker.getSquaresBoard()[tempPos[0]][tempPos[1]].removePiece();
    }

    /**
     * Method to make an attack, decides the winner and places the winner on the attacked piece's square.
     */
    public void makeAttack(int[] attack, Piece p) {
        int[] tempPos = new int[]{p.getX(), p.getY()};
        if (matchupChecker(p,boardMaker.getSquaresBoard()[attack[0]][attack[1]].getPiece())){
            boardMaker.getSquaresBoard()[attack[0]][attack[1]].removePiece();
            p.setX(attack[0]);
            p.setY(attack[1]);
            boardMaker.getSquaresBoard()[p.getX()][p.getY()].setPiece(p);
        }
        boardMaker.getSquaresBoard()[tempPos[0]][tempPos[1]].removePiece();
    }

    /**
     * Checks the piece matchup, returns the winning piece
     */
    public boolean matchupChecker(Piece p, Piece p1){
        if(p.getRank().equals(Rank.MINER) && p1.getRank().equals(Rank.BOMB)) return true;
        if(p.getRank().equals(Rank.SPY) && p1.getRank().equals(Rank.MARSHAL)) return true;
        if(p.getRank().equals(p1.getRank())) return true;
        return p.getRankPower() > p1.getRankPower();

    }
    /**
     * Method that clears the board and fills it with empty squares
     */
    public void clearBoard() {
        boardMaker.clearFullBoardAndPlayerLists();
        fillWithSquares();
    }

    /**
     * Method that clears the back-end board depending on the ID
     * @param id The ID determines who's pieces will be removed
     */
    public void clearSetupSideById(int id){
        boardMaker.clearBoardById(id);
    }

    public Square[][] getBoardMaker() {
        return boardMaker.squaresBoard;
    }


    public List<Piece> getPieceListByID(int id) {
        return boardMaker.getPieceListByID(id);
    }


}
