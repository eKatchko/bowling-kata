package de.ekatchko.bowlingkata.models;

import java.util.ArrayList;

public class BowlingGame {
    private final ArrayList<Frame> frames;
    private int score;

    public BowlingGame() {
        this.frames = new ArrayList<>();
        this.score = 0;
    }

    public void addRoll(int pins) {
        if (frames.isEmpty()) {
            Frame frame = new Frame(false);
            frame.addRoll(pins);
            frames.add(frame);
        } else {
            Frame lastFrame = frames.get(frames.size() - 1);
            if (!lastFrame.isCompleted()) {
                lastFrame.addRoll(pins);
            } else if (frames.size() == 9){
                Frame frame = new Frame(true);
                frame.addRoll(pins);
                frames.add(frame);
            } else {
                Frame frame = new Frame(false);
                frame.addRoll(pins);
                frames.add(frame);
            }
        }
    }

    public boolean isGameOver() {
        return frames.size() == 10 && frames.get(9).isCompleted();
    }

    public ArrayList<Frame> getFrames() {
        return this.frames;
    }

    public int computeScore() {
        this.score = 0;

        for (int frameIndex = 0; frameIndex < this.frames.size(); frameIndex++) {
            ArrayList<Roll> rolls = this.frames.get(frameIndex).getRolls();
            for (Roll roll : rolls) {
                this.score += roll.value();
                if (roll.isSpare()) {
                    try {
                        this.score += this.frames.get(frameIndex + 1).getRolls().get(0).value();
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                }
                if (roll.isStrike()) {
                    try {
                        ArrayList<Roll> nextRolls = this.frames.get(frameIndex + 1).getRolls();
                        if (nextRolls.size() > 1) {
                            this.score += nextRolls.get(0).value();
                            this.score += nextRolls.get(1).value();
                        } else {
                            this.score += nextRolls.get(0).value();
                            this.score += this.frames.get(frameIndex + 2).getRolls().get(0).value();
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
