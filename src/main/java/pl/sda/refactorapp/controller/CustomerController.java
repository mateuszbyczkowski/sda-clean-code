package pl.sda.refactorapp.controller;

import java.util.UUID;
import pl.sda.refactorapp.annotation.Controller;
import pl.sda.refactorapp.service.CustomerService;

@Controller
public class CustomerController {

    public CustomerService customerService;

    public String postRegisterPerson(String email, String fName, String lName, String pesel, boolean verified) {
        if (customerService.registerPerson(email, fName, lName, pesel, verified)) {
            return "register-person-success-page";
        } else {
            return "register-person-error-page";
        }
    }

    public String postRegisterCompany(String email, String name, String vat, boolean verified) {
        if (customerService.registerCompany(email, name, vat, verified)) {
            return "register-company-success-page";
        } else {
            return "register-company-error-page";
        }
    }

    public String postUpdateAddress(UUID cid, String str, String zipcode, String city, String country) {
        if (customerService.updateAddress(cid, str, zipcode, city, country)) {
            return "update-address-success-page";
        } else {
            return "update-address-error-page";
        }
    }
}
