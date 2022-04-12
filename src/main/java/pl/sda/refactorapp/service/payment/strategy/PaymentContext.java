package pl.sda.refactorapp.service.payment.strategy;

import pl.sda.refactorapp.entity.PreferredPayment;

public class PaymentContext {

    public Payment chooseStrategy(PreferredPayment preferredPayment) {
        switch (preferredPayment) {
            case CARD:
                return new CardStrategy();
            case PAYPAL:
                return new PayPalStrategy();
            default:
                return new BankTransferStrategy();
        }
    }
}
