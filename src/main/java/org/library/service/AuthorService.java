package org.library.service;

import org.library.exception.AuthorAlreadyRegisteredException;
import org.library.model.Author;
import org.library.repository.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AuthorService {

    private Repository<Author> repository;

    public AuthorService() {
        repository = new Repository<>();
    }

    public void registerAuthor(Author author) {
        Optional<Author> authorDataBase = getAuthorByName(author.getName());

        if (authorDataBase.isPresent()) {
            if (authorDataBase.get().getName().equalsIgnoreCase(author.getName())) {
                throw new AuthorAlreadyRegisteredException("This author is already registered.");
            }
        }
        repository.insertObj(author);
    }

    public Author getAuthorById(Long id) {
        return repository.getObjById(Author.class, id);
    }

    public Optional<Author> getAuthorByName(String name) {
        return repository.getObjByName(Author.class, name);
    }
}
