package org.library.service;

import org.library.model.Checkout;
import org.library.model.enums.CheckoutState;
import org.library.repository.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

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

    public List<Checkout> checkoutMemberList(Long id) {
        List<Checkout> checkouts = repository
                .getAll(Checkout.class)
                .stream()
                .filter(x -> Objects.equals(x.getMember().getId(), id))
                .toList();

        return calculateFine(checkouts);
    }

    private List<Checkout> calculateFine(List<Checkout> checkouts) {
        for (Checkout checkout : checkouts) {
            LocalDate dueDate = checkout.getDueDate();

            if (dueDate != null) {
                long days = ChronoUnit.DAYS.between(dueDate, LocalDate.now());

                if (days > 0) {
                    BigDecimal fine = BigDecimal.valueOf((days - 5) * 2.0);

                    checkout.setFine(fine);
                    checkout.setCheckoutState(CheckoutState.OVERDUE);
                    makeCheckout(checkout);
                }
            }
        }
        return checkouts;
    }
}
