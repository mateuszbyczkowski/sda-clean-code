package pl.sda.refactorapp.service.payment.strategy;

import pl.sda.refactorapp.entity.Customer;
import pl.sda.refactorapp.entity.Order;

public interface Payment {
    void pay(Customer customer, Order order) throws Exception;
}
