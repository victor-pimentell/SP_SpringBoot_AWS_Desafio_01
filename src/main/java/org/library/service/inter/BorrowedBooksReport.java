package org.library.service.inter;

import org.library.model.Checkout;
import org.library.repository.Repository;
import org.library.util.DateFormat;

import java.util.List;

public class BorrowedBooksReport implements Report {

    private Repository<Checkout> checkoutRepository;

    public BorrowedBooksReport() {
        checkoutRepository = new Repository<>();
    }

    @Override
    public void generateReport() {
        List<Checkout> checkouts = checkoutRepository.getAll(Checkout.class);
        System.out.println("==================== Borrowed Books ====================");
        for (Checkout checkout : checkouts) {
            System.out.println("ID: " + checkout.getId()
                    + " | Title: " + checkout.getBook().getTitle()
                    + " | Author: " + checkout.getBook().getAuthor().getName()
                    + " | Member: " + checkout.getMember().getName()
                    + " | Checkout date: " + DateFormat.dateFormat(checkout.getCheckoutDate())
                    + " | Return date: " + DateFormat.dateFormat(checkout.getDueDate()) + "\n"
            );
        }
        System.out.println("==================== Borrowed Books ====================");
    }
}
