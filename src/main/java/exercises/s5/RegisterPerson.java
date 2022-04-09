package exercises.s5;

import pl.sda.refactorapp.annotation.Controller;
import pl.sda.refactorapp.annotation.Autowired;
import pl.sda.refactorapp.service.CustomerService;

@Controller
class RegisterPerson {

    @Autowired
    private CustomerService customerService;

    public String postRegisterPerson(String email, String fName, String lName, String pesel, boolean verified, String street, String city, String country, String zipCode) {
        if (customerService.registerPerson(email, fName, lName, pesel, verified)) {
            return "register-person-success-page";
        } else {
            return "register-person-error-page";
        }
    }
}
