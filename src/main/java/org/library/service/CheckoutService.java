package org.library.service;

import org.library.model.Book;
import org.library.model.Checkout;
import org.library.repository.Repository;

import java.util.List;

public class CheckoutService {

    private Repository<Checkout> repository;

    public CheckoutService() {
        repository = new Repository<>();
    }

    public void bookCheckout(Checkout checkout) {
        repository.insertObj(checkout);
    }

    public Checkout getBookCheckoutById(Long id) {
        return repository.getObjById(Checkout.class, id);
    }

    public void makeCheckout(Checkout checkout) {
        repository.insertObj(checkout);
    }
}
