package be.kdg.stratego;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Class responsible for the leader board model, reading from the file and sorting the score.
 */
public class LeaderBoardModel {

    /**
     * Default constructor for leaderboard model
     */
    public LeaderBoardModel(){

    }

    /**
     * Reads from a file with highscores and sorts them by points descending.
     * @return returns a string with highscores from high to low
     */
    public String getHighScoreString() {
        StringBuilder highscores = new StringBuilder();
        List<String> lines = new ArrayList<>();
        try {
            File highScores = new File("highScores.txt");
            Scanner sc = new Scanner(highScores);

            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found error");
            e.printStackTrace();
        }
        Collections.sort(lines);
        Collections.reverse(lines);
        for (String s : lines) {
            highscores.append(s);
            highscores.append("\n");
        }
        return highscores.toString();
    }
}
