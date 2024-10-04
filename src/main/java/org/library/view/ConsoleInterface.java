package org.library.view;

import org.library.controller.AuthorController;
import org.library.util.DateFormat;

import java.util.Scanner;

public class ConsoleInterface {

    private Scanner sc;

    public ConsoleInterface(Scanner sc) {
        this.sc = sc;
    }

    public String mainMenu() {
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

    public void registerAuthor() {
        AuthorController authorController = new AuthorController();

        System.out.println("==================== Author ====================");
        System.out.print("Type a name: ");
        String name = sc.nextLine();

        System.out.print("Type a birth date: ");
        String birthDate = sc.nextLine();

        System.out.print("Type a nationality: ");
        String nationality = sc.nextLine();

        System.out.print("Type a biography: ");
        String biography = sc.nextLine();

        authorController.registerAuthor(name, DateFormat.getDate(birthDate), nationality, biography);
    }
}
