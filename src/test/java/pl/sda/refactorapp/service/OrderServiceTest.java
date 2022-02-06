package pl.sda.refactorapp.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mockStatic;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.mail.Transport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sda.refactorapp.dao.DiscountCouponsDao;
import pl.sda.refactorapp.dao.OrderDao;
import pl.sda.refactorapp.entity.Customer;
import pl.sda.refactorapp.entity.Item;

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

    @BeforeAll
    static void beforeAll() throws Exception {
        EvnHelper.setEnvironmentVariables(Map.of(
            "MAIL_SMTP_HOST", "smtp",
            "MAIL_SMTP_PORT", "22",
            "MAIL_SMTP_SSL_TRUST", "false"));
    }

    @Test
    void shouldMakeAnOrderWithDiscountCoupon() {
        // given
        final var cid = UUID.randomUUID();
        final var item = new Item();
        item.setId(UUID.randomUUID());
        item.setPrice(new BigDecimal("100.00"));
        item.setQuantity(1);
        item.setWeight(1.f);
        final var items = List.of(item);
        final var coupon = "ABC200";
        final var customer = new Customer();
        customer.setEmail("email@email.com");
        given(customerService.findById(cid)).willReturn(Optional.of(customer));

        final var transportMockedStatic = mockStatic(Transport.class);

        // when
        final var result = orderService.makeOrder(cid, items, coupon);

        // then
        assertTrue(result);
        transportMockedStatic.verify(() -> Transport.send(any()));
    }
}