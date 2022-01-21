package board;

import pieces.Piece;
import pieces.Rank;
import pieces.Scout;
import player.Player;

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
            for (int i = 0; i < r.getAmount(); i++) {
                boardMaker.assignPiece(r, player);
            }
        }
    }

    //available squares to move piece to
    public List<int[]> availableSquares(int x, int y) {
        int counter = 0;
        List<int[]> moveableSquares = new ArrayList<>();
        if (notOutOfBounds(x + 1, y) && !boardMaker.getSquaresBoard()[x + 1][y].getIsOccupied()) {
            moveableSquares.add(new int[]{x + 1, y});
            System.out.println(++counter + " Move DOWN available");
        }
        if (notOutOfBounds(x, y + 1) && !boardMaker.getSquaresBoard()[x][y + 1].getIsOccupied()) {
            moveableSquares.add(new int[]{x, y + 1});
            System.out.println(++counter + " Move RIGHT available");
        }
        if (notOutOfBounds(x, y - 1) && !boardMaker.getSquaresBoard()[x][y - 1].getIsOccupied()) {
            moveableSquares.add(new int[]{x, y - 1});
            System.out.println(++counter + " Square LEFT available");
        }
        if (notOutOfBounds(x - 1, y) && !boardMaker.getSquaresBoard()[x - 1][y].getIsOccupied()) {
            moveableSquares.add(new int[]{x - 1, y});
            System.out.println(++counter + " Square UP available");
        }
        return moveableSquares;
    }

   /* public String printMovesScout(List<int[]> listarr){
        String[] directions = new String[]{"Down","Right","Left","Up"};
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < directions.length; i++) {
            out.append(directions[i] + listarr[i].)
        }
    }*/

    //Selecting a piece for moving attacking etc
    public Piece choosePiece(Player player) {
        System.out.println("\n" + player.getName() + ": Enter a position of the piece you would like to move.");
        String pos = sc.next();
        if (pos.length() != 2) return choosePiece(player);
        int x = Character.digit(pos.charAt(0), 10);
        int y = Character.digit(pos.charAt(1), 10);
        if (boardMaker.getSquaresBoard()[x][y].getPiece() != null) return boardMaker.getSquaresBoard()[x][y].getPiece();

        else return choosePiece(player);
    }

    //Executing the move
    public void makeMove(Piece p, Player player) {

        int[] move = chooseMove(p, player);
        int[] tempPos = new int[]{p.getX(), p.getY()};
        if (notOutOfBounds(move[0], move[1])) {
            p.setX(move[0]);
            p.setY(move[1]);
            boardMaker.getSquaresBoard()[p.getX()][p.getY()].setPiece(p);
            boardMaker.getSquaresBoard()[tempPos[0]][tempPos[1]].removePiece();
        }
    }

    public List<int[]> attackPossible(int x, int y, Player player) {
        int counter = 4;
        List<int[]> attackableSquares = new ArrayList<>();
        if (notOutOfBounds(x + 1, y)) {
            if (boardMaker.getSquaresBoard()[x + 1][y].getIsOccupied()&& !boardMaker.getSquaresBoard()[x + 1][y].getPiece().getPlayer().equals(player)) {
                attackableSquares.add(new int[]{x + 1, y});
                System.out.println(++counter + " Square DOWN attackable");
            }
        }
        if(notOutOfBounds(x, y + 1)) {
            if (boardMaker.getSquaresBoard()[x][y + 1].getIsOccupied() && !boardMaker.getSquaresBoard()[x][y + 1].getPiece().getPlayer().equals(player)) {
                attackableSquares.add(new int[]{x, y + 1});
                System.out.println(++counter + " Square RIGHT attackable");
            }
        }
        if (notOutOfBounds(x, y - 1) ) {
            if (boardMaker.getSquaresBoard()[x][y - 1].getIsOccupied() && !boardMaker.getSquaresBoard()[x][y - 1].getPiece().getPlayer().equals(player)) {
                attackableSquares.add(new int[]{x, y - 1});
                System.out.println(++counter + " Square LEFT attackable");
            }
        }
        if(notOutOfBounds(x - 1, y) ){
        if (boardMaker.getSquaresBoard()[x - 1][y].getIsOccupied()&& !boardMaker.getSquaresBoard()[x - 1][y].getPiece().getPlayer().equals(player)) {
            attackableSquares.add(new int[]{x - 1, y});
            System.out.println(++counter + " Square UP attackable");
        }}
        return attackableSquares;
    }

    //Choosing where to move
    public int[] chooseMove(Piece p, Player player) {
        Scanner sc = new Scanner(System.in);


        List<int[]> listArr;
        listArr = availableSquares(p.getX(), p.getY());
        List<int[]> listAttackableSquares;
        listAttackableSquares = attackPossible(p.getX(), p.getY(), player);

        if (listAttackableSquares.size() != 0) {
            System.out.println("Would you like to attack or move?  1: Move  2: Attack");
            int answer = sc.nextInt();
            if (answer == 2) {

                System.out.println("Attackable squares: ");

            }
        }


        //Scout implementation, doe maar als ge wilt, ma da hoeft nie eht meer denk ik.
        //if( p instanceof Scout) List<int[]> listArr = ((Scout) p).getCrossPositions(11)



       /* if(p instanceof Scout){
            listArr = ((Scout) p).getCrossPositions();
            printMovesScout(listArr);
        }*/
        // else {

        // }
        //Prevent stuck scenario. No step-sibling action here.
        if (listArr.size() == 0) {
            System.out.println("Choose a new piece, no moves possible.");
            return new int[]{-1, -1};
        }


        //we need to add a check on player before making the move, so u cant move other players pieces
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
        return chooseMove(p, player);
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

    public boolean spaceAvailable(int heightIndex, int widthIndex) {
        if (!boardMaker.squaresBoard[heightIndex][widthIndex].getIsOccupied()) {
            return true;
        } else {
            System.out.println("place taken");
            return false;
        }

    }
}