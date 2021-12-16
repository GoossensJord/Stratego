import board.Board;
import pieces.Piece;

public class testmain {
    public static void main(String[] args) {
        Board board = new Board();
        Piece p = new Piece();

        board.fillCharArray();
        board.makeBoard();

        int[] position = {1,1};
        int[] pos1 = {position[0], position[1] + 1};
        int[] pos2 = {position[0], position[1] - 1};
        int[] pos3 = {position[0] + 1, position[1]};
        int[] pos4 = {position[0] - 1, position[1]};
        boolean b = p.notOutOfBounds(position);
        System.out.println("b = " + b);
        System.out.println(p.notOutOfBounds(position));
        System.out.println("xd");
//        int b = Integer.valueOf('B');
//        System.out.println("b = " + b);
//        int f = Integer.valueOf('F');
//        System.out.println("f = " + f);



    }
}
