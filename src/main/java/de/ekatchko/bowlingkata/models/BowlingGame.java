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
            Roll[] rolls = this.frames.get(frameIndex).getRolls();
            for (int rollIndex = 0; rollIndex < rolls.length; rollIndex++) {
                this.score += rolls[rollIndex].value();
            }
        }

        return this.getScore();
    }

    public int getScore() {
        return this.score;
    }
}
