package board;

import pieces.Piece;
import pieces.Rank;

import java.util.*;

public class Board {
    //placeholder for input later.
    private int boardHeight = 60;
    private int boardWidth = 20;
    //square arr sizing
    private final int SQUARE_ARRAY_WIDTH = Math.abs((boardWidth + 1) / 2);
    private final int SQUARE_ARRAY_HEIGHT = Math.abs((boardHeight + 1) / 6);
    private Scanner sc = new Scanner(System.in);

    Square[][] squaresBoard = new Square[SQUARE_ARRAY_HEIGHT + 1][SQUARE_ARRAY_WIDTH + 1];


    //if piece selected highlight moveable squares
    public Board() {
        fillWithSquares();
    }

    //makes squares on all index to not get nullpointers, then adds empty pieces
    public void fillWithSquares() {

        for (int i = 0; i <= SQUARE_ARRAY_HEIGHT; i++) {
            for (int j = 0; j <= SQUARE_ARRAY_WIDTH; j++) {
                if (squaresBoard[i][j] == null) {
                    squaresBoard[i][j] = new Square(this);
                }
            }
        }
    }


    public void fillBoard(Player player) {
        for (Rank r : Rank.values()) {
            for (int i = 0; i < r.getAmnt(); i++) {
                assignPiece(r, player);
            }
        }
        System.out.println("full");
    }

    //for assigning pieces at the start of the game, to be cleaned up
    public void assignPiece(Rank rank, Player player) {

        boolean validNumberRange = true;
        boolean placeTaken = true;
        String index = " ";
        int heightIndex;
        int widthIndex;

        while (validNumberRange) {
            while (placeTaken) {
                index = inputString(player,rank);
                heightIndex = Character.digit(index.charAt(0), 10);
                widthIndex = Character.digit(index.charAt(1), 10);

                if (!rangeOfInputNumbersCheck(player, heightIndex, widthIndex)) {
                    if (spaceAvailable(heightIndex, widthIndex)) {
                        squaresBoard[heightIndex][widthIndex].setPiece(new Piece(rank, player, heightIndex, widthIndex));
                        placeTaken = false;
                        validNumberRange = false;
                        printOutCurrentBoard();
                    }

                }
            }
        }
    }
    public String inputString(Player player, Rank rank){
        Scanner sc = new Scanner(System.in);
        String index = " ";
        boolean validAmountOfNumbers = true;

        while (validAmountOfNumbers) {
            System.out.println(player.getName() + ", where do u want to place the " + rank.getName() + "? ");
            index = sc.next();
            validAmountOfNumbers = ammountOfInputCharacterCheck(index);
        }
        return index;
    }

    //checks if space is available to place piece while setting up piece layout
    public boolean spaceAvailable(int heightIndex, int widthIndex) {


        if (!squaresBoard[heightIndex][widthIndex].getIsOccupied()) {

            return true;

        } else {

            System.out.println("place taken");
            return false;

        }

    }

    //available squares to move piece to
    public List<int[]> availableSquares(int x, int y) {
        List<int[]> moveableSquares = new ArrayList<>();
        if (!squaresBoard[x + 1][y].getIsOccupied()) {
            moveableSquares.add(new int[]{x + 1, y});
            System.out.println("Square DOWN available");
        }
        if (!squaresBoard[x][y + 1].getIsOccupied()) {
            moveableSquares.add(new int[]{x, y + 1});
            System.out.println("Square RIGTH available");
        }
        if (!squaresBoard[x][y - 1].getIsOccupied()) {
            moveableSquares.add(new int[]{x - 1, y});
            System.out.println("Square LEFT available");
        }
        if (!squaresBoard[x - 1][y].getIsOccupied()) {
            moveableSquares.add(new int[]{x, y - 1});
            System.out.println("Square UP available");
        }
        return moveableSquares;
    }


    public boolean rangeOfInputNumbersCheck(Player player, int heightIndex, int widthIndex) {

        if (player.getId() == 1) {
            if (heightIndex <= 3 && widthIndex <= 9 && heightIndex >= 0) {
                return false;
            } else {
                System.out.println("incorrect number range");
                return true;
            }
        } else {
            if (heightIndex >= 6 && widthIndex <= 9 && heightIndex <= 9) {
                return false;

            } else {
                System.out.println("incorrect number range");
                return true;
            }

        }
    }


    public boolean ammountOfInputCharacterCheck(String input) {

        if (input.length() != 2) {

            System.out.println("Enter two numbers no spaces");
            return true;

        } else return false;
    }


    public void printOutCurrentBoard() {
        System.out.println("Stratego\n");
        for (int i = 0; i < SQUARE_ARRAY_HEIGHT; i++) {
            for (int j = 0; j < SQUARE_ARRAY_WIDTH; j++) {
                System.out.print(squaresBoard[i][j] + " ");
            }
            System.out.print(" " + i);
            System.out.println();
        }

        System.out.println("\n0  1  2  3  4  5  6  7  8  9");
    }


    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    public int getPIECE_ARRAY_WIDTH() {
        return this.SQUARE_ARRAY_WIDTH;
    }

    public int getPIECE_ARRAY_HEIGHT() {
        return this.SQUARE_ARRAY_WIDTH;
    }


    //xd
    // randomly assigns the correct amount of pieces for each player, TESTING PURPOSES.
    public void randomlyPlacePieces(Player player) {
        List<Piece> piecesPlayer = Arrays.asList(
                new Piece(Rank.BOMB, player, 0, 0),
                new Piece(Rank.BOMB, player, 0, 0),
                new Piece(Rank.BOMB, player, 0, 0),
                new Piece(Rank.BOMB, player, 0, 0),
                new Piece(Rank.BOMB, player, 0, 0),
                new Piece(Rank.BOMB, player, 0, 0),
                new Piece(Rank.MARSHAL, player, 0, 0),
                new Piece(Rank.GENERAL, player, 0, 0),
                new Piece(Rank.COLONEL, player, 0, 0),
                new Piece(Rank.COLONEL, player, 0, 0),
                new Piece(Rank.MAJOR, player, 0, 0),
                new Piece(Rank.MAJOR, player, 0, 0),
                new Piece(Rank.MAJOR, player, 0, 0),
                new Piece(Rank.CAPTAIN, player, 0, 0),
                new Piece(Rank.CAPTAIN, player, 0, 0),
                new Piece(Rank.CAPTAIN, player, 0, 0),
                new Piece(Rank.CAPTAIN, player, 0, 0),
                new Piece(Rank.LUITENANT, player, 0, 0),
                new Piece(Rank.LUITENANT, player, 0, 0),
                new Piece(Rank.LUITENANT, player, 0, 0),
                new Piece(Rank.LUITENANT, player, 0, 0),
                new Piece(Rank.SERGEANT, player, 0, 0),
                new Piece(Rank.SERGEANT, player, 0, 0),
                new Piece(Rank.SERGEANT, player, 0, 0),
                new Piece(Rank.SERGEANT, player, 0, 0),
                new Piece(Rank.MINER, player, 0, 0),
                new Piece(Rank.MINER, player, 0, 0),
                new Piece(Rank.MINER, player, 0, 0),
                new Piece(Rank.MINER, player, 0, 0),
                new Piece(Rank.MINER, player, 0, 0),
                new Piece(Rank.SCOUT, player, 0, 0),
                new Piece(Rank.SCOUT, player, 0, 0),
                new Piece(Rank.SCOUT, player, 0, 0),
                new Piece(Rank.SCOUT, player, 0, 0),
                new Piece(Rank.SCOUT, player, 0, 0),
                new Piece(Rank.SCOUT, player, 0, 0),
                new Piece(Rank.SCOUT, player, 0, 0),
                new Piece(Rank.SCOUT, player, 0, 0),
                new Piece(Rank.SPY, player, 0, 0),
                new Piece(Rank.FLAG, player, 0, 0));

        Collections.shuffle(piecesPlayer);
        for (Piece piece : piecesPlayer) {
            if (player.getId() == 1) {
                arrangePiecesTopPlayer(piece);
            } else {
                arrangePiecesBottomPlayer(piece);
            }
        }

    }

    public void arrangePiecesTopPlayer(Piece piece) {
        for (int i = 0; i < SQUARE_ARRAY_HEIGHT; i++) {
            for (int j = 0; j < SQUARE_ARRAY_WIDTH; j++) {
                if (spaceAvailable(i, j)) {
                    squaresBoard[i][j].setPiece(piece);
                    return;
                }
            }
        }
    }


    public void arrangePiecesBottomPlayer(Piece piece) {
        for (int i = SQUARE_ARRAY_HEIGHT - 1; i >= 0; i--) {
            for (int j = SQUARE_ARRAY_WIDTH - 1; j >= 0; j--) {
                if (spaceAvailable(i, j)) {
                    squaresBoard[i][j].setPiece(piece);
                    return;
                }
            }
        }
    }

    public void makeMove(Piece p) {

        int[] move = chooseMove(p);
        if(spaceAvailable(move[0],move[1])) {
            p.setX(move[0]);
            p.setY(move[1]);
        }
    }

    public int[] chooseMove(Piece p){
        Scanner sc = new Scanner(System.in);
        int counter = 1;
        List<int[]> listArr = availableSquares(p.getX(), p.getY());

        for (int[] arr : listArr) {
            System.out.print(counter++ + " : ");
            Arrays.stream(arr).forEach(i -> System.out.print(i));
            System.out.println();
        }
        System.out.println("make your pick (1,2,3,4)");

        int n = sc.nextInt();

        for (int i = 0; i < listArr.size(); i++) {
            if(n-1 == i && n < listArr.size()+1){
                Arrays.stream(listArr.get(i)).forEach(num -> System.out.print(num));
                return listArr.get(i-1);
            }
        }
        System.out.println("Try again.");
        return chooseMove(p);
    }
}