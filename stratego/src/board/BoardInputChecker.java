package board;

import pieces.Rank;

import java.util.Scanner;

public class BoardInputChecker extends Board {


    public String inputString(Player player, Rank rank) {

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
}
