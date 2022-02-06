package pl.sda.refactorapp.dao;

import java.util.Optional;
import pl.sda.refactorapp.annotation.Repository;
import pl.sda.refactorapp.entity.DiscountCoupon;

@Repository
public class DiscountCouponsDao {

    public Optional<DiscountCoupon> findByCode(String code) {
        throw new UnsupportedOperationException();
    }

    public boolean couponExists(String code) {
        throw new UnsupportedOperationException();
    }

    public void save(DiscountCoupon coupon) {
        throw new UnsupportedOperationException();
    }
}
