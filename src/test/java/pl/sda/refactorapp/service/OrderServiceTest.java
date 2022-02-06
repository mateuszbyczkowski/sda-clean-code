package pl.sda.refactorapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sda.refactorapp.dao.DiscountCouponsDao;
import pl.sda.refactorapp.dao.OrderDao;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderDao orderDao;

    @Mock
    private CustomerService customerService;

    @Mock
    private DiscountCouponsDao couponsDao;

    @InjectMocks
    private OrderService orderService;

    @Test
    void shouldMakeAnOrderWithDiscountCoupon() {
        // given

        // when

        // then
    }
}