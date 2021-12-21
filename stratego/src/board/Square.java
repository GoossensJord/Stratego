package board;

import pieces.Piece;
import pieces.Rank;

public class Square {

    private boolean isOccupied;
    private Board board;
    public Piece p;
    private int xPosition;
    private int yPosition;

    public Square(Board b){
        this.isOccupied = false;
        this.board = b;
    }

    public void setPiece(Piece p){
        this.p = p;
        isOccupied = true;
    }
    public boolean getIsOccupied() {
        return isOccupied;
    }

    public boolean outOfBoundsOrOccupied(int x, int y) {
        if (!isOccupied) {
            if(x >= 0 && x <= board.getPIECE_ARRAY_HEIGHT() && y>=0 && y<= board.getPIECE_ARRAY_WIDTH()) return false;
            //            for (int i = 0; i < posarr.length; i++) {
//                boolean inBoundsHeight = posarr[i] >= 0 && posarr[i] <= board.getPIECE_ARRAY_HEIGHT();
//                boolean inBoundsWidth = posarr[i] >= 0 && posarr[i] <= board.getPIECE_ARRAY_WIDTH();
//                if(!inBoundsHeight||!inBoundsWidth) return true;
//            }
        }
        return false;
    }

    public Rank getRank(){
        return this.p.getRank();
    }
}
