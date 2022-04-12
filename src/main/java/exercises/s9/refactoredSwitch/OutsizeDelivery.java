package exercises.s9.refactoredSwitch;

import pl.sda.refactorapp.entity.Item;
import pl.sda.refactorapp.entity.Order;

import java.util.List;

class OutsizeDelivery implements Delivery {

    public boolean isDelivered() {
        return false;
    }

    public void calculateCost(List<Item> items, Order order) {
        //calculate outsize delivery
    }

    public void deliver() {

    }
}