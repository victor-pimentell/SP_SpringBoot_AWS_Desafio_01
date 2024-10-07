package org.library.service;

import org.library.exception.BookAlreadyRegisteredException;
import org.library.model.Book;
import org.library.repository.Repository;

import java.util.List;
import java.util.Optional;

public class BookService {

    private Repository<Book> repository;

    public BookService() {
        repository = new Repository<>();
    }

    public void registerBook(Book book) {
        Optional<Book> bookDataBase = getBookByIsbn(book.getIsbn());

        if (bookDataBase.isPresent()) {
            if (bookDataBase.get().getIsbn().equalsIgnoreCase(book.getIsbn())) {
                throw new BookAlreadyRegisteredException("This book is already registered.");
            }
        }

        repository.insertObj(book);
    }

    public void updateBook(Book book) {
        repository.insertObj(book);
    }

    public Book getBookById(Long id) {
        return repository.getObjById(Book.class, id);
    }

    public List<Book> booksAvailable() {
        return repository.getAllAvailable(Book.class);
    }

    public Optional<Book> getBookByIsbn(String isbn) {
        return repository.getObjByIsbn(Book.class, isbn);
    }
}
