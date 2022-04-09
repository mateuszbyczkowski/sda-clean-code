package exercises.s1;

import pl.sda.refactorapp.entity.Item;
import pl.sda.refactorapp.entity.Order;

import java.math.BigDecimal;
import java.util.List;

// nazwy klas, metod, zmiennych
class MyClass {

    private void count(List<Item> items, Order order) {
        var tp = BigDecimal.ZERO; //total price
        var tw = 0; //total weight
        for (Item i : items) {
            tp = tp.add(i.getPrice().multiply(new BigDecimal(i.getQuantity()))); // tp = tp + (i.price * i.quantity)
            tw += (i.getQuantity() * i.getWeight());
        }
        if (tp.compareTo(new BigDecimal(250)) > 0 && tw < 1) {
            order.setDeliveryCost(BigDecimal.ZERO);
        } else if (tw < 1) {
            order.setDeliveryCost(new BigDecimal(15));
        } else if (tw < 5) {
            order.setDeliveryCost(new BigDecimal(35));
        } else {
            order.setDeliveryCost(new BigDecimal(50));
        }
    }
}
