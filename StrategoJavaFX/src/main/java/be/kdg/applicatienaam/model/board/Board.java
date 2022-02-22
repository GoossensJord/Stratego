package be.kdg.applicatienaam.model.board;


import javafx.scene.Node;
import javafx.scene.text.Text;

public class Board {
    public Square[][] bord;
    private BoardMaker boardMaker;

    public Board(BoardMaker boardMaker) {
        bord = new Square[10][10];
        this.boardMaker = boardMaker;
        bord = boardMaker.fillWithSquares(this);
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
        return bord;
    }

    public boolean notOutOfBounds(int x, int y) {

        if (x >= 0 && y >= 0) {
            return x < boardMaker.getSQUARE_ARRAY_WIDTH() && y < boardMaker.getSQUARE_ARRAY_HEIGHT();
        } else {
            return false;
        }
    }
}
