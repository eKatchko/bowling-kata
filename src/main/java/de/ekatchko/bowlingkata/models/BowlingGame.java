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
            for (Roll roll : rolls) {
                this.score += roll.value();
                if (roll.isSpare()) {
                    try {
                        this.score += this.frames.get(frameIndex + 1).getRolls()[0].value();
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                }
                if (roll.isStrike()) {
                    try {
                        Roll[] nextRolls = this.frames.get(frameIndex + 1).getRolls();
                        if (nextRolls.length > 1) {
                            this.score += nextRolls[0].value();
                            this.score += nextRolls[1].value();
                        } else {
                            this.score += nextRolls[0].value();
                            this.score += this.frames.get(frameIndex + 2).getRolls()[0].value();
                        }
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                }
            }
        }

        return this.getScore();
    }

    public int getScore() {
        return this.score;
    }
}
