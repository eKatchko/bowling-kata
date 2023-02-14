package de.ekatchko.bowlingkata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import de.ekatchko.bowlingkata.models.BowlingGame;
import de.ekatchko.bowlingkata.models.Frame;

import java.util.ArrayList;

public class GameTest {
    private BowlingGame bowlingGame;

    @BeforeEach
    void initNewBowlingGame() {
        bowlingGame = new BowlingGame();
    }

    @Test
    @DisplayName("Initialize a bowling game with 10 frames.")
    void initializeBowlingGame() {
        String framesString = "-- -- -- -- -- -- -- -- -- ---";
        bowlingGame.addFrames(framesString);
        ArrayList<Frame> frames = bowlingGame.getFrames();

        assertThat(frames.size()).isEqualTo(10);
    }

    @Test
    @DisplayName("Initialize a bowling game with 5 frames.")
    void initializeBowlingGameWith5Frames() {
        String framesString = "-- -- -- -- --";
        bowlingGame.addFrames(framesString);
        ArrayList<Frame> frames = bowlingGame.getFrames();

        assertThat(frames.size()).isEqualTo(5);
    }

    @Test
    @DisplayName("Initialize a bowling game with 5 frames and extend by 6 more frames. Expect 10.")
    void initializeBowlingGameWith5FramesAndExtend() {
        String framesString = "-- -- -- -- --";
        bowlingGame.addFrames(framesString);
        framesString = "-- -- -- -- -- ---";
        bowlingGame.addFrames(framesString);
        ArrayList<Frame> frames = bowlingGame.getFrames();

        assertThat(frames.size()).isEqualTo(10);
    }

    @Test
    @DisplayName("Initialize a bowling game with 10 frames and test for correct characters.")
    void initializeBowlingGameAndTestFramesCharacters() {
        String framesString = "?2 34 5/ X 6- -7 8/ 9/ -- XX1";
        bowlingGame.addFrames(framesString);
        ArrayList<Frame> frames = bowlingGame.getFrames();

        assertThat(frames).satisfiesExactly(
                frame1 -> assertThat(frame1.getRolls()).isEqualTo(new char[]{'-', '2'}),
                frame2 -> assertThat(frame2.getRolls()).isEqualTo(new char[]{'3', '4'}),
                frame3 -> assertThat(frame3.getRolls()).isEqualTo(new char[]{'5', '/'}),
                frame4 -> assertThat(frame4.getRolls()).isEqualTo(new char[]{'X'}),
                frame5 -> assertThat(frame5.getRolls()).isEqualTo(new char[]{'6', '-'}),
                frame6 -> assertThat(frame6.getRolls()).isEqualTo(new char[]{'-', '7'}),
                frame7 -> assertThat(frame7.getRolls()).isEqualTo(new char[]{'8', '/'}),
                frame8 -> assertThat(frame8.getRolls()).isEqualTo(new char[]{'9', '/'}),
                frame9 -> assertThat(frame9.getRolls()).isEqualTo(new char[]{'-', '-'}),
                frame10 -> assertThat(frame10.getRolls()).isEqualTo(new char[]{'X', 'X', '1'})
                );
    }

    @Test
    @DisplayName("Play a game with: no spare, no strike, no miss. 23 23 23 23 23 23 23 23 23 23- = 50.")
    void playBowlingGameWithScore50() {
        String framesString = "23 23 23 23 23 23 23 23 23 23-";
        bowlingGame.addFrames(framesString);
        int score = bowlingGame.computeScore();

        assertThat(score).isEqualTo(50);
    }

    @Test
    @DisplayName("Play a game with only strikes: X X X X X X X X X XXX = 300.")
    void playBowlingGameWithScore300() {
        String framesString = "X X X X X X X X X XXX";
        bowlingGame.addFrames(framesString);
        int score = bowlingGame.computeScore();

        assertThat(score).isEqualTo(300);
    }

    @Test
    @DisplayName("Play a game with only spares: 1/ 2/ 3/ 4/ 5/ 6/ 7/ 8/ 9/ 5/5 = 154.")
    void playBowlingGameWithScore154() {
        String framesString = "1/ 2/ 3/ 4/ 5/ 6/ 7/ 8/ 9/ 5/5";
        bowlingGame.addFrames(framesString);
        int score = bowlingGame.computeScore();

        assertThat(score).isEqualTo(154);
    }

    @Test
    @DisplayName("Play a game with only misses: -- -- -- -- -- -- -- -- -- --- = 0.")
    void playBowlingGameWithScore0() {
        String framesString = "-- -- -- -- -- -- -- -- -- ---";
        bowlingGame.addFrames(framesString);
        int score = bowlingGame.computeScore();

        assertThat(score).isEqualTo(0);
    }

    @Test
    @DisplayName("Play a game with strikes, spares and misses: 2/ X -- 3/ X 14 -3 54 5/ 8/2 = 112.")
    void playBowlingGameWithScore112() {
        String framesString = "2/ X -- 3/ X 14 -3 54 5/ 8/2";
        bowlingGame.addFrames(framesString);
        int score = bowlingGame.computeScore();

        assertThat(score).isEqualTo(112);
    }

    @Test
    @DisplayName("Play an uncompleted game: 2/ X -- 3/ X 14 = 70.")
    void playUncompletedBowlingGameWithScore70() {
        String framesString = "2/ X -- 3/ X 14";
        bowlingGame.addFrames(framesString);
        int score = bowlingGame.computeScore();

        assertThat(score).isEqualTo(70);
    }

    @Test
    @DisplayName("Play an uncompleted game: 2/ X -- 3/ X 14 = 70. Then finish and compute again: 2/ X -- 3/ X 14 -3 54 5/ 8/2 = 112.")
    void playUncompletedBowlingGameWithScore112() {
        String framesString = "2/ X -- 3/ X 14";
        bowlingGame.addFrames(framesString);
        framesString = "-3 54 5/ 8/2";
        bowlingGame.addFrames(framesString);
        int score = bowlingGame.computeScore();

        assertThat(score).isEqualTo(112);
    }

}
