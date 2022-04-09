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
    private CustomerDao dao;

    @Transactional
    public boolean registerPerson(String email, String fName, String lName, String pesel, boolean verified) {
        var result = false;
        var customer = new Customer();
        customer.setType(Customer.PERSON);
        var isInDb = dao.emailExists(email) || dao.peselExists(pesel);
        if (!isInDb) {
            if (isValidPerson(customer)) {
                result = true;
            }
        }

        if (result) {
            genCustomerId(customer);
            dao.save(customer);
        }

        return result;
    }

    @Transactional
    public boolean registerCompany(String email, String name, String vat, boolean verified) {
        var result = false;
        var customer = new Customer();
        customer.setType(Customer.COMPANY);
        var isInDb = dao.emailExists(email) || dao.vatExists(vat); // vat jest również w customerze
        if (!isInDb) {
            if (isValidPerson(customer)) {
                result = true;
            }
        }

        if (result) {
            customer.setCtime(LocalDateTime.now());
            genCustomerId(customer);
            dao.save(customer);
        }

        return result;
    }

    private boolean isValidPerson(Customer customer) {
        return customer.getEmail() != null && customer.getfName() != null && customer.getlName() != null && customer.getPesel() != null;
    }

    private void genCustomerId(Customer customer) {
        customer.setId(UUID.randomUUID());
    }
}
