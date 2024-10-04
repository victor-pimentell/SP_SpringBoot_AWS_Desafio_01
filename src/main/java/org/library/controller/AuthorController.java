package org.library.controller;

import org.library.model.Author;
import org.library.service.AuthorService;

import java.time.LocalDate;

public class AuthorController {

    private AuthorService authorService;

    public AuthorController() {
        authorService = new AuthorService();
    }

    public void registerAuthor(String name, LocalDate bithDate, String nationality, String biography) {
        Author author = new Author(name, bithDate, nationality, biography);
        authorService.registerAuthor(author);
    }
}
