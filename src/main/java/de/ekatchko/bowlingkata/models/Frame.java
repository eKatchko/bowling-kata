package de.ekatchko.bowlingkata.models;

import java.util.ArrayList;
import java.util.HashSet;

public class Frame {
    private final ArrayList<Roll> rolls = new ArrayList<>();
    private final boolean lastFrame;
    private boolean isCompleted = false;

    public Frame(boolean lastFrame) {
        this.lastFrame = lastFrame;
    }

    public ArrayList<Roll> getRolls() {
        return this.rolls;
    }

    public void addRoll(int pins) {
        Roll defaultRoll = new Roll(pins, Character.forDigit(pins, 10));
        if (!this.lastFrame) {
            addRollToFrame(pins, defaultRoll);
        } else {
            addRollToLastFrame(pins, defaultRoll);
        }
    }

    private void addRollToFrame(int pins, Roll defaultRoll) {
        if (this.rolls.isEmpty()) {
            switch (pins) {
                case 10 -> {
                    isCompleted = true;
                    rolls.add(new Roll(10, 'X', true, false));
                }
                case 0 -> rolls.add(new Roll(0, '-'));
                default -> rolls.add(defaultRoll);
            }
        } else {
            isCompleted = true;
            switch (pins) {
                case 10 -> rolls.add(new Roll(10, '/', false, true));
                case 0 -> rolls.add(new Roll(0, '-'));
                default -> {
                    if (rolls.get(0).value() + pins == 10) {
                        rolls.add(new Roll(pins, '/', false, true));
                    } else {
                        rolls.add(defaultRoll);
                    }
                }
            }
        }
    }

    private void addRollToLastFrame(int pins, Roll defaultRoll) {
        if (rolls.isEmpty()) {
            switch (pins) {
                case 10 -> rolls.add(new Roll(10, 'X', true, false));
                case 0 -> rolls.add(new Roll(0, '-'));
                default -> rolls.add(defaultRoll);
            }
        } else if (rolls.size() == 1) {
            switch (pins) {
                case 10 -> {
                    if (rolls.get(0).isStrike()) {
                        rolls.add(new Roll(10, 'X', true, false));
                    } else {
                        rolls.add(new Roll(10, '/', false, true));
                    }
                }
                case 0 -> rolls.add(new Roll(0, '-'));
                default -> {
                    if (rolls.get(0).value() + pins == 10) {
                        rolls.add(new Roll(pins, '/', false, true));
                    } else {
                        rolls.add(defaultRoll);
                    }
                }
            }
        } else {
            isCompleted = true;
            switch (pins) {
                case 10 -> rolls.add(new Roll(10, 'X', true, false));
                case 0 -> rolls.add(new Roll(0, '-'));
                default -> rolls.add(defaultRoll);
            }
        }
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    @Override
    public String toString() {
        StringBuilder rollsStringBuilder = new StringBuilder();
        for (Roll roll: rolls) {
            rollsStringBuilder.append(roll.representation());
        }
        return rollsStringBuilder.toString();
    }
}
