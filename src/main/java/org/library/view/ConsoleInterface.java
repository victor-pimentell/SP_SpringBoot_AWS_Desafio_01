package org.library.view;

import org.library.controller.AuthorController;
import org.library.controller.BookController;
import org.library.controller.CheckoutController;
import org.library.controller.MemberController;
import org.library.exception.*;
import org.library.model.Author;
import org.library.model.Book;
import org.library.model.Checkout;
import org.library.model.Member;
import org.library.model.enums.CheckoutState;
import org.library.model.enums.Genre;
import org.library.util.CheckEntry;
import org.library.util.DateFormat;

import java.time.LocalDate;
import java.util.*;

public class ConsoleInterface {

    private final Scanner sc;

    private final AuthorController authorController;
    private final BookController bookController;
    private final MemberController memberController;
    private final CheckoutController checkoutController;

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

    public Optional<Author> registerAuthor() {
        System.out.println("==================== Author ====================");

        System.out.print("Name: ");
        String name = isStringEmpty(sc.nextLine());

        System.out.print("Birth date: ");
        LocalDate birthDate = DateFormat.getDate(sc);

        System.out.print("Nationality: ");
        String nationality = isStringEmpty(sc.nextLine());

        System.out.print("Biography: ");
        String biography = isStringEmpty(sc.nextLine());

        System.out.println("==================== Author ====================");
        Author author;
        try {
            author = authorController.registerAuthor(name, birthDate, nationality, biography);
        } catch (AuthorAlreadyRegisteredException e) {
            System.out.println(e.getMessage());
            return authorController.getAuthorByName(name);
        }
        return Optional.ofNullable(author);
    }

    public void registerBook() {
        System.out.println("==================== Book ====================");
        System.out.print("Title: ");
        String title = isStringEmpty(sc.nextLine());

        System.out.print("Publication Date: ");
        LocalDate publicationDate = DateFormat.getDate(sc);

        System.out.print("ISBN: ");
        String isbn = isStringEmpty(sc.nextLine());

        System.out.print("Quantity: ");
        int quatity = verifyInteger();

        String input = "";
        List<Genre> genres = new ArrayList<>();

        while (!input.equalsIgnoreCase("exit")) {
            try {
                System.out.print(genreMenu());
                input = sc.nextLine();
                genres.add(chooseGenre(input));
            } catch (InvalidOptionException e) {
                System.out.println(e.getMessage());
            }

        }

        Optional<Author> authorOptional = registerAuthor();
        Author author = null;

        if (authorOptional.isPresent()) {
            author = authorOptional.get();
        }

        try {
            bookController.registerBook(title, author, publicationDate, genres, isbn, quatity);
        } catch (BookAlreadyRegisteredException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("==================== Book ====================");
    }

    public void registerMember() {
        System.out.println("==================== Member ====================");
        System.out.print("Name: ");
        String name = isStringEmpty(sc.nextLine());

        System.out.print("Address: ");
        String address = sc.nextLine();

        System.out.print("Phone Number: ");
        String phoneNumber = isStringEmpty(sc.nextLine());

        System.out.print("Email: ");
        String email = isStringEmpty(sc.nextLine());

        System.out.print("Association date: ");
        LocalDate associationDate = DateFormat.getDate(sc);

        try {
            memberController.registerMember(name, address, phoneNumber, email, associationDate);
        } catch (MemberAlreadyRegisteredException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("==================== Member ====================");
    }

    public void bookCheckout() {
        System.out.println("==================== Checkout ====================");

        Member member = null;

        while (member == null) {
            try {
                System.out.print("Type a member's email: ");
                String email = sc.nextLine();
                member = memberController.logMemberByEmail(email);
            } catch (MemberNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        List<Checkout> checkouts = checkoutController.checkoutMemberList(member.getId());
        boolean overdue = checkouts.stream().anyMatch(x -> x.getCheckoutState() == CheckoutState.OVERDUE);
        int checkoutActiveSize = checkouts.stream().filter(x -> x.getCheckoutState() == CheckoutState.ACTIVE).toList().size();

        if (overdue) {
            throw new CheckoutOverdueException("One of the books borrowed has passed the period of return, please return and pay your fine before borrowing a new book.");
        } else if (checkoutActiveSize >= 5) {
            throw new MaxNumberBookBorrowedException("Limit of books borrowed achieved, please return a book before borrowing another.");
        }

        System.out.println("Books Available:");
        List<Book> books = bookController.booksAvailable();
        books.forEach(System.out::println);

        Book book = null;

        while (book == null) {
            System.out.print("Please enter a book id: ");
            int id = sc.nextInt();
            sc.nextLine();

            book = books.stream().filter(x -> x.getId() == id).findFirst().orElse(null);

            if (book == null) {
                System.out.println("Invalid id, please choose an id from the list: ");
            }
        }

        book.setQuantity(book.getQuantity() - 1);
        bookController.updateBook(book);

        LocalDate checkoutDate = LocalDate.now();
        System.out.println("Checkout date set to " + DateFormat.dateFormat(checkoutDate));

        LocalDate dueDate = checkoutDate.plusDays(5);
        System.out.print("Checkout due date set to " + DateFormat.dateFormat(dueDate));

        checkoutController.registerCheckout(book, member, checkoutDate, dueDate, CheckoutState.ACTIVE);
        System.out.println("==================== Checkout ====================");
    }

    public void bookReturn() {
        System.out.println("==================== Return ====================");
        Member member = null;

        while (member == null) {
            try {
                System.out.print("Type a member's email: ");
                String email = sc.nextLine();
                member = memberController.logMemberByEmail(email);
            } catch (MemberNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        List<Checkout> checkouts = checkoutController.checkoutMemberList(member.getId());
        checkouts = checkouts.stream().filter(x -> x.getCheckoutState() == CheckoutState.ACTIVE || x.getCheckoutState() == CheckoutState.OVERDUE).toList();

        System.out.println("Books to return");
        checkouts.forEach(System.out::println);

        Checkout checkout = null;

        while (checkout == null) {
            System.out.print("Please enter a checkout id: ");
            int id = sc.nextInt();
            sc.nextLine();

            checkout = checkouts.stream().filter(x -> x.getId() == id).findFirst().orElse(null);

            if (checkout == null) {
                System.out.println("Invalid id, please choose an id from the list: ");
            }
        }

        if (checkout.getCheckoutState() == CheckoutState.OVERDUE) {
            System.out.printf("The return period has passed, to have access to " +
                    "new books you need to pay a fine of $ %.2f%n", checkout.getFine());


            System.out.println("Press enter to confirm the payment. ");
            sc.nextLine();
            checkout.setCheckoutState(CheckoutState.RETURNED);
            checkout.setDueDate(null);
        } else {
            checkout.setCheckoutState(CheckoutState.RETURNED);
            System.out.println("Thank you for returning the book within the period.");
        }

        Book book = bookController.getBookById(checkout.getBook().getId());
        book.setQuantity(book.getQuantity() + 1);
        bookController.updateBook(book);

        checkoutController.registerCheckout(checkout);
        System.out.println("==================== Return ====================");
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

    private Genre chooseGenre(String id) {

        String[] options = {
                "0", "1", "2", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
                "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
                "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
                "41", "42"
        };

        int input = CheckEntry.verifyMenuInput(id, options);

        return switch (input) {
            case 0 -> Genre.LITERARY_FICTION;
            case 1 -> Genre.HISTORICAL_FICTION;
            case 2 -> Genre.SCIENCE_FICTION;
            case 3 -> Genre.FANTASY;
            case 4 -> Genre.THRILLER;
            case 5 -> Genre.MYSTERY;
            case 6 -> Genre.ROMANCE;
            case 7 -> Genre.HORROR;
            case 8 -> Genre.DYSTOPIAN;
            case 9 -> Genre.ADVENTURE;
            case 10 -> Genre.BIOGRAPHY;
            case 11 -> Genre.AUTOBIOGRAPHY;
            case 12 -> Genre.MEMOIR;
            case 13 -> Genre.SELF_HELP;
            case 14 -> Genre.HISTORY;
            case 15 -> Genre.TRUE_CRIME;
            case 16 -> Genre.SCIENCE;
            case 17 -> Genre.PHILOSOPHY;
            case 18 -> Genre.BUSINESS;
            case 19 -> Genre.PSYCHOLOGY;
            case 20 -> Genre.POETRY;
            case 21 -> Genre.GRAPHIC_NOVEL;
            case 22 -> Genre.YOUNG_ADULT;
            case 23 -> Genre.CHILDRENS_BOOK;
            case 24 -> Genre.RELIGIOUS_SPIRITUAL;
            case 25 -> Genre.TRAVEL;
            case 26 -> Genre.COOKING;
            case 27 -> Genre.ESSAYS;
            case 28 -> Genre.HUMOR;
            case 29 -> Genre.SHORT_STORIES;
            case 30 -> Genre.FICTION;
            case 31 -> Genre.NON_FICTION;
            case 32 -> Genre.DRAMA;
            case 33 -> Genre.CLASSIC;
            case 34 -> Genre.CHILDREN;
            case 35 -> Genre.COMICS;
            case 36 -> Genre.CRIME;
            case 37 -> Genre.RELIGION;
            case 38 -> Genre.ART;
            case 39 -> Genre.GUIDE;
            case 40 -> Genre.HEALTH;
            case 41 -> Genre.MUSIC;
            case 42 -> Genre.SPORTS;
            default -> null;
        };
    }

    private String genreMenu() {
        StringBuilder sc = new StringBuilder();

        sc.append("==================== Genre Menu ====================\n");
        sc.append("0 - Literary Fiction     | 1 - Historical Fiction | 2 - Science Fiction | 3 - Fantasy        | 4 - Thriller     | 5 - Mystery\n");
        sc.append("6 - Romance              | 7 - Horror             | 8 - Dystopian       | 9 - Adventure      | 10 - Biography   | 11 - Autobiography\n");
        sc.append("12 - Memoir              | 13 - Self Help         | 14 - History        | 15 - True Crime    | 16 - Science     | 17 - Philosophy\n");
        sc.append("18 - Business            | 19 - Psychology        | 20 - Poetry         | 21 - Graphic Novel | 22 - Young Adult | 23 - Children's Book\n");
        sc.append("24 - Religious/Spiritual | 25 - Travel            | 26 - Cooking        | 27 - Essays        | 28 - Humor       | 29 - Short Stories\n");
        sc.append("30 - Fiction             | 31 - Non-Fiction       | 32 - Drama          | 33 - Classic       | 34 - Children    | 35 - Comics\n");
        sc.append("36 - Crime               | 37 - Religion          | 38 - Art            | 39 - Guide         | 40 - Health      | 41 - Music\n");
        sc.append("42 - Sports\n");
        sc.append("Choose the genre of the book, in case you already choose all the genres type \"exit\": ");

        return sc.toString();
    }

    private String isStringEmpty(String string) {
        while (string.isBlank()) {
            System.out.print("Please enter a valid entry: ");
            string = sc.nextLine();
        }
        return string;
    }
}
