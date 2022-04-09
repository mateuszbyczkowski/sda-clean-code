package pl.sda.refactorapp.controller;

import java.time.LocalDateTime;
import pl.sda.refactorapp.annotation.Controller;
import pl.sda.refactorapp.annotation.Autowired;
import pl.sda.refactorapp.service.DiscountCouponsService;

@Controller
public class DiscountCouponController {

    @Autowired
    private DiscountCouponsService couponsService;

    public String postNewCoupon(String code, LocalDateTime validDate) {
        if (couponsService.createCoupon(code, validDate)) {
            return "create-coupon-success-page";
        } else {
            return "create-coupon-error-page";
        }
    }

    public String postDeactivateCoupon(String code) {
        if (couponsService.deactivateCoupon(code)) {
            return "deactivate-coupon-success-page";
        } else {
            return "deactivate-coupon-error-page";
        }
    }
}
