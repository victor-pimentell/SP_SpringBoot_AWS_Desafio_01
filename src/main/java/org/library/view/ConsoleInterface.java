package org.library.view;

import org.library.controller.AuthorController;
import org.library.controller.BookController;
import org.library.controller.CheckoutController;
import org.library.controller.MemberController;
import org.library.exception.InvalidOptionException;
import org.library.exception.MemberNotFoundException;
import org.library.model.Author;
import org.library.model.Book;
import org.library.model.Checkout;
import org.library.model.Member;
import org.library.model.enums.CheckoutState;
import org.library.util.CheckEntry;
import org.library.util.DateFormat;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleInterface {

    private Scanner sc;

    private AuthorController authorController;
    private BookController bookController;
    private MemberController memberController;
    private CheckoutController checkoutController;

    public ConsoleInterface(Scanner sc) {
        this.sc = sc;
        authorController = new AuthorController();
        bookController = new BookController();
        memberController = new MemberController();
        checkoutController = new CheckoutController();
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

    public void registerMember() {
        System.out.println("==================== Member ====================");
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Address: ");
        String address = sc.nextLine();

        System.out.print("Phone Number: ");
        String phoneNumber = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Association date: ");
        String associationDate = sc.nextLine();

        memberController.registerMember(name, address, phoneNumber, email, DateFormat.getDate(associationDate));
        System.out.println("==================== Member ====================");
    }

    public void makeCheckout() {
        System.out.println("==================== Checkout ====================");

        Member member = null;

        while (member == null) {
            try {
                System.out.print("Type a member's email: ");
                String email = sc.nextLine();
                member = memberController.getMemberByEmail(email);
            } catch (MemberNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Books Available:");
        List<Book> books = bookController.booksAvailable();
        books.forEach(System.out::println);

        Book book = null;

        while (book == null) {
            System.out.print("Please enter a book id: ");
            int id = sc.nextInt();

            book = books.stream().filter(x -> x.getId() == id).findFirst().orElse(null);

            if (book == null) {
                System.out.println("Invalid id, please choose an id from the list: ");
            }
        }

        int option = 0;

        while (option == 0) {
            try {
                System.out.println("Checkout date");
                System.out.println("Would you like to use: ");
                System.out.println("1 - Present date \n2 - Insert a date");
                System.out.print("Select an option: ");
                option = CheckEntry.verifyMenuInput(sc.nextLine(), new String[]{"1", "2"});
            } catch (InvalidOptionException e) {
                System.out.println(e.getMessage());
            }
        }

        LocalDate checkoutDate = null;

        switch (option) {
            case 1:
                checkoutDate = LocalDate.now();
                System.out.println("Checkout date set to " + DateFormat.dateFormat(checkoutDate));
                break;
            case 2:
                System.out.print("Enter checkout date: ");
                checkoutDate = DateFormat.getDate(sc.nextLine());
                System.out.println("Checkout date set to " + DateFormat.dateFormat(checkoutDate));
        }

        System.out.print("Enter checkout due date: ");
        LocalDate dueDate = DateFormat.getDate(sc.nextLine());

        checkoutController.makeCheckout(book, member, checkoutDate, dueDate, CheckoutState.ACTIVE);
        System.out.println("==================== Checkout ====================");
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
