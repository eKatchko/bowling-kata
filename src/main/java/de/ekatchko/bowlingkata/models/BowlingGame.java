package de.ekatchko.bowlingkata.models;

import java.util.ArrayList;

public class BowlingGame {
    private final ArrayList<Frame> frames;
    private int score;

    public BowlingGame() {
        this.frames = new ArrayList<>();
        this.score = 0;
    }

    public void addFrames(String frames) {
        String[] splitFrames = frames.split(" ");
        for (String splitFrame : splitFrames) {
            if (this.frames.size() >= 10) {
                return;
            }
            this.frames.add(new Frame(splitFrame));
        }
    }

    public ArrayList<Frame> getFrames() {
        return this.frames;
    }

    public int computeScore() {
        this.score = 0;

        for (int frameIndex = 0; frameIndex < this.frames.size(); frameIndex++) {
            char[] rolls = this.frames.get(frameIndex).getRolls();
            for (int rollIndex = 0; rollIndex < rolls.length; rollIndex++) {
                switch (rolls[rollIndex]) {
                    case 'X' -> this.computeStrike();
                    case '/' -> this.computeSpare(rolls, rollIndex);
                    case '-' -> {}
                    default -> this.score += Character.getNumericValue(rolls[rollIndex]);
                }
            }
        }

        return this.getScore();
    }

    private void computeStrike() {
        this.score += 10;
    }

    private void computeSpare(char[] rolls, int rollIndex) {
        int prevRoll = Character.getNumericValue(rolls[rollIndex - 1]);
        if (prevRoll < 0) {
            prevRoll = 0;
        }
        this.score += (10 - prevRoll);
    }

    public int getScore() {
        return this.score;
    }
}
