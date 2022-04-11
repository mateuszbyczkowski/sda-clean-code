package exercises.s5;

import pl.sda.refactorapp.annotation.Controller;
import pl.sda.refactorapp.service.CustomerService;

@Controller
class RegisterPerson {

    private final CustomerService customerService;

    RegisterPerson(CustomerService customerService) {
        this.customerService = customerService;
    }

    //@RequestMethod(POST)
//    public String postRegisterPerson(Person person) {
//        return customerService.registerPerson(person)
//                ? "register-person-success-page"
//                : "register-person-error-page";
//    }

    public void createPerson() {
        new Person("email@email.pl", "firstName", "lastName", "987654412312", true, null);
    }
}

class Person {
    private String email;
    private String firstName;
    private String lastName;
    private String pesel;
    private boolean verified;
    private PersonAddress personAddress;

    public Person(String email, String firstName, String lastName, String pesel, boolean verified, PersonAddress personAddress) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.verified = verified;
        this.personAddress = personAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}

class PersonAddress {
    private String street;
    private String city;
    private String country;
    private String zipCode;

    public PersonAddress(String street, String city, String country, String zipCode) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
    }
}
