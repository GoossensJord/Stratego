package be.kdg.applicatienaam.model.board;


import javafx.scene.Node;
import javafx.scene.text.Text;

public class Board {
    public Square[][] bord;
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

        for (int i = 0; i <= boardMaker.getSQUARE_ARRAY_HEIGHT(); i++) {
            for (int j = 0; j <= boardMaker.getSQUARE_ARRAY_WIDTH(); j++) {
                if (boardMaker.getSquaresBoard()[i][j] == null) {
                    boardMaker.getSquaresBoard()[i][j] = new Square(this);
                }
            }
        }

    }


}
