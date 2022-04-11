package pl.sda.refactorapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sda.refactorapp.dao.DiscountCouponsDao;
import pl.sda.refactorapp.dao.OrderDao;
import pl.sda.refactorapp.entity.Customer;
import pl.sda.refactorapp.entity.DiscountCoupon;
import pl.sda.refactorapp.entity.Item;
import pl.sda.refactorapp.entity.Order;

import javax.mail.Transport;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

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
        final var customerId = randomUUID();
        final var customer = new Customer();
        customer.setEmail("email@email.com");
        given(customerService.findById(customerId)).willReturn(Optional.of(customer));

        final var item = new Item();
        item.setWeight(1.f);
        item.setQuantity(1);
        item.setPrice(BigDecimal.ONE);
        final var items = List.of(item);

        final var couponCode = "ABC200";
        final var discountCoupon = new DiscountCoupon();
        discountCoupon.setCoupon(couponCode);
        discountCoupon.setValue(0.2f);
        given(couponsDao.findByCode(couponCode)).willReturn(Optional.of(discountCoupon));

        final var mockedStaticTransport = Mockito.mockStatic(Transport.class);

        // when
        final var result = orderService.makeOrder(customerId, items, couponCode);

        // then
        verify(couponsDao).save(discountCoupon);
        assertTrue(discountCoupon.isUsed());
        final var orderCapture = ArgumentCaptor.forClass(Order.class);
        verify(orderDao).save(orderCapture.capture());
        final var order = orderCapture.getValue();
        assertEquals(customerId, order.getCid());
        assertEquals(Order.ORDER_STATUS_WAITING, order.getStatus());
        assertEquals(0.2f, order.getDiscount());
        assertEquals(new BigDecimal("35"), order.getDeliveryCost());
        mockedStaticTransport.verify(() -> Transport.send(any()));
        assertTrue(result);
    }
}