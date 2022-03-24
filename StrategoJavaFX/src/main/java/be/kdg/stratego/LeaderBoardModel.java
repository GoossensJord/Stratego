package be.kdg.stratego;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LeaderBoardModel {

    public LeaderBoardModel(){

    }
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
