package pl.sda.refactorapp.service.payment;

import pl.sda.refactorapp.annotation.Service;
import pl.sda.refactorapp.entity.Customer;
import pl.sda.refactorapp.entity.Order;
import pl.sda.refactorapp.entity.PreferredPayment;
import pl.sda.refactorapp.service.payment.strategy.Payment;
import pl.sda.refactorapp.service.payment.strategy.PaymentContext;

@Service
public class PaymentService {
    public void pay(Order order, Customer customer) throws Exception {
        PreferredPayment preferredPayment = customer.getPreferredPayment();

        Payment payment = new PaymentContext().chooseStrategy(preferredPayment);

        payment.pay(customer, order);
    }
}
