package exercises.s9.refactoredSwitch;

import pl.sda.refactorapp.entity.Item;
import pl.sda.refactorapp.entity.Order;

import java.util.List;

abstract class Delivery {
    abstract boolean isDelivered(); //sprawdz czy zostalo dostarczone

    abstract void calculateCost(List<Item> items, Order order);

    abstract void deliver();
}