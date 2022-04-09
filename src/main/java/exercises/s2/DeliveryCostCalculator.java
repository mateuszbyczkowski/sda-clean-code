package exercises.s2;

import pl.sda.refactorapp.entity.Item;
import pl.sda.refactorapp.entity.Order;

import java.math.BigDecimal;
import java.util.List;

// zmiana magic numbers/strings na stałe
class DeliveryCostCalculator {

    private void calculate(List<Item> items, Order order) {
        var totalPrice = BigDecimal.ZERO;
        var totalWeight = 0;
        for (Item item : items) {
            totalPrice = totalPrice.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
            totalWeight += item.getQuantity() * item.getWeight();
        }
        if (totalPrice.compareTo(new BigDecimal(250)) > 0 && totalWeight < 1) {
            order.setDeliveryCost(BigDecimal.ZERO);
        } else if (totalWeight < 1) {
            order.setDeliveryCost(new BigDecimal(15));
        } else if (totalWeight < 5) {
            order.setDeliveryCost(new BigDecimal(35));
        } else {
            order.setDeliveryCost(new BigDecimal(50));
        }
    }
}
