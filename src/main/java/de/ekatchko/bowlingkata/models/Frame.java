package de.ekatchko.bowlingkata.models;

import java.util.HashSet;

public class Frame {
    private static final HashSet<Character> allowedRolls = new HashSet<>();

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

    private final Roll[] rolls;

    public Frame(String rolls) {
        this.rolls = new Roll[rolls.length()];
        for (int i = 0; i < rolls.length(); i++) {
            if (allowedRolls.contains(rolls.charAt(i))) {
                switch (rolls.charAt(i)) {
                    case 'X' -> this.rolls[i] = new Roll(10, 'X', true, false);
                    case '/' -> this.rolls[i] = new Roll((10 - this.rolls[i-1].value()), '/', false, true);
                    case '-' -> this.rolls[i] = new Roll(0, '-');
                    default -> this.rolls[i] = new Roll(Character.getNumericValue(rolls.charAt(i)), rolls.charAt(i));
                }
            } else {
                this.rolls[i] = new Roll(0, '-');
            }
        }
    }

    public Roll[] getRolls() {
        return this.rolls;
    }
}
