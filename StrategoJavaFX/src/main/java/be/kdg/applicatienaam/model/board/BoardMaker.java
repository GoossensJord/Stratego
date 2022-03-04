package be.kdg.applicatienaam.model.board;

import be.kdg.applicatienaam.model.pieces.Piece;
import be.kdg.applicatienaam.model.player.Player;
import be.kdg.applicatienaam.model.player.PlayerData;

import java.util.List;

public class BoardMaker {
    private int boardHeight = 60;
    private int boardWidth = 20;

    private final int SQUARE_ARRAY_WIDTH = Math.abs((boardWidth + 1) / 2);
    private final int SQUARE_ARRAY_HEIGHT = Math.abs((boardHeight + 1) / 6);

    protected Square[][] squaresBoard;


    PlayerData playerData = new PlayerData();


    public BoardMaker() {
        squaresBoard = new Square[SQUARE_ARRAY_HEIGHT + 1][SQUARE_ARRAY_WIDTH + 1];

    }

    public void randomlyPlacePieces(Player playerOne, Player playerTwo) {

        List<Piece> piecesPlayerOne = playerData.createRandomPieceList(playerOne);
        List<Piece> piecesPlayerTwo = playerData.createRandomPieceList(playerTwo);

        int counter = 0;
        for (int i = 3; i >= 0; i--) {
            for (int j = 0; j < 10; j++) {
                arrangePiecesBottomPlayer(piecesPlayerOne.get(counter),i,j);
                piecesPlayerOne.get(counter).setX(i);
                piecesPlayerOne.get(counter++).setY(j);

            }
        }
        int counterTwo = 0;
        for (int i = 9; i >= 6; i--) {
            for (int j = 0; j <= 9; j++) {
                arrangePiecesTopPlayer(piecesPlayerTwo.get(counterTwo),i,j);
                piecesPlayerTwo.get(counterTwo).setX(i);
                piecesPlayerTwo.get(counterTwo++).setY(j);
            }
        }
    }

    public void arrangePiecesTopPlayer(Piece piece, int x, int y) {
        squaresBoard[x][y].setPiece(piece);
        squaresBoard[x][y].setOccupied(true);
    }

    public void arrangePiecesBottomPlayer(Piece piece,int x, int y) {
        squaresBoard[x][y].setPiece(piece);
        squaresBoard[x][y].setOccupied(true);

    }

    public boolean spaceAvailableNoPrint(int heightindex, int widthindex) {
        if (!squaresBoard[heightindex][widthindex].getIsOccupied()) {
            return true;
        } else return false;
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
