package org.library.service;

import org.library.model.Checkout;
import org.library.repository.Repository;

public class CheckoutService {

    private Repository<Checkout> repository;

    public CheckoutService(Repository<Checkout> repository) {
        this.repository = repository;
    }

    public void bookCheckout(Checkout checkout) {
        repository.insertObj(checkout);
    }

    public Checkout getBookCheckoutById(Long id) {
        return repository.getObjById(Checkout.class, id);
    }
}
