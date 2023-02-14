package de.ekatchko.bowlingkata;

import de.ekatchko.bowlingkata.models.BowlingGame;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Pattern TWO_CHAR_PATTERN = Pattern.compile("^[1-9-][1-9-/]?$|^X$");
    private static final Pattern THREE_CHAR_PATTERN = Pattern.compile("^[1-9-X][1-9-/X]?[1-9-X]?$");
    public static void main(String[] args) {
        BowlingGame game = new BowlingGame();

        Scanner frameInput = new Scanner(System.in);
        System.out.println("Please enter a string of bowling frames:");

        String frameLine = frameInput.nextLine();
        String[] framesSplit = frameLine.split(" ");
        if (framesSplit.length > 10) {
            System.out.println("Error: You put more than 10 frames. Exiting.");
            System.exit(1);
        }
        for (int i = 0; i < framesSplit.length; i++) {
            Matcher matcher;
            if (i % 9 == 0) {
                matcher = THREE_CHAR_PATTERN.matcher(framesSplit[i]);
            } else {
                matcher = TWO_CHAR_PATTERN.matcher(framesSplit[i]);
            }
            boolean matchFound = matcher.find();
            if (!matchFound) {
                System.out.printf("Error in: %s. Exiting.%n", framesSplit[i]);
                System.exit(1);
            }
        }

        game.addFrames(frameLine);
        game.computeScore();
        System.out.printf("Your score is: %s.%n", game.getScore());
    }

}
