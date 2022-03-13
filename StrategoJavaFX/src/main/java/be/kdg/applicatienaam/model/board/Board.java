package be.kdg.applicatienaam.model.board;


import be.kdg.applicatienaam.model.pieces.Piece;
import be.kdg.applicatienaam.model.pieces.Rank;

import java.util.List;

public class Board {
    private BoardMaker boardMaker;


    public Board(BoardMaker boardMaker) {
        this.boardMaker = boardMaker;
        fillWithSquares();
    }

    public boolean spaceAvailable(int heightIndex, int widthIndex) {
        if (!boardMaker.squaresBoard[heightIndex][widthIndex].getIsOccupied()) {
            return true;
        } else {
            System.out.println("place taken");
            return false;
        }
    }

    public Square[][] getBord() {
        return boardMaker.squaresBoard;
    }

    public boolean notOutOfBounds(int x, int y) {

        if (x >= 0 && y >= 0) {
            return x < boardMaker.getSQUARE_ARRAY_WIDTH() && y < boardMaker.getSQUARE_ARRAY_HEIGHT();
        } else {
            return false;
        }
    }

    public void fillWithSquares() {

        for (int i = 0; i < boardMaker.getSQUARE_ARRAY_HEIGHT(); i++) {
            for (int j = 0; j < boardMaker.getSQUARE_ARRAY_WIDTH(); j++) {
                boardMaker.getSquaresBoard()[i][j] = new Square(this);
            }
        }
    }

    public void makeMove(int[] move, Piece p) {
        int[] tempPos = new int[]{p.getX(), p.getY()};
        p.setX(move[0]);
        p.setY(move[1]);
        boardMaker.getSquaresBoard()[p.getX()][p.getY()].setPiece(p);
        boardMaker.getSquaresBoard()[tempPos[0]][tempPos[1]].removePiece();
    }

    public void makeAttack(int[] attack, Piece p) {
        int[] tempPos = new int[]{p.getX(), p.getY()};
        if (boardMaker.getSquaresBoard()[p.getX()][p.getY()].getPiece().getRankPower() >= boardMaker.getSquaresBoard()[attack[0]][attack[1]].getPiece().getRankPower() ||
                (boardMaker.getSquaresBoard()[p.getX()][p.getY()].getPiece().getRank().equals(Rank.MINER) && boardMaker.getSquaresBoard()[attack[0]][attack[1]].getPiece().getRank().equals(Rank.BOMB))
                || (boardMaker.getSquaresBoard()[p.getX()][p.getY()].getPiece().getRank().equals(Rank.SPY) && boardMaker.getSquaresBoard()[attack[0]][attack[1]].getPiece().getRank().equals(Rank.MARSHAL))) {

            boardMaker.getSquaresBoard()[attack[0]][attack[1]].removePiece();
            p.setX(attack[0]);
            p.setY(attack[1]);
            boardMaker.getSquaresBoard()[p.getX()][p.getY()].setPiece(p);
        }
        boardMaker.getSquaresBoard()[tempPos[0]][tempPos[1]].removePiece();
    }



}
