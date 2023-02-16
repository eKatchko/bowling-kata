package de.ekatchko.bowlingkata;

import de.ekatchko.bowlingkata.models.BowlingGame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BowlingGame game = new BowlingGame();

        Scanner frameInput = new Scanner(System.in);
        int nextRoll;

        while (!game.isGameOver()) {
            System.out.println("Please enter a roll between 0-10:");
            try {
                nextRoll = frameInput.nextInt(10);
            } catch (InputMismatchException e) {
                System.out.println("Only integers between 0 and 10 allowed.");
                frameInput.next();
                continue;
            }
            if (nextRoll < 0 || nextRoll > 10) {
                System.out.println("Only integers between 0 and 10 allowed.");
                continue;
            }
            game.addRoll(nextRoll);
            game.computeScore();

            System.out.printf("Your current score for %s is: %s.%n", game.getFrames(), game.getScore());
        }
    }

}
