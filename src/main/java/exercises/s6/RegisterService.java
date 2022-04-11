package exercises.s6;

import pl.sda.refactorapp.annotation.Autowired;
import pl.sda.refactorapp.annotation.Service;
import pl.sda.refactorapp.annotation.Transactional;
import pl.sda.refactorapp.dao.CustomerDao;
import pl.sda.refactorapp.entity.Customer;

import java.time.LocalDateTime;
import java.util.UUID;

//grupowanie podobnych i zaleznych metod
@Service
class RegisterService {

    @Autowired
    private CustomerDao customerRepository;

    @Transactional
    public boolean registerCustomer(Customer customer) {
        var customerValid = isValid(customer);

        if (customerValid) {
            prepareAndSave(customer);
        }

        return customerValid;
    }

    private void prepareAndSave(Customer customer) {
        //customer.setCreationTime(LocalDateTime.now());
        customer.setId(UUID.randomUUID());
        customerRepository.save(customer);
    }

    private boolean isValid(Customer customer) {
        var isInDb = customerRepository.emailExists(customer.getEmail()) || vatOrPeselExists(customer);

        if (isInDb) {
            return false;
        }

        return isValidPerson(customer);
    }

    private boolean vatOrPeselExists(Customer customer) {
        return customerRepository.vatExists(customer.getCompVat())
                || customerRepository.peselExists(customer.getPesel());
    }

    private boolean isValidPerson(Customer customer) {
        return customer.getEmail() != null && customer.getfName() != null && customer.getLname() != null && customer.getPesel() != null;
    }
}

class RegisterController {
    void registerUser(Customer customer) {
        customer.setType(Customer.PERSON);
        new RegisterService().registerCustomer(customer);
    }

    void registerCompany(Customer customer) {
        customer.setType(Customer.COMPANY);
        new RegisterService().registerCustomer(customer);
    }
}