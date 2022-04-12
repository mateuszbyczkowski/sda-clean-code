package pl.sda.refactorapp.service;

import java.time.LocalDateTime;
import java.util.UUID;

import pl.sda.refactorapp.annotation.Autowired;
import pl.sda.refactorapp.annotation.Service;
import pl.sda.refactorapp.annotation.Transactional;
import pl.sda.refactorapp.dao.DiscountCouponsDao;
import pl.sda.refactorapp.entity.DiscountCoupon;

@Service
public class DiscountCouponsService {

    @Autowired
    private DiscountCouponsDao couponsDao;

    @Transactional
    public boolean createCoupon(String code, LocalDateTime date) {
        var result = false;
        var bool = couponsDao.couponExists(code);
        if (!bool) {
            var coupon = new DiscountCoupon();
            coupon.setCoupon(code);
            coupon.setValidDate(date);
            coupon.setUsed(false);
            couponsDao.save(coupon);
            result = true;
        }
        return result;
    }

    @Transactional
    public boolean deactivateCoupon(String code) {
        var result = false;
        var opt = couponsDao.findByCode(code);
        if (opt.isPresent()) {
            opt.get().setUsed(true);
            couponsDao.save(opt.get());
            result = true;
        }
        return result;
    }

    @Transactional
    public float applyCoupon(UUID customerId, String couponCode) throws CouponAlreadyUsedException, CouponNotExistException {
        var discountCouponOptional = couponsDao.findByCode(couponCode);
        if (discountCouponOptional.isPresent()) {
            var discountCoupon = discountCouponOptional.get();
            if (isExpired(discountCoupon)) {
                return 0;
            }

            if (!discountCoupon.isUsed()) {
                discountCoupon.setUsedBy(customerId);
                discountCoupon.setUsed(true);
                couponsDao.save(discountCoupon);
                return discountCoupon.getValue();
            } else {
                throw new CouponAlreadyUsedException("Coupon already used!");
            }
        } else {
            throw new CouponNotExistException("Coupon not exists. Typo?");
        }
    }

    private boolean isExpired(DiscountCoupon discountCoupon) {
        return discountCoupon.getValidDate().isBefore(LocalDateTime.now());
    }
}
