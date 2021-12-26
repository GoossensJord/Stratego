package board;

import pieces.Piece;
import pieces.Rank;
import pieces.Scout;

import java.util.*;

public class Board {

    private Scanner sc = new Scanner(System.in);
    private BoardMaker boardMaker;


    public Board(BoardMaker boardMaker) {
        this.boardMaker = boardMaker;
    }

    //Allows players to manually fill their board with the available pieces.
    public void fillBoard(Player player) {
        for (Rank r : Rank.values()) {
            for (int i = 0; i < r.getAmnt(); i++) {
                boardMaker.assignPiece(r, player);
            }
        }
    }

    //available squares to move piece to
    public List<int[]> availableSquares(int x, int y) {
        List<int[]> moveableSquares = new ArrayList<>();
        if (notOutOfBounds(x + 1, y) && !boardMaker.getSquaresBoard()[x + 1][y].getIsOccupied()) {
            moveableSquares.add(new int[]{x + 1, y});
            System.out.println("Square DOWN available");
        }
        if (notOutOfBounds(x, y + 1) && !boardMaker.getSquaresBoard()[x][y + 1].getIsOccupied()) {
            moveableSquares.add(new int[]{x, y + 1});
            System.out.println("Square RIGTH available");
        }
        if (notOutOfBounds(x, y - 1) && !boardMaker.getSquaresBoard()[x][y - 1].getIsOccupied()) {
            moveableSquares.add(new int[]{x - 1, y});
            System.out.println("Square LEFT available");
        }
        if (notOutOfBounds(x - 1, y) && !boardMaker.getSquaresBoard()[x - 1][y].getIsOccupied()) {
            moveableSquares.add(new int[]{x, y - 1});
            System.out.println("Square UP available");
        }
        return moveableSquares;
    }

    //Selecting a piece for moving attacking etc
    public Piece choosePiece() {
        System.out.println("Enter a position of the piece you would like to move.");
        String pos = sc.next();
        if( pos.length() != 2 ) return choosePiece();
        int x = Character.digit(pos.charAt(0), 10);
        int y = Character.digit(pos.charAt(1), 10);
        if (boardMaker.getSquaresBoard()[x][y].getPiece() != null) return boardMaker.getSquaresBoard()[x][y].getPiece();

        else return choosePiece();
    }

    //Executing the move
    public void makeMove(Piece p) {
        int[] move = chooseMove(p);
        int[] tempPos = new int[]{p.getX(), p.getY()};
        if (notOutOfBounds(move[0], move[1])) {
            p.setX(move[0]);
            p.setY(move[1]);
            boardMaker.getSquaresBoard()[p.getX()][p.getY()].setPiece(p);
            boardMaker.getSquaresBoard()[tempPos[0]][tempPos[1]].removePiece();
        }
    }

    //Choosing where to move
    private int[] chooseMove(Piece p) {

        int counter = 1;
        //Scout implementation, doe maar als ge wilt, ma da hoeft nie eht meer denk ik.
        //if( p instanceof Scout) List<int[]> listArr = ((Scout) p).getCrossPositions(11)
        List<int[]> listArr = availableSquares(p.getX(), p.getY());


        for (int[] arr : listArr) {
            System.out.print(counter++ + " : ");
            Arrays.stream(arr).forEach(i -> System.out.print(i));
            System.out.println();
        }
        //Prevent stuck scenario. No step-sibling action here.
        if (listArr.size() == 0) {
            System.out.println("Choose a new piece, no moves possible.");
            return new int[]{-1, -1};
        }

        System.out.println("make your pick (1,2,3,4)");

        int n = sc.nextInt();

        //printing made move
        for (int i = 0; i < listArr.size(); i++) {
            if (n - 1 == i && n < listArr.size() + 1) {
                Arrays.stream(listArr.get(i)).forEach(num -> System.out.print(num));
                return listArr.get(i);
            }
        }
        //if not succesfull, recursive call for retry (failsafe)
        return chooseMove(p);
    }

    private boolean notOutOfBounds(int x, int y) {
        if (x >= 0 && y >= 0) {
            if (x < boardMaker.getSQUARE_ARRAY_WIDTH() && y < boardMaker.getSQUARE_ARRAY_HEIGHT()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}