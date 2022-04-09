package exercises.s9.refactoredSwitch;

import pl.sda.refactorapp.entity.Item;
import pl.sda.refactorapp.entity.Order;

import java.util.List;

class NormalDelivery extends Delivery {

    @Override
    boolean isDelivered() {
        return false;
    }

    @Override
    void calculateCost(List<Item> items, Order order) {
        //calculate normal delivery
    }

    @Override
    void deliver() {

    }
}