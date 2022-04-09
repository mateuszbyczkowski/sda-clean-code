package exercises.s9;

import pl.sda.refactorapp.entity.Item;
import pl.sda.refactorapp.entity.Order;

import java.util.List;

// zmiana switch/if na polimorfizm
class DeliveryCostCalculator {
    private void calculate(List<Item> items, Order order, DeliveryType deliveryType) {
        switch(deliveryType) {
            case OUTSIZE:
                calculateOutsizeDelivery(items, order);
                break;
            case FREE:
                calculateFreeDelivery(items,order);
                break;
            case GLASS:
                calculateGlassDelivery(items, order);
                break;
            case NORMAL:
                calculateNormalDelivery(items, order);
                break;
            default:
                throw new IllegalStateException("Idk");
        }
    }

    private void calculateNormalDelivery(List<Item> items, Order order) {
        //calculate normal delivery
    }

    private void calculateGlassDelivery(List<Item> items, Order order) {
        //calculate glass delivery
    }

    private void calculateFreeDelivery(List<Item> items, Order order) {
        //calculate free delivery
    }

    private void calculateOutsizeDelivery(List<Item> items, Order order) {
        //calculate outsize delivery
    }
}

enum DeliveryType {
    OUTSIZE, NORMAL, FREE, GLASS;
}