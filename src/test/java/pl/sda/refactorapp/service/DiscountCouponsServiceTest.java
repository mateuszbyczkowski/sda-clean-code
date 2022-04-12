package pl.sda.refactorapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sda.refactorapp.dao.DiscountCouponsDao;
import pl.sda.refactorapp.entity.DiscountCoupon;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.HOURS;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DiscountCouponsServiceTest {

    @Mock
    private DiscountCouponsDao couponsDao;

    @InjectMocks
    private DiscountCouponsService couponsService;

    private final static float DISCOUNT_VALUE = 0.2f;
    private final static String COUPON_CODE = "ABC200";
    private final static UUID CUSTOMER_ID = randomUUID();

    @Test
    void shouldCalculateDiscountIfCouponNotUsed() throws CouponNotExistException, CouponAlreadyUsedException {
        //given
        LocalDateTime validDate = LocalDateTime.now().plus(1, HOURS);
        var coupon = createCoupon(false, validDate);
        given(couponsDao.findByCode(COUPON_CODE)).willReturn(Optional.of(coupon));

        //when
        float discount = couponsService.applyCoupon(CUSTOMER_ID, COUPON_CODE);

        assertEquals(discount, DISCOUNT_VALUE);
        //todo sprawdź czy kupon został poprawnie uzyty i przypisano mu klienta
    }

    @Test
    void shouldNotApplyCouponAlreadyUsed() {
        //given
        LocalDateTime validDate = LocalDateTime.now().plus(1, HOURS);
        var coupon = createCoupon(true, validDate);
        given(couponsDao.findByCode(COUPON_CODE)).willReturn(Optional.of(coupon));

        //when
        Assertions.assertThrows(CouponAlreadyUsedException.class, () ->
                couponsService.applyCoupon(CUSTOMER_ID, COUPON_CODE));
    }

    @Test
    void shouldNotApplyExpiredCoupon() throws CouponNotExistException, CouponAlreadyUsedException {
        //given
        LocalDateTime invalidDate = LocalDateTime.now().minus(1, HOURS);
        var coupon = createCoupon(false, invalidDate);
        given(couponsDao.findByCode(COUPON_CODE)).willReturn(Optional.of(coupon));

        //when
        float discount = couponsService.applyCoupon(CUSTOMER_ID, COUPON_CODE);

        //then
        assertNotEquals(discount, DISCOUNT_VALUE);
        assertEquals(discount, 0.0f);
    }

    private DiscountCoupon createCoupon(boolean isUsed, LocalDateTime validDate) {
        DiscountCoupon discountCoupon = new DiscountCoupon();
        discountCoupon.setCoupon(COUPON_CODE);
        discountCoupon.setValidDate(validDate);
        discountCoupon.setValue(DISCOUNT_VALUE);
        discountCoupon.setUsed(isUsed);
        return discountCoupon;
    }
}
