package org.library.model;

import org.library.model.enums.CheckoutState;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Checkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDate checkoutDate;

    private LocalDate dueDate;

    private CheckoutState checkoutState;

    private BigDecimal fine;

    public Checkout() {
    }

    public Checkout(Book book, Member member, LocalDate checkoutDate, LocalDate dueDate, CheckoutState checkoutState) {
        this.book = book;
        this.member = member;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.checkoutState = checkoutState;
    }

    public Long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public CheckoutState getCheckoutState() {
        return checkoutState;
    }

    public void setCheckoutState(CheckoutState checkoutState) {
        this.checkoutState = checkoutState;
    }

    public BigDecimal getFine() {
        return fine;
    }

    public void setFine(BigDecimal fine) {
        this.fine = fine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Checkout checkout = (Checkout) o;
        return Objects.equals(id, checkout.id) && Objects.equals(book, checkout.book) && Objects.equals(member, checkout.member) && Objects.equals(checkoutDate, checkout.checkoutDate) && Objects.equals(dueDate, checkout.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, member, checkoutDate, dueDate);
    }

    @Override
    public String toString() {
        return "Checkout{" +
                "id=" + id +
                ", book=" + book +
                ", member=" + member +
                ", checkoutDate=" + checkoutDate +
                ", dueDate=" + dueDate +
                ", checkoutState=" + checkoutState +
                ", fine=" + fine +
                '}';
    }
}
