package org.library.model;

import org.library.model.enums.Genre;
import org.library.util.DateFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    private LocalDate publicationDate;

    private String isbn;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Genre.class)
    private List<Genre> genres;

    private Integer quantity;

    public Book() {
    }

    public Book(String title, Author author, LocalDate publicationDate, String isbn, List<Genre> genres, Integer quantity) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
        this.genres = genres;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void addGenres(Genre genre) {
        genres.add(genre);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(publicationDate, book.publicationDate) && Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, publicationDate, isbn);
    }

    @Override
    public String toString() {
        return "BOOK ID: " + id
                + " | Title: " + title
                + " | Author: " + author.getName()
                + " | Publication Date: " + DateFormat.dateFormat(publicationDate)
                + " | ISBN: " + isbn
                + " | Genres: " + genres
                + " | Quantity: " + quantity;
    }
}
