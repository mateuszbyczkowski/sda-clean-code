package exercises.s8;

import pl.sda.refactorapp.annotation.Autowired;
import pl.sda.refactorapp.annotation.Service;
import pl.sda.refactorapp.annotation.Transactional;
import pl.sda.refactorapp.dao.CustomerDao;
import pl.sda.refactorapp.entity.Customer;

import java.time.LocalDateTime;
import java.util.UUID;

// szukanie odpowiedzialności i wynoszenie metod do osobnych klas
@Service
class RegisterService {

    @Autowired
    private final CustomerDao dao;

    public RegisterService(CustomerDao dao, CustomerValidator customerValidator) {
        this.dao = dao;
    }

    @Transactional
    public boolean registerCustomer(Customer customer) {
        var isInDb = dao.emailExists(customer.getEmail()) || dao.peselExists(customer.getPesel());

        if (isInDb) {
            return false;
        }

        //customer.setCreationTime(LocalDateTime.now());
        customer.setId(UUID.randomUUID());
        dao.save(customer);

        return true;
    }
}

class CustomerValidator {
    public boolean isValid(Customer customer) {
        return customer.getEmail() != null && customer.getfName() != null && customer.getLname() != null && customer.getPesel() != null;
    }
}

class Main {
    public void main(CustomerDao customerDao, CustomerValidator validator) {
        Customer customer = new Customer();

        RegisterService registerService = new RegisterService(customerDao, validator);

        if (validator.isValid(customer)) {
            registerService.registerCustomer(customer);
        }
    }
}
