package pl.sda.refactorapp.service;

import java.time.LocalDateTime;
import pl.sda.refactorapp.annotation.Autowired;
import pl.sda.refactorapp.annotation.Service;
import pl.sda.refactorapp.annotation.Transactional;
import pl.sda.refactorapp.dao.DiscountCouponsDao;
import pl.sda.refactorapp.entity.DiscountCoupon;

@Service
public class DiscountCouponsService {

    @Autowired
    private DiscountCouponsDao dao;

    @Transactional
    public boolean createCoupon(String code, LocalDateTime date) {
        var result = false;
        var bool = dao.couponExists(code);
        if (!bool) {
            var coupon = new DiscountCoupon();
            coupon.setCoupon(code);
            coupon.setValidDate(date);
            coupon.setUsed(false);
            dao.save(coupon);
            result = true;
        }
        return result;
    }

    @Transactional
    public boolean deactivateCoupon(String code) {
        var result = false;
        var opt = dao.findByCode(code);
        if (opt.isPresent()) {
            opt.get().setUsed(true);
            dao.save(opt.get());
            result = true;
        }
        return result;
    }
}
