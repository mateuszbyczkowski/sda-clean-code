package exercises.s9.refactoredSwitch;

import pl.sda.refactorapp.entity.Item;
import pl.sda.refactorapp.entity.Order;

import java.util.List;

class FreeDelivery implements Delivery {

    public boolean isDelivered() {
        return false;
    }

    public void calculateCost(List<Item> items, Order order) {
        //calculate free delivery
    }

    public void deliver() {

    }
}