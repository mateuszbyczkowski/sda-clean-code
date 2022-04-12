package exercises.s9.refactoredSwitch;

import pl.sda.refactorapp.entity.Item;
import pl.sda.refactorapp.entity.Order;

import java.util.List;

interface Delivery {
    boolean isDelivered(); //sprawdz czy zostalo dostarczone

    void calculateCost(List<Item> items, Order order);

    void deliver();
}