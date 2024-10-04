package org.library.view;

import org.library.controller.AuthorController;
import org.library.controller.BookController;
import org.library.model.Author;
import org.library.util.CheckEntry;
import org.library.util.DateFormat;

import java.util.InputMismatchException;
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

    public Author registerAuthor() {
        AuthorController authorController = new AuthorController();

        System.out.println("==================== Author ====================");
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Birth date: ");
        String birthDate = sc.nextLine();

        System.out.print("Nationality: ");
        String nationality = sc.nextLine();

        System.out.print("Biography: ");
        String biography = sc.nextLine();

        System.out.println("==================== Author ====================");
        return authorController.registerAuthor(name, DateFormat.getDate(birthDate), nationality, biography);
    }

    public void registerBook() {
        BookController bookController = new BookController();

        System.out.println("==================== Book ====================");
        System.out.print("Title: ");
        String title = sc.nextLine();

        System.out.print("Publication Date: ");
        String publicationDate = sc.nextLine();

        System.out.print("ISBN: ");
        String isbn = sc.nextLine();

        System.out.print("Quantity: ");
        int quatity = verifyInteger();

        Author author = registerAuthor();

        bookController.registerBook(title, author, DateFormat.getDate(publicationDate), isbn, quatity);
        System.out.println("==================== Book ====================");
    }

    private int verifyInteger() {
        int number = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                number = sc.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, try again.");
                System.out.print("Quantity: ");
                sc.next();
            } finally {
                sc.nextLine();
            }
        }
        return number;
    }
}
