package exercises.s10;

import pl.sda.refactorapp.entity.Customer;

// zamiana kodów błędów na wyjątki
class CustomerValidator {

    public void validate(Customer customer) throws Exception {
        if(customer.getEmail() == null) {
            throw new Exception("EMAIL");
        }

        if(customer.getfName() == null) {
            throw new Exception("FNAME");
        }

        if(customer.getlName() == null) {
            throw new Exception("LNAME");
        }

        if(customer.getPesel() == null) {
            throw new Exception("PESEL");
        }

        if(customer.getAddrCity() == null) {
            throw new Exception("CITY");
        }
    }
}

class ExceptionHandler {
    void catchException(Exception exception) {
        String exMsg = exception.getMessage();
        switch ((exMsg)) {
            case "EMAIL":
                showMessage("email is invalid");
                break;
            case "FNAME":
                showMessage("first name is invalid");
                break;
            case "LNAME":
                showMessage("last name is invalid");
                break;
            case "PESEL":
                showMessage("pesel number is invalid");
                break;
            case "CITY":
                showMessage("city is invalid");
                break;
        }
    }

    private void showMessage(String msg) {
        //pokaz informacje o walidacji
    }
}