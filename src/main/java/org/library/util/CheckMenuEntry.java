package org.library.util;

import org.library.exception.InvalidOptionException;

import java.util.Arrays;

public class CheckMenuEntry {

    public static int verifyMenuInput(String input) {
        String[] options = {"0", "1", "2", "3", "4", "5", "6"};

        boolean validInput = Arrays.stream(options).anyMatch(x -> x.equals(input));

        if (validInput) {
            return Integer.parseInt(input);
        } else {
            throw new InvalidOptionException("Invalid option, please choose a valid option.");
        }
    }
}
