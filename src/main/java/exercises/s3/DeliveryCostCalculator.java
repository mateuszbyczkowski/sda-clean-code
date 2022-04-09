package exercises.s3;

import pl.sda.refactorapp.entity.Item;
import pl.sda.refactorapp.entity.Order;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.*;

// odchudzanie metod
class DeliveryCostCalculator {
    private static final BigDecimal FREE_DELIVERY_PRICE_THRESHOLD = new BigDecimal(250);
    private static final int FREE_DELIVERY_WEIGHT_THRESHOLD = 1;
    private static final int NORMAL_DELIVERY_WEIGHT_THRESHOLD = 5;

    private static final BigDecimal FREE_DELIVERY = ZERO;
    private static final BigDecimal ECONOMIC_PARCEL = new BigDecimal(15);
    private static final BigDecimal NORMAL_PARCEL = new BigDecimal(35);
    private static final BigDecimal OUTSIZE_PARCEL = new BigDecimal(50);

    private void calculate(List<Item> items, Order order) {
        var totalPrice = ZERO;
        var totalWeight = 0;
        for (Item item : items) {
            totalPrice = totalPrice.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
            totalWeight += item.getQuantity() * item.getWeight();
        }
        if (totalPrice.compareTo(FREE_DELIVERY_PRICE_THRESHOLD) > 0 && totalWeight < FREE_DELIVERY_WEIGHT_THRESHOLD) {
            order.setDeliveryCost(FREE_DELIVERY);
        } else if ((totalWeight < FREE_DELIVERY_WEIGHT_THRESHOLD)) {
            order.setDeliveryCost(ECONOMIC_PARCEL);
        } else if (totalWeight < NORMAL_DELIVERY_WEIGHT_THRESHOLD) {
            order.setDeliveryCost(NORMAL_PARCEL);
        } else {
            order.setDeliveryCost(OUTSIZE_PARCEL);
        }
    }
}
