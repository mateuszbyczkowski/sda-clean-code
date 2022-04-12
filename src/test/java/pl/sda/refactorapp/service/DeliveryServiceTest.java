package pl.sda.refactorapp.service;

import org.junit.jupiter.api.Test;
import pl.sda.refactorapp.entity.Item;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeliveryServiceTest {
    private static final BigDecimal FREE_DELIVERY_COST = BigDecimal.ZERO;

    @Test
    void shouldCorrectlyCalculateFreeDelivery() {
        //given
        DeliveryService deliveryService = new DeliveryService();

        List<Item> items = List.of(
                anItem(100, 0.2f),
                anItem(150, 0.1f),
                anItem(50, 0.4f),
                anItem(30, 0.1f)
        );
        //when
        BigDecimal deliveryCost = deliveryService.calculateDeliveryCost(items);

        //then
        assertEquals(FREE_DELIVERY_COST, deliveryCost);
    }

    private Item anItem(double price, float weight) {
        Item item = new Item();
        item.setPrice(BigDecimal.valueOf(price));
        item.setWeight(weight);
        item.setQuantity(1);
        return item;
    }
}
