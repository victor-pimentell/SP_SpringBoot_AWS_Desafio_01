package org.library.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Member extends Person {

    private String address;

    private String phoneNumber;

    private String email;

    private LocalDate dateAssociation;

    @OneToMany(mappedBy = "member")
    private List<Checkout> checkouts;

    public Member() {
    }

    public Member(String name, String address, String phoneNumber, String email, LocalDate dateAssociation) {
        super(name);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateAssociation = dateAssociation;
        checkouts = new ArrayList<>();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateAssociation() {
        return dateAssociation;
    }

    public void setDateAssociation(LocalDate dateAssociation) {
        this.dateAssociation = dateAssociation;
    }

    public List<Checkout> getCheckouts() {
        return checkouts;
    }

    public void addCheckouts(Checkout checkout) {
        checkouts.add(checkout);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Member member = (Member) o;
        return Objects.equals(address, member.address) && Objects.equals(phoneNumber, member.phoneNumber) && Objects.equals(email, member.email) && Objects.equals(dateAssociation, member.dateAssociation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address, phoneNumber, email, dateAssociation);
    }
}
