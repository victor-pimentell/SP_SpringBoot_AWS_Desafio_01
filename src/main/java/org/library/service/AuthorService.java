package org.library.service;

import org.library.model.Author;
import org.library.repository.Repository;

public class AuthorService {

    private Repository<Author> repository;

    public AuthorService(Repository<Author> repository) {
        this.repository = repository;
    }

    public void registerAuthor(Author author) {
        repository.insertObj(author);
    }

    public Author getAuthorById(Long id) {
        return repository.getObjById(Author.class, id);
    }
}
