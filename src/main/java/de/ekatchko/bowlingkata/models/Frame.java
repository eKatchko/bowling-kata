package de.ekatchko.bowlingkata.models;

import java.util.HashSet;

public class Frame {
    private static final HashSet<Character> allowedRolls = new HashSet<Character>();

    static{
        allowedRolls.add('1');
        allowedRolls.add('2');
        allowedRolls.add('3');
        allowedRolls.add('4');
        allowedRolls.add('5');
        allowedRolls.add('6');
        allowedRolls.add('7');
        allowedRolls.add('8');
        allowedRolls.add('9');
        allowedRolls.add('X');
        allowedRolls.add('/');
        allowedRolls.add('-');
    }

    private char[] rolls;

    public Frame(String rolls) {
        this.rolls = new char[rolls.length()];
        for (int i = 0; i < rolls.length(); i++) {
            if (allowedRolls.contains(rolls.charAt(i))) {
                this.rolls[i] = rolls.charAt(i);
            } else {
                this.rolls[i] = '-';
            }
        }
    }
}
