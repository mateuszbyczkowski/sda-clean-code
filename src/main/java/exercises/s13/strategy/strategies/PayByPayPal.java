package exercises.s13.strategy.strategies;

import exercises.s13.strategy.PayStrategy;

public class PayByPayPal implements PayStrategy {
    private String email;
    private String password;
    private boolean signedIn;

    @Override
    public void collectPaymentDetails() {
        while (!signedIn) {
            email = "test@test.pl";
            password = "PASSWORD";
            if (verify()) {
                System.out.println("Data verification has been successful.");
            } else {
                System.out.println("Wrong email or password!");
            }
        }
    }

    private boolean verify() {
        //verify is user correct
        if (email == "test@test.pl" && password == "PASSWORD") {
            setSignedIn(true);
        }
        return signedIn;
    }

    @Override
    public boolean pay(int paymentAmount) {
        if (signedIn) {
            return true;
        } else {
            return false;
        }
    }

    private void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }
}
