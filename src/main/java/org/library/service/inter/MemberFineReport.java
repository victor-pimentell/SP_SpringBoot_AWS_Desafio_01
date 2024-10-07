package org.library.service.inter;

import org.library.model.Checkout;
import org.library.model.enums.CheckoutState;
import org.library.repository.Repository;
import org.library.service.CheckoutService;
import org.library.util.DateFormat;

import java.util.List;

public class MemberFineReport implements Report {

    private Repository<Checkout> checkoutRepository;
    private CheckoutService checkoutService;

    public MemberFineReport() {
        checkoutRepository = new Repository<>();
        checkoutService = new CheckoutService();
    }

    @Override
    public void generateReport() {
        List<Checkout> checkouts = checkoutService.calculateFine(checkoutRepository.getAll(Checkout.class));

        List<Checkout> overdueCheckouts = checkouts.stream().filter(x -> x.getCheckoutState() == CheckoutState.OVERDUE).toList();

        System.out.println("========================================  Members Fine  ========================================");
        for (Checkout checkout : overdueCheckouts) {
            System.out.println("ID: " + checkout.getId()
                    + " | Title: " + checkout.getBook().getTitle()
                    + " | Member: " + checkout.getMember().getName()
                    + " | Checkout State: " + checkout.getCheckoutState()
                    + " | Fine: $ " + String.format("%.2f", checkout.getFine())
                    + " | Checkout date: " + DateFormat.dateFormat(checkout.getCheckoutDate())
                    + " | Return date: " + DateFormat.dateFormat(checkout.getDueDate()) + "\n"
            );
        }
        System.out.println("========================================  Members Fine  ========================================");
    }
}
