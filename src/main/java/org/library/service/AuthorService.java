package org.library.service;

import org.library.exception.AuthorAlreadyRegisteredException;
import org.library.model.Author;
import org.library.repository.Repository;

import java.util.HashSet;
import java.util.Set;

public class AuthorService {

    private Repository<Author> repository;

    public AuthorService() {
        repository = new Repository<>();
    }

    public void registerAuthor(Author author) {
        repository.insertObj(author);
    }

    public Author getAuthorById(Long id) {
        return repository.getObjById(Author.class, id);
    }
}
