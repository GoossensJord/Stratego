import board.Board;
import board.Player;
import pieces.Piece;
import pieces.Rank;

public class TestMain {
    public static void main(String[] args) {
        Board board = new Board();
        Player pl = new Player("jord",board);
        Piece p = new Piece(Rank.MAJOR,pl);


        board.fillCharArray();
        board.makeBoard();



        int[] position = {1,1};
        int[] pos1 = {position[0], position[1] + 1};
        int[] pos2 = {position[0], position[1] - 1};
        int[] pos3 = {position[0] + 1, position[1]};
        int[] pos4 = {position[0] - 1, position[1]};

        int[] outOfBounds = {-1,21};

        System.out.println(p.notOutOfBounds(outOfBounds));
    }
}
