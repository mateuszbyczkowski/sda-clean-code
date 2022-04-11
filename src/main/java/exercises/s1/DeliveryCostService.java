package exercises.s1;

import pl.sda.refactorapp.entity.Item;
import pl.sda.refactorapp.entity.Order;

import java.math.BigDecimal;
import java.util.List;

// nazwy klas, metod, zmiennych
class DeliveryCostService {

   public  void calculateCostAndUpdateOrder(List<Item> items, Order order) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        var totalWeight = 0;

        for (var item : items) {
            totalPrice = totalPrice.add(item.getPrice().multiply(new BigDecimal(item.getQuantity()))); // totalPrice = totalPrice + (item.price * item.quantity)
            totalWeight += (item.getQuantity() * item.getWeight());
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

class Main {
    public static void main(String[] args) {
        DeliveryCostService deliveryCostService = new DeliveryCostService();


        //..
        deliveryCostService.calculateCostAndUpdateOrder(null, null);

        //myClass.calculate();
    }
}
