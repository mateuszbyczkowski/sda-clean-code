package pl.sda.refactorapp.dao;

import java.util.Optional;
import java.util.UUID;
import pl.sda.refactorapp.annotation.Repository;
import pl.sda.refactorapp.entity.Customer;

@Repository
public class CustomerDao {

    public void save(Customer customer) {
        throw new UnsupportedOperationException();
    }

    public boolean emailExists(String email) {
        throw new UnsupportedOperationException();
    }

    public boolean peselExists(String pesel) {
        throw new UnsupportedOperationException();
    }

    public boolean vatExists(String vat) {
        throw new UnsupportedOperationException();
    }

    public Optional<Customer> findById(UUID cid) {
        throw new UnsupportedOperationException();
    }
}
