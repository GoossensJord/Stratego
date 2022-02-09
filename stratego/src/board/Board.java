package board;

import pieces.*;
import player.Player;

import javax.crypto.spec.PSource;
import java.util.*;

public class Board {

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

    //checks for attackable pieces nearby returns them in a list
    public List<int[]> attackPossible(int x, int y, Player player) {
        int counter = 0;
        List<int[]> attackableSquares = new ArrayList<>();
        if (notOutOfBounds(x + 1, y) && boardMaker.getSquaresBoard()[x + 1][y].getIsOccupied()) {
            if (!boardMaker.getSquaresBoard()[x + 1][y].getPiece().getPlayer().equals(player)) {
                attackableSquares.add(new int[]{x + 1, y});
                System.out.println(++counter + " Square DOWN attackable");
            }
        }
        if (notOutOfBounds(x, y + 1) && boardMaker.getSquaresBoard()[x][y + 1].getIsOccupied()) {
            if (!boardMaker.getSquaresBoard()[x][y + 1].getPiece().getPlayer().equals(player)) {
                attackableSquares.add(new int[]{x, y + 1});
                System.out.println(++counter + " Square RIGHT attackable");
            }
        }
        if (notOutOfBounds(x, y - 1) && boardMaker.getSquaresBoard()[x][y - 1].getIsOccupied()) {
            if (!boardMaker.getSquaresBoard()[x][y - 1].getPiece().getPlayer().equals(player)) {
                attackableSquares.add(new int[]{x, y - 1});
                System.out.println(++counter + " Square LEFT attackable");
            }
        }
        if (notOutOfBounds(x - 1, y) && boardMaker.getSquaresBoard()[x - 1][y].getIsOccupied()) {
            if (!boardMaker.getSquaresBoard()[x - 1][y].getPiece().getPlayer().equals(player)) {
                attackableSquares.add(new int[]{x - 1, y});
                System.out.println(++counter + " Square UP attackable");
            }
        }
        return attackableSquares;
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
        Scanner sc = new Scanner(System.in);

        System.out.println("\n" + player.getName() + ": Enter a position of the piece you would like to move.");
        String pos = sc.next();
        if (pos.length() != 2) return choosePiece(player);
        int x = Character.digit(pos.charAt(0), 10);
        int y = Character.digit(pos.charAt(1), 10);
        if (boardMaker.getSquaresBoard()[x][y].getPiece() == null) {
            System.out.println("\nNo piece on this square");
            return choosePiece(player);
        }
        if (!boardMaker.getSquaresBoard()[x][y].getPiece().isMovable()) {
            System.out.println("\nCant move bombs or flags!");
            return choosePiece(player);
        }


        return boardMaker.getSquaresBoard()[x][y].getPiece();
    }

    //Choosing where to move
    public int[] chooseMove(Piece p, Player player) {
        Scanner sc = new Scanner(System.in);
        //1 = down
     /*   if (p instanceof Scout) {
            List<List<int[]>> scoutarr = ((Scout) p).getCrossPositions();
            System.out.println("choose a direction 1 Down \n2 Up \n3  - 4 ");
            int direction = sc.nextInt();

            //printing all moves
            for (int i = 0; i < scoutarr.size(); i++) {
                for (int j = 0; j < scoutarr.get(i).size(); j++) {
                    System.out.print(scoutarr.get(i).get(j)[0]);
                    System.out.print(scoutarr.get(i).get(j)[1]);
                }
                System.out.println();
            }
            System.out.printf("choose amount of spaces you want to move 0- %d",scoutarr.get(direction).size());
            int amntofSpaces = sc.nextInt();

            return scoutarr.get(direction).get(amntofSpaces);
        } else {*/
            List<int[]> listArr = availableSquares(p.getX(), p.getY());
            //Prevent stuck scenario. No step-sibling action here.
            if (listArr.size() == 0) {
                System.out.println("Choose a new piece, no moves possible.");
                return new int[]{-1, -1};
            }

            System.out.println("make your move pick (1,2,3,4)");
            int n = sc.nextInt();

            //printing made move
            for (int i = 0; i < listArr.size(); i++) {
                if (n - 1 == i && n < listArr.size() + 1) {
                    Arrays.stream(listArr.get(i)).forEach(num -> System.out.print(num));
                    return listArr.get(i);
                }
            }
        //}
        //if not succesfull, recursive call for retry (failsafe)


        return null;
    }

    public int[] chooseAttack(Piece p, Player player) {
        Scanner sc = new Scanner(System.in);

        List<int[]> listAttackableSquares = attackPossible(p.getX(), p.getY(), player);
        if (listAttackableSquares.size() != 0) {

            System.out.println("make your attack pick (1,2,3,4)");
            int attackPick = sc.nextInt();

            for (int i = 0; i < listAttackableSquares.size(); i++) {
                if (attackPick - 1 == i && attackPick < listAttackableSquares.size() + 1) {
                    Arrays.stream(listAttackableSquares.get(i)).forEach(num -> System.out.print(num));
                    return listAttackableSquares.get(i);
                }
            }

        }
        return null;
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

    public void makeAttack(Piece p, Player player) {
        int[] attack = chooseAttack(p, player);
        int[] tempPos = new int[]{p.getX(), p.getY()};
        if (notOutOfBounds(attack[0], attack[1])) {
            Piece winningPiece = p.attack(boardMaker.getSquaresBoard()[attack[0]][attack[1]].getPiece());
            winningPiece.setX(attack[0]);
            winningPiece.setY(attack[1]);
            boardMaker.getSquaresBoard()[winningPiece.getX()][winningPiece.getY()].setPiece(winningPiece);
            boardMaker.getSquaresBoard()[tempPos[0]][tempPos[1]].removePiece();
        }
    }


    private boolean notOutOfBounds(int x, int y) {

        if (x >= 0 && y >= 0) {
            return x < boardMaker.getSQUARE_ARRAY_WIDTH() && y < boardMaker.getSQUARE_ARRAY_HEIGHT();
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