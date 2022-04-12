package pl.sda.refactorapp.service.payment.strategy;

import pl.sda.refactorapp.entity.Customer;
import pl.sda.refactorapp.entity.Item;
import pl.sda.refactorapp.entity.Order;

import java.math.BigDecimal;

public class PayPalStrategy implements Payment {
    private boolean signedIn = false;

    @Override
    public void pay(Customer customer, Order order) throws Exception {
        //verify is user correct
        if (customer.getEmail().equals("test@test.pl")
                && customer.getPassword().equals("PASSWORD")) {
            setSignedIn(true);
        }

        var totalPrice = BigDecimal.ZERO;
        for (Item item : order.getItems()) {
            totalPrice = totalPrice.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
        }

        var result = pay(order.getDeliveryCost().add(totalPrice));
        if (!result) {
            throw new Exception("Payment failed! Try again later");
        }

    }

    private void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }

    private boolean pay(BigDecimal paymentAmount) {
        if (signedIn) {
            //paying polacz z paypalem i zaplac
            return true;
        } else {
            //not paying
            return false;
        }
    }
}
