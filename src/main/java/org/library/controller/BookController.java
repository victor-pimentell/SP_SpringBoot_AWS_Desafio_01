package org.library.controller;

import org.library.model.Author;
import org.library.model.Book;
import org.library.model.enums.Genre;
import org.library.service.BookService;

import java.time.LocalDate;
import java.util.List;

public class BookController {

    private BookService bookService;

    public BookController() {
        bookService = new BookService();
    }

    public void registerBook(String title, Author author, LocalDate publicationDate, List<Genre> genres, String isbn, int quatity) {
        Book book = new Book(title, author, publicationDate, isbn, genres, quatity);
        bookService.registerBook(book);
    }

    public void registerBook(Book book) {
        bookService.registerBook(book);
    }

    public List<Book> booksAvailable() {
        return bookService.booksAvailable();
    }

    public Book getBookById(Long id) {
        return bookService.getBookById(id);
    }

    public void updateBook(Book book) {
        bookService.updateBook(book);
    }
}
