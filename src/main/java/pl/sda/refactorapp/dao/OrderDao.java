package pl.sda.refactorapp.dao;

import java.util.Optional;
import java.util.UUID;
import pl.sda.refactorapp.annotation.Repository;
import pl.sda.refactorapp.entity.Order;

@Repository
public class OrderDao {

    public void save(Order order) {
        throw new UnsupportedOperationException();
    }

    public Optional<Order> findById(UUID oid) {
        throw new UnsupportedOperationException();
    }
}
