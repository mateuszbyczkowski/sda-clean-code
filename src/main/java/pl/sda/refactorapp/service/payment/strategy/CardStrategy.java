package pl.sda.refactorapp.service.payment.strategy;

import pl.sda.refactorapp.entity.CardData;
import pl.sda.refactorapp.entity.Customer;
import pl.sda.refactorapp.entity.Item;
import pl.sda.refactorapp.entity.Order;
import pl.sda.refactorapp.service.CardService;

import java.math.BigDecimal;

public class CardStrategy implements Payment {
    @Override
    public void pay(Customer customer, Order order) throws Exception {
        if (order == null) {
            throw new Exception("Payment failed! Try again later");
        }

        CardData cardData = customer.getCardData();

        if (isValid(cardData)) {
            var totalPrice = BigDecimal.ZERO;
            for (Item item : order.getItems()) {
                totalPrice = totalPrice.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
            }

            try {
                CardService cardService = new CardService();
                cardService.payWithCard(cardData, order.getDeliveryCost().add(totalPrice));
            } catch (Exception ex) {
                throw new Exception("Payment failed! Try again later");
            }
        }
    }

    private boolean isValid(CardData cardData) {
        return cardData.getCardNo() != null && cardData.getCardDate() != null && cardData.getCVV() != null;
    }
}
