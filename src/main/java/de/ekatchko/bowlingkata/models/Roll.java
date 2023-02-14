package de.ekatchko.bowlingkata.models;

public record Roll(int value, char representation, boolean isStrike, boolean isSpare) {
    public Roll(int value, char representation) {
        this(value, representation, false, false);
    }
}
