package org.library.service;

import org.library.model.Book;
import org.library.repository.Repository;

import java.util.List;

public class BookService {

    private Repository<Book> repository;

    public BookService() {
        repository = new Repository<>();
    }

    public void registerBook(Book book) {
        repository.insertObj(book);
    }

    public Book getBookById(Long id) {
        return repository.getObjById(Book.class, id);
    }

    public List<Book> booksAvailable() {
        return repository.getAllAvailable(Book.class);
    }
}
