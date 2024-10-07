package org.library.util;

import org.library.exception.InvalidOptionException;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CheckEntry {

    public static int verifyMenuInput(String input, String[] options) {
        boolean validInput = Arrays.stream(options).anyMatch(x -> x.equals(input));

        if (validInput) {
            return Integer.parseInt(input);
        } else {
            throw new InvalidOptionException("Invalid option, please choose a valid option.");
        }
    }
}
