package de.ekatchko.bowlingkata.models;

import java.util.ArrayList;

public class BowlingGame {

    private final ArrayList<Frame> frames;

    public BowlingGame() {
        this.frames = new ArrayList<Frame>();
    }

    public void addFrames(String frames) {
        String[] splitFrames = frames.split(" ");
        for (String splitFrame : splitFrames) {
            Frame frame = new Frame(splitFrame);
            this.frames.add(frame);
        }
    }

    public ArrayList<Frame> getFrames() {
        return this.frames;
    }
}
