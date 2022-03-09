package be.kdg.applicatienaam.model.board;

import be.kdg.applicatienaam.model.pieces.Piece;
import be.kdg.applicatienaam.model.pieces.Rank;
import be.kdg.applicatienaam.model.player.Player;
import be.kdg.applicatienaam.model.player.PlayerData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoardMaker {
    private int boardHeight = 60;
    private int boardWidth = 20;

    private final int SQUARE_ARRAY_WIDTH = Math.abs((boardWidth + 1) / 2);
    private final int SQUARE_ARRAY_HEIGHT = Math.abs((boardHeight + 1) / 6);

    protected Square[][] squaresBoard;

    PlayerData playerData = new PlayerData();

    List<Piece> piecesPlayerOne;
    List<Piece> piecesPlayerTwo;

    public BoardMaker() {
        squaresBoard = new Square[SQUARE_ARRAY_HEIGHT + 1][SQUARE_ARRAY_WIDTH + 1];
        piecesPlayerOne = new ArrayList<>();
        piecesPlayerTwo = new ArrayList<>();
    }

    public void makePieces(Player playerOne, Player playerTwo) {
        piecesPlayerOne = playerData.createPieceList(playerOne);
        piecesPlayerTwo = playerData.createPieceList(playerTwo);
    }

    public List<String> getListView() {
        List<String> out = new ArrayList<>();
        for (Rank r : Rank.values()) {
            for (int i = 0; i < r.getAmount(); i++) {
                out.add(r.getName().substring(0, 2));
            }
        }
        return out;
    }

    public void shufflePieces() {
        Collections.shuffle(piecesPlayerOne);
        Collections.shuffle(piecesPlayerTwo);

    }

    public void manualListChecker(Piece p) {
        if (playerData.getPiecesList().size() < 40) {
            manualPieceSelection(p);
        }
    }


    public String manualPieceSelection(Piece p) {
        if (playerData.addPieceToPieceList(p) != null) {
            piecesPlayerOne.add(playerData.addPieceToPieceList(p));
            squaresBoard[p.getX()][p.getY()].setPiece(p);
            return "Success";
        } else return "Failed, pieces are full";
    }

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

    public void arrangePiecesTopPlayer(Piece piece, int x, int y) {
        squaresBoard[x][y].setPiece(piece);
        squaresBoard[x][y].setOccupied(true);
    }

    public void arrangePiecesBottomPlayer(Piece piece, int x, int y) {
        squaresBoard[x][y].setPiece(piece);
        squaresBoard[x][y].setOccupied(true);

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
