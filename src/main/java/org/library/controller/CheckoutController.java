package org.library.controller;

import org.library.model.Book;
import org.library.model.Checkout;
import org.library.model.Member;
import org.library.model.enums.CheckoutState;
import org.library.service.CheckoutService;

import java.time.LocalDate;
import java.util.List;

public class CheckoutController {

    private CheckoutService checkoutService;

    public CheckoutController() {
        checkoutService = new CheckoutService();
    }

    public void registerCheckout(Book book, Member member, LocalDate checkoutDate, LocalDate dueDate, CheckoutState checkoutState) {
        checkoutService.makeCheckout(new Checkout(book, member, checkoutDate, dueDate, checkoutState));
    }

    public void registerCheckout(Checkout checkout) {
        checkoutService.makeCheckout(checkout);
    }

    public List<Checkout> checkoutMemberList(Long id) {
        return checkoutService.checkoutMemberList(id);
    }
}
