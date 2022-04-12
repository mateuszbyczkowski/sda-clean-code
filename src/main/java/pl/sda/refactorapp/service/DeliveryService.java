package pl.sda.refactorapp.service;

import pl.sda.refactorapp.annotation.Service;
import pl.sda.refactorapp.entity.Item;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DeliveryService {

    public BigDecimal calculateDeliveryCost(List<Item> items) {
        var tp = BigDecimal.ZERO; //total price
        var tw = 0; //total weight
        for (Item i : items) {
            tp = tp.add(i.getPrice().multiply(new BigDecimal(i.getQuantity())));
            tw += (i.getQuantity() * i.getWeight());
        }
        if (tp.compareTo(new BigDecimal(250)) > 0 && tw < 1) {
            return BigDecimal.ZERO;
        } else if (tw < 1) {
            return new BigDecimal(15);
        } else if (tw < 5) {
            return new BigDecimal(35);
        } else {
            return new BigDecimal(50);
        }
    }
}
