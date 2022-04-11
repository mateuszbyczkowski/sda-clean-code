package exercises.s2;

import pl.sda.refactorapp.entity.Item;
import pl.sda.refactorapp.entity.Order;

import java.math.BigDecimal;
import java.util.List;

// zmiana magic numbers/strings na stałe
class DeliveryCostCalculator {
    private static final BigDecimal FREE_DELIVERY_PRICE_THRESHOLD = new BigDecimal(250);
    private static final int FREE_DELIVERY_WEIGHT_THRESHOLD = 1;
    private static final int SMALL_DELIVERY_WEIGHT = 1;
    private static final int STANDARD_DELIVERY_WEIGHT = 5;

    private static final BigDecimal SMALL_PARCEL_ECONOMIC_CARRIER_PRICE = new BigDecimal(15);
    private static final BigDecimal STANDARD_PARCEL_PRICE = new BigDecimal(35);
    private static final BigDecimal OUTSIZE_PARCEL_PRICE = new BigDecimal(50);
    private static final BigDecimal FREE_PARCEL_PRICE = BigDecimal.ZERO;

    private void calculateCostAndUpdateOrder(List<Item> items, Order order) {
        var totalPrice = BigDecimal.ZERO;
        var totalWeight = 0;

        for (Item item : items) {
            totalPrice = totalPrice.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
            totalWeight += item.getQuantity() * item.getWeight();
        }

        if (totalPrice.compareTo(FREE_DELIVERY_PRICE_THRESHOLD) > 0 && totalWeight < FREE_DELIVERY_WEIGHT_THRESHOLD) {
            order.setDeliveryCost(FREE_PARCEL_PRICE);
        } else if (totalWeight < SMALL_DELIVERY_WEIGHT) {
            order.setDeliveryCost(SMALL_PARCEL_ECONOMIC_CARRIER_PRICE);
        } else if (totalWeight < STANDARD_DELIVERY_WEIGHT) {
            order.setDeliveryCost(STANDARD_PARCEL_PRICE);
        } else {
            order.setDeliveryCost(OUTSIZE_PARCEL_PRICE);
        }
    }
}
