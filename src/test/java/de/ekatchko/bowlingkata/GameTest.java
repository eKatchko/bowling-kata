package de.ekatchko.bowlingkata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {
    private BowlingGame bowlingGame;

    @Test
    @DisplayName("Initialize a bowling game with 10 frames.")
    void initializeBowlingGame() {
        bowlingGame = new BowlingGame();
        String framesString = "-- -- -- -- -- -- -- -- -- ---";
        bowlingGame.addFrames(framesString);
        ArrayList<Frames> frames = bowlingGame.getFrames();

        assertThat(frames.size()).isEqualTo(10);
    }

    @Test
    @DisplayName("Initialize a bowling game with 5 frames.")
    void initializeBowlingGameWith5Frames() {
        bowlingGame = new BowlingGame();
        String framesString = "-- -- -- -- --";
        bowlingGame.addFrames(framesString);
        ArrayList<Frames> frames = bowlingGame.getFrames();

        assertThat(frames.size()).isEqualTo(5);
    }

    @Test
    @DisplayName("Initialize a bowling game with 5 frames and extend by 5 more frames.")
    void initializeBowlingGameWith5FramesAndExtend() {
        bowlingGame = new BowlingGame();
        String framesString = "-- -- -- -- --";
        bowlingGame.addFrames(framesString);
        String framesString = "-- -- -- -- ---";
        bowlingGame.addFrames(framesString);
        ArrayList<Frames> frames = bowlingGame.getFrames();

        assertThat(frames.size()).isEqualTo(10);
    }
}