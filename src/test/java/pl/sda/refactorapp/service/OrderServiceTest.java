package pl.sda.refactorapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sda.refactorapp.dao.OrderDao;
import pl.sda.refactorapp.entity.Customer;
import pl.sda.refactorapp.entity.Item;
import pl.sda.refactorapp.entity.Order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private OrderDao orderDao;

    @Mock
    private CustomerService customerService;

    @Mock
    private DiscountCouponsService couponsService;

    @Mock
    private MailSender mailSender;

    @Mock
    private DeliveryService deliveryService;

    @InjectMocks
    private OrderService orderService;

    private final static float DISCOUNT_VALUE = 0.2f;
    private final static String COUPON_CODE = "ABC200";
    private final static String CUSTOMER_EMAIL = "email@email.com";

    @BeforeEach
    public void init() {
        given(mailSender.sendEmail(CUSTOMER_EMAIL, "Your order is placed!", "Thanks for ordering our products. Your order will be send very soon!"))
                .willReturn(true);
    }

    @Test
    void shouldMakeAnOrderWithDiscountCoupon() throws CouponNotExistException, CouponAlreadyUsedException {
        // given
        final UUID customerId = randomUUID();
        final Customer customer = createCustomer(customerId);
        given(customerService.findById(customerId)).willReturn(Optional.of(customer));

        given(couponsService.applyCoupon(customerId, COUPON_CODE)).willReturn(DISCOUNT_VALUE);

        List<Item> items = createItems();
        given(deliveryService.calculateDeliveryCost(items)).willReturn(new BigDecimal(35));

        // when
        assertDoesNotThrow(() -> orderService.makeOrder(customerId, items, COUPON_CODE));

        // then
        final var orderCapture = ArgumentCaptor.forClass(Order.class);
        verify(orderDao).save(orderCapture.capture());
        final var order = orderCapture.getValue();
        assertEquals(customerId, order.getCid());
        assertEquals(Order.ORDER_STATUS_WAITING, order.getStatus());
        assertEquals(DISCOUNT_VALUE, order.getDiscount()); // czy potrzeba tutaj sprawdzac discount? osobny test
    }

    @Test
    void shouldMakeAnOrderWithoutDiscountCoupon() {
        // given
        final UUID customerId = randomUUID();
        final Customer customer = createCustomer(customerId);
        given(customerService.findById(customerId)).willReturn(Optional.of(customer));

        List<Item> items = createItems();
        given(deliveryService.calculateDeliveryCost(items)).willReturn(new BigDecimal(35));

        // when
        assertDoesNotThrow(() -> orderService.makeOrder(customerId, items, null));

        // then
        final var orderCapture = ArgumentCaptor.forClass(Order.class);
        verify(orderDao).save(orderCapture.capture());
        final var order = orderCapture.getValue();
        assertEquals(customerId, order.getCid());
        assertEquals(Order.ORDER_STATUS_WAITING, order.getStatus());
        assertEquals(0.0f, order.getDiscount()); // czy potrzeba tutaj sprawdzac discount? osobny test
        assertEquals(new BigDecimal("35"), order.getDeliveryCost()); // czy powinnismy sprawdzac koszt dostawy tutaj?
    }


    @Test
    void shouldMakeAnOrderWithDiscountCouponForMailSenderError() throws CouponNotExistException, CouponAlreadyUsedException {
        // given
        final UUID customerId = randomUUID();
        final Customer customer = createCustomer(customerId);
        given(customerService.findById(customerId)).willReturn(Optional.of(customer));

        given(couponsService.applyCoupon(customerId, COUPON_CODE)).willReturn(DISCOUNT_VALUE);

        given(mailSender.sendEmail(CUSTOMER_EMAIL, "Your order is placed!", "Thanks for ordering our products. Your order will be send very soon!"))
                .willReturn(false);

        List<Item> items = createItems();
        given(deliveryService.calculateDeliveryCost(items)).willReturn(new BigDecimal(35));

        // when
        Assertions.assertThrows(MailSendingException.class, () ->
                orderService.makeOrder(customerId, items, COUPON_CODE));

        // then
        final var orderCapture = ArgumentCaptor.forClass(Order.class);
        verify(orderDao).save(orderCapture.capture());
        final var order = orderCapture.getValue();
        assertEquals(customerId, order.getCid());
        assertEquals(Order.ORDER_STATUS_WAITING, order.getStatus());
        assertEquals(DISCOUNT_VALUE, order.getDiscount()); // czy potrzeba tutaj sprawdzac discount? osobny test
        assertEquals(new BigDecimal("35"), order.getDeliveryCost()); // czy powinnismy sprawdzac koszt dostawy tutaj?
    }

    private List<Item> createItems() {
        final var item = new Item();
        item.setWeight(1.f);
        item.setQuantity(1);
        item.setPrice(BigDecimal.ONE);
        return List.of(item);
    }

    private Customer createCustomer(UUID customerId) {
        final var customer = new Customer();
        customer.setId(customerId);
        customer.setEmail(CUSTOMER_EMAIL);
        return customer;
    }
}