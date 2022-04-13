package exercises.s13.template.solution;


import pl.sda.refactorapp.entity.Order;

public class DeliveryManager {
    void orderDelivery(Order order) {
        DeliveryBase deliveryBase;
        if (order.getChosenDelivery().equals("INPOST")) {
            deliveryBase = new DeliveryInpost();
        } else {
            deliveryBase = new DeliveryPoczta();
        }

        deliveryBase.deliver(order);
    }
}
