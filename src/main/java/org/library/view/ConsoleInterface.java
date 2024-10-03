package org.library.view;

import org.library.exception.InvalidOptionException;

import java.util.Arrays;

public class ConsoleInterface {

    public static String mainMenu() {
        StringBuilder sb = new StringBuilder();

        sb.append("==================== Library System ====================\n");
        sb.append("1 - Register book\n");
        sb.append("2 - Register author\n");
        sb.append("3 - Register member\n");
        sb.append("4 - Borrow a book\n");
        sb.append("5 - Return a book\n");
        sb.append("6 - Report\n");
        sb.append("0 - Close application\n");
        sb.append("==================== Library System ====================\n");
        sb.append("Select an option: ");

        return sb.toString();
    }

    public void registerBook() {

    }

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
