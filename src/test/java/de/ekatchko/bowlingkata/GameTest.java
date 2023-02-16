package de.ekatchko.bowlingkata;

import de.ekatchko.bowlingkata.models.Roll;
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
        for (int i = 0; i < 20; i++) {
            bowlingGame.addRoll(0);
        }
        ArrayList<Frame> frames = bowlingGame.getFrames();

        assertThat(frames.size()).isEqualTo(10);
    }

    @Test
    @DisplayName("Initialize a bowling game with 5 frames.")
    void initializeBowlingGameWith5Frames() {
        for (int i = 0; i < 10; i++) {
            bowlingGame.addRoll(0);
        }
        ArrayList<Frame> frames = bowlingGame.getFrames();

        assertThat(frames.size()).isEqualTo(5);
    }

    @Test
    @DisplayName("Initialize a bowling game with 5 frames and extend by 6 more frames. Expect 10.")
    void initializeBowlingGameWith5FramesAndExtend() {
        for (int i = 0; i < 10; i++) {
            bowlingGame.addRoll(0);
        }
        for (int i = 0; i < 10; i++) {
            bowlingGame.addRoll(0);
        }
        ArrayList<Frame> frames = bowlingGame.getFrames();

        assertThat(frames.size()).isEqualTo(10);
    }

    @Test
    @DisplayName("Initialize a bowling game with 10 frames and test for correct characters.")
    void initializeBowlingGameAndTestFramesCharacters() {
        bowlingGame.addRoll(0);
        bowlingGame.addRoll(2);

        bowlingGame.addRoll(3);
        bowlingGame.addRoll(4);

        bowlingGame.addRoll(5);
        bowlingGame.addRoll(5);

        bowlingGame.addRoll(10);

        bowlingGame.addRoll(6);
        bowlingGame.addRoll(0);

        bowlingGame.addRoll(0);
        bowlingGame.addRoll(7);

        bowlingGame.addRoll(8);
        bowlingGame.addRoll(2);

        bowlingGame.addRoll(9);
        bowlingGame.addRoll(1);

        bowlingGame.addRoll(0);
        bowlingGame.addRoll(0);

        bowlingGame.addRoll(10);
        bowlingGame.addRoll(10);
        bowlingGame.addRoll(1);

        ArrayList<Frame> frames = bowlingGame.getFrames();

        assertThat(frames).satisfiesExactly(
                frame1 -> assertThat(frame1.getRolls().toArray()).isEqualTo(
                        new Roll[]{new Roll(0, '-'), new Roll(2, '2')}
                ),
                frame2 -> assertThat(frame2.getRolls().toArray()).isEqualTo(
                        new Roll[]{new Roll(3, '3'), new Roll(4, '4')}
                ),
                frame3 -> assertThat(frame3.getRolls().toArray()).isEqualTo(
                        new Roll[]{new Roll(5, '5'), new Roll(5, '/', false, true)}
                ),
                frame4 -> assertThat(frame4.getRolls().toArray()).isEqualTo(
                        new Roll[]{new Roll(10, 'X', true, false)}
                ),
                frame5 -> assertThat(frame5.getRolls().toArray()).isEqualTo(
                        new Roll[]{new Roll(6, '6'), new Roll(0, '-')}
                ),
                frame6 -> assertThat(frame6.getRolls().toArray()).isEqualTo(
                        new Roll[]{new Roll(0, '-'), new Roll(7, '7')}
                ),
                frame7 -> assertThat(frame7.getRolls().toArray()).isEqualTo(
                        new Roll[]{new Roll(8, '8'), new Roll(2, '/', false, true)}
                ),
                frame8 -> assertThat(frame8.getRolls().toArray()).isEqualTo(
                        new Roll[]{new Roll(9, '9'), new Roll(1, '/', false, true)}
                        ),
                frame9 -> assertThat(frame9.getRolls().toArray()).isEqualTo(
                        new Roll[]{new Roll(0, '-'), new Roll(0, '-')}
                ),
                frame10 -> assertThat(frame10.getRolls().toArray()).isEqualTo(
                        new Roll[]{
                                new Roll(10, 'X', true, false),
                                new Roll(10, 'X', true, false),
                                new Roll(1, '1')
                        }
                )
                );
    }

    @Test
    @DisplayName("Play a game with: no spare, no strike, no miss. 23 23 23 23 23 23 23 23 23 23- = 50.")
    void playBowlingGameWithScore50() {
        for (int i = 0; i < 10; i++) {
            bowlingGame.addRoll(2);
            bowlingGame.addRoll(3);
        }
        int score = bowlingGame.computeScore();

        assertThat(score).isEqualTo(50);
    }

    @Test
    @DisplayName("Play a game with only strikes: X X X X X X X X X XXX = 300.")
    void playBowlingGameWithScore300() {
        for (int i = 0; i < 12; i++) {
            bowlingGame.addRoll(10);
        }
        int score = bowlingGame.computeScore();

        assertThat(score).isEqualTo(300);
    }

    @Test
    @DisplayName("Play a game with only spares: 4/ 4/ 4/ 4/ 4/ 4/ 4/ 4/ 4/ 5/5 = 142.")
    void playBowlingGameWithScore142() {
        for (int i = 0; i < 9; i++) {
            bowlingGame.addRoll(4);
            bowlingGame.addRoll(6);
        }
        bowlingGame.addRoll(5);
        bowlingGame.addRoll(5);
        bowlingGame.addRoll(5);
        int score = bowlingGame.computeScore();

        assertThat(score).isEqualTo(142);
    }

    @Test
    @DisplayName("Play a game with only misses: -- -- -- -- -- -- -- -- -- --- = 0.")
    void playBowlingGameWithScore0() {
        for (int i = 0; i < 20; i++) {
            bowlingGame.addRoll(0);
        }
        int score = bowlingGame.computeScore();

        assertThat(score).isEqualTo(0);
    }

    @Test
    @DisplayName("Play a game with strikes, spares and misses: 2/ X -- 3/ X 14 -3 54 5/ 8/2 = 112.")
    void playBowlingGameWithScore112() {
        bowlingGame.addRoll(2);
        bowlingGame.addRoll(8);

        bowlingGame.addRoll(10);

        bowlingGame.addRoll(0);
        bowlingGame.addRoll(0);

        bowlingGame.addRoll(3);
        bowlingGame.addRoll(7);

        bowlingGame.addRoll(10);

        bowlingGame.addRoll(1);
        bowlingGame.addRoll(4);

        bowlingGame.addRoll(0);
        bowlingGame.addRoll(3);

        bowlingGame.addRoll(5);
        bowlingGame.addRoll(4);

        bowlingGame.addRoll(5);
        bowlingGame.addRoll(5);

        bowlingGame.addRoll(8);
        bowlingGame.addRoll(2);
        bowlingGame.addRoll(2);

        int score = bowlingGame.computeScore();

        assertThat(score).isEqualTo(112);
    }

    @Test
    @DisplayName("Play an uncompleted game: 2/ X -- 3/ X 14 = 70.")
    void playUncompletedBowlingGameWithScore70() {
        bowlingGame.addRoll(2);
        bowlingGame.addRoll(8);

        bowlingGame.addRoll(10);

        bowlingGame.addRoll(0);
        bowlingGame.addRoll(0);

        bowlingGame.addRoll(3);
        bowlingGame.addRoll(7);

        bowlingGame.addRoll(10);

        bowlingGame.addRoll(1);
        bowlingGame.addRoll(4);

        int score = bowlingGame.computeScore();

        assertThat(score).isEqualTo(70);
    }


}
