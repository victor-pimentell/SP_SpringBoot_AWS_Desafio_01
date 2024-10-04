package org.library.controller;

import org.library.model.Author;
import org.library.model.Book;
import org.library.service.BookService;

import java.time.LocalDate;

public class BookController {

    private BookService bookService;

    public BookController() {
        bookService = new BookService();
    }

    public void registerBook(String title, Author author, LocalDate publicationDate, String isbn, int quatity) {
        Book book = new Book(title, author, publicationDate, isbn, quatity);
        bookService.registerBook(book);
    }
}
