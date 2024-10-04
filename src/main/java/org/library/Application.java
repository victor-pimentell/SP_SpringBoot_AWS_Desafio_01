package org.library;

import org.library.exception.AuthorAlreadyRegisteredException;
import org.library.exception.InvalidOptionException;
import org.library.util.CheckMenuEntry;
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
                int input = CheckMenuEntry.verifyMenuInput(sc.nextLine());
                switch (input) {
                    case 1:
                        consoleInterface.registerAuthor();
                        break;
                    case 2:
                        appCycle = false;
                        break;
                    case 3:
                        appCycle = false;
                        break;
                    case 4:
                        appCycle = false;
                        break;
                    case 5:
                        appCycle = false;
                        break;
                    case 6:
                        appCycle = false;
                        break;
                    case 0:
                        appCycle = false;
                        break;
                }
            } catch (InvalidOptionException e) {
                System.out.println(e.getMessage());
            } catch (AuthorAlreadyRegisteredException e) {
                System.out.println(e.getMessage());
            }

        }
        sc.close();
    }
}