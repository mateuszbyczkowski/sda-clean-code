package exercises.s9;

import pl.sda.refactorapp.entity.Order;

// zmiana switch/if na polimorfizm //ZŁAMALIŚMY OPENED-CLOSED PRINCIPLE
class DeliveryCostCalculator {
    private void calculate(Order order, DeliveryType deliveryType) {
        switch(deliveryType) {
            case OUTSIZE:
                calculateOutsizeDelivery(order);
                break;
            case FREE:
                calculateFreeDelivery(order);
                break;
            case GLASS:
                calculateGlassDelivery(order);
                break;
            case NORMAL:
                calculateNormalDelivery(order);
                break;
            case FANCY:
                calculateFancyDelivery(order);
                break;
            default:
                throw new IllegalStateException("Idk");
        }
    }

    private void calculateFancyDelivery(Order order) {
        //calculate fancy delivery
    }

    private void calculateNormalDelivery(Order order) {
        //calculate normal delivery
    }

    private void calculateGlassDelivery(Order order) {
        //calculate glass delivery
    }

    private void calculateFreeDelivery(Order order) {
        //calculate free delivery
    }

    private void calculateOutsizeDelivery(Order order) {
        //calculate outsize delivery
    }
}

enum DeliveryType {
    OUTSIZE, NORMAL, FREE, GLASS, FANCY
}