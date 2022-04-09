package exercises.s4;

import pl.sda.refactorapp.entity.Item;
import pl.sda.refactorapp.entity.Order;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;

// inline metod
class DeliveryCostCalculator {
    private static final BigDecimal FREE_DELIVERY_PRICE_THRESHOLD = new BigDecimal(250);
    private static final int FREE_DELIVERY_WEIGHT_THRESHOLD = 1;
    private static final int NORMAL_DELIVERY_WEIGHT_THRESHOLD = 5;

    private static final BigDecimal FREE_DELIVERY = ZERO;
    private static final BigDecimal ECONOMIC_PARCEL = new BigDecimal(15);
    private static final BigDecimal NORMAL_PARCEL = new BigDecimal(35);
    private static final BigDecimal OUTSIZE_PARCEL = new BigDecimal(50);

    private void calculate(List<Item> items, Order order) {
        BigDecimal totalPrice = calculatePrice(items);
        float totalWeight = calculateWeight(items);

        if (isFreeDelivery(totalPrice, totalWeight)) {
            order.setDeliveryCost(FREE_DELIVERY);
        } else if (isEconomicParcel(totalWeight)) {
            order.setDeliveryCost(ECONOMIC_PARCEL);
        } else if (isNormalParcel(totalWeight)) {
            order.setDeliveryCost(NORMAL_PARCEL);
        } else {
            order.setDeliveryCost(OUTSIZE_PARCEL);
        }
    }

    private Float calculateWeight(List<Item> items) {
        return items.stream().map(this::calculateItemWeight).reduce(0f, Float::sum);
    }

    private BigDecimal calculatePrice(List<Item> items) {
        return items.stream().map(this::calculateItemPrice).reduce(ZERO, BigDecimal::add);
    }

    private boolean isNormalParcel(float totalWeight) {
        return totalWeight < NORMAL_DELIVERY_WEIGHT_THRESHOLD;
    }

    private boolean isEconomicParcel(float totalWeight) {
        return totalWeight < FREE_DELIVERY_WEIGHT_THRESHOLD;
    }

    private boolean isFreeDelivery(BigDecimal totalPrice, float totalWeight) {
        return isFreeDeliveryPrice(totalPrice) > 0 && isFreeDeliveryWeight(totalWeight);
    }
    // inline metod
    private boolean isFreeDeliveryWeight(float totalWeight) {
        return totalWeight < FREE_DELIVERY_WEIGHT_THRESHOLD;
    }
    // inline metod
    private int isFreeDeliveryPrice(BigDecimal totalPrice) {
        return totalPrice.compareTo(FREE_DELIVERY_PRICE_THRESHOLD);
    }

    private float calculateItemWeight(Item item) {
        return item.getQuantity() * item.getWeight();
    }

    private BigDecimal calculateItemPrice(Item item) {
        return item.getPrice().multiply(new BigDecimal(item.getQuantity()));
    }
}
