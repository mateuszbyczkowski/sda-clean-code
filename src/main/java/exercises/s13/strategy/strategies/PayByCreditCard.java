package exercises.s13.strategy.strategies;

import exercises.s13.strategy.CreditCard;
import exercises.s13.strategy.PayStrategy;

public class PayByCreditCard implements PayStrategy {
    private CreditCard card;

    @Override
    public void collectPaymentDetails() {
        System.out.print("Enter the card number: ");
        System.out.print("Enter the card expiration date 'mm/yy': ");
        System.out.print("Enter the CVV code: ");
        card = new CreditCard("1234 1234 1234 1234 1234", "12/12/2020", "123");

        // Validate credit card number...
    }

    @Override
    public boolean pay(int paymentAmount) {
        if (cardIsPresent()) {
            System.out.println("Paying " + paymentAmount + " using Credit Card.");
            card.setAmount(card.getAmount() - paymentAmount);
            return true;
        } else {
            return false;
        }
    }

    private boolean cardIsPresent() {
        return card != null;
    }
}
