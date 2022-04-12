package exercises.s9.refactoredSwitch;

import pl.sda.refactorapp.annotation.Autowired;
import pl.sda.refactorapp.entity.Item;
import pl.sda.refactorapp.entity.Order;

import java.util.List;

// jeden powód do zmiany (nie narusza zasady SRP)
// brak obawy nadmiernego wzrostu
// jedna odpowiedzialność
// spełnia zasadę OCP - otwarty zamknięty.
class DeliveryCalculator {
    @Autowired
    DeliveryContext deliveryContext;

    private void calculate(List<Item> items, Order order, DeliveryType deliveryType) {
        Delivery delivery = deliveryContext.setStrategy(deliveryType);

        delivery.calculateCost(items, order);
    }
}

class DeliveryContext {
    Delivery setStrategy(DeliveryType deliveryType) {
        switch (deliveryType) {
            case OUTSIZE:
                return new OutsizeDelivery();
            case FREE:
                return new FreeDelivery();
            case GLASS:
                return new GlassDelivery();
            case NORMAL:
                return new NormalDelivery();
            case FANCY:
                return new FancyDelivery();
            default:
                throw new IllegalStateException("Idk");
        }
    }
}

enum DeliveryType {
    OUTSIZE, NORMAL, FREE, GLASS, FANCY
}