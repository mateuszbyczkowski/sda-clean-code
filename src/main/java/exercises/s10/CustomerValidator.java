package exercises.s10;

import pl.sda.refactorapp.entity.Customer;

// zamiana kodów błędów na wyjątki
class CustomerValidator {

    public void validate(Customer customer) throws Exception {
        if (customer.getEmail() == null) {
            throw new EmailValidationException("This email is invalid!");
        }

        if (customer.getfName() == null) {
            throw new FNameValidationException("FNAME");
        }

        if (customer.getLname() == null) {
            throw new LastNameValidationException("LNAME");
        }

        if (customer.getPesel() == null) {
            throw new Exception("PESEL");
        }

        if (customer.getAddrCity() == null) {
            throw new Exception("CITY");
        }
    }
}

class EmailValidationException extends Exception {
    public EmailValidationException(String message) {
        super(message);
    }
}

class FNameValidationException extends Exception {
    public FNameValidationException(String message) {
        super(message);
    }
}

class LastNameValidationException extends Exception {
    public LastNameValidationException(String message) {
        super(message);
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
            default:
                showMessage("unknown error");
                break;
        }
    }

    void catchException(EmailValidationException exception) {
        showMessage(exception.getMessage());
    }

    private void showMessage(String msg) {
        //pokaz informacje o walidacji
    }
}
