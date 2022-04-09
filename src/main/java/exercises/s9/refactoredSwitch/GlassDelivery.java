package exercises.s9.refactoredSwitch;

import pl.sda.refactorapp.entity.Item;
import pl.sda.refactorapp.entity.Order;

import java.util.List;

class GlassDelivery extends Delivery {

    @Override
    boolean isDelivered() {
        return false;
    }

    @Override
    void calculateCost(List<Item> items, Order order) {
        //calculate glass delivery
    }

    @Override
    void deliver() {

    }
}