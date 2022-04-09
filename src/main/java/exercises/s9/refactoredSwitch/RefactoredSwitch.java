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
    DeliveryProvider deliveryProvider;

    private void calculate(List<Item> items, Order order, DeliveryType deliveryType) {
        Delivery delivery = deliveryProvider.provideDelivery(deliveryType);

        delivery.calculateCost(items, order);
    }
}

class DeliveryProvider {
    Delivery provideDelivery(DeliveryType deliveryType) {
        switch (deliveryType) {
            case OUTSIZE:
                return new OutsizeDelivery();
            case FREE:
                return new FreeDelivery();
            case GLASS:
                return new GlassDelivery();
            case NORMAL:
                return new NormalDelivery();
            default:
                throw new IllegalStateException("Idk");
        }
    }
}

enum DeliveryType {
    OUTSIZE, NORMAL, FREE, GLASS;
}