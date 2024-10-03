package org.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Author extends Person {

    private LocalDate birthDate;

    private String nationality;

    @Column(length=1000)
    private String biography;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public Author() {
    }

    public Author(String name, LocalDate birthDate, String nationality, String biography) {
        super(name);
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.biography = biography;
        books = new ArrayList<>();
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBooks(Book book) {
        books.add(book);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Author author = (Author) o;
        return Objects.equals(birthDate, author.birthDate) && Objects.equals(nationality, author.nationality) && Objects.equals(biography, author.biography);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), birthDate, nationality, biography);
    }
}
