package org.library;

import org.library.exception.AuthorAlreadyRegisteredException;
import org.library.exception.CheckoutOverdueException;
import org.library.exception.InvalidOptionException;
import org.library.exception.MaxNumberBookBorrowedException;
import org.library.util.CheckEntry;
import org.library.view.ConsoleInterface;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ConsoleInterface consoleInterface = new ConsoleInterface(sc);

        boolean appCycle = true;

        while (appCycle) {
            try {
                System.out.print(consoleInterface.mainMenu());
                int input = CheckEntry.verifyMenuInput(sc.nextLine(), new String[]{"0", "1", "2", "3", "4", "5", "6"});
                switch (input) {
                    case 1:
                        consoleInterface.registerBook();
                        break;
                    case 2:
                        consoleInterface.registerAuthor();
                        break;
                    case 3:
                        consoleInterface.registerMember();
                        break;
                    case 4:
                        consoleInterface.bookCheckout();
                        break;
                    case 5:
                        consoleInterface.bookReturn();
                        break;
                    case 6:
                        appCycle = false;
                        break;
                    case 0:
                        appCycle = false;
                        break;
                }
            } catch (InvalidOptionException | AuthorAlreadyRegisteredException | MaxNumberBookBorrowedException |
                     CheckoutOverdueException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }
}