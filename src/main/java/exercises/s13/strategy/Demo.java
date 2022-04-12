package exercises.s13.strategy;

import exercises.s13.strategy.strategies.PayByPayPal;

public class Demo {
    public static void main(String[] args) {
        PayStrategy strategy = new PayByPayPal();
        //PayStrategy strategy = new PayByCreditCard();
        pay(strategy);
    }

    private static void pay(PayStrategy strategy) {
        Order order = new Order();
        order.setTotalCost(100);

        order.processOrder(strategy);

        strategy.pay(order.getTotalCost());
    }
}
