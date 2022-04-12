package pl.sda.refactorapp.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import pl.sda.refactorapp.annotation.Autowired;
import pl.sda.refactorapp.annotation.Service;
import pl.sda.refactorapp.annotation.Transactional;
import pl.sda.refactorapp.dao.OrderDao;
import pl.sda.refactorapp.entity.Item;
import pl.sda.refactorapp.entity.Order;
import pl.sda.refactorapp.service.payment.PaymentService;

@Service
public class OrderService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DiscountCouponsService couponsService;

    @Autowired
    private OrderDao dao;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private MailSender mailSender;

    /**
     * Create order and (optionally) apply discount couponCode
     *
     * @param customerId
     * @param items
     * @param couponCode
     * @return
     */
    @Transactional
    public void makeOrder(UUID customerId, List<Item> items, String couponCode) throws Exception {
        var customerOptional = customerService.findById(customerId);
        if (customerOptional.isPresent() && items != null && items.size() > 0) {
            // validate items
            for (var item : items) {
                if (item.getPrice().compareTo(BigDecimal.ZERO) <= 0 ||
                        item.getWeight() <= 0 ||
                        item.getQuantity() < 1) {
                    throw new ItemsValidationException("Ordered items are invalid!");
                }
            }

            var order = new Order();
            order.setId(UUID.randomUUID());
            order.setCid(customerId);
            order.setCtime(LocalDateTime.now());
            order.setStatus(Order.ORDER_STATUS_WAITING);
            order.setItems(items);

            // add discount
            if (couponCode != null) {
                float discount = couponsService.applyCoupon(customerId, couponCode);
                order.setDiscount(discount);
            }

            // calculate delivery
            BigDecimal deliveryCost = deliveryService.calculateDeliveryCost(items);
            order.setDeliveryCost(deliveryCost);

            var customer = customerOptional.get();

            // save to db and send email
            dao.save(order);
            boolean isSent = mailSender.sendEmail(customer.getEmail(),
                    "Your order is placed!",
                    "Thanks for ordering our products. Your order will be send very soon!");
            if (!isSent) {
                throw new MailSendingException("Message not sent! Rollback");
            }

            paymentService.pay(order, customer);
        }
    }

    /**
     * Create order and apply provided discount
     *
     * @param cid
     * @param items
     * @param discount
     * @return
     */
    @Transactional
    public boolean makeOrder(UUID cid, List<Item> items, float discount) {
        var result = false;
        var optional = customerService.findById(cid);
        if (optional.isPresent() && items != null && items.size() > 0 && discount > 0 && discount < 1) {
            // validate items
            for (var i : items) {
                if (i.getPrice().compareTo(BigDecimal.ZERO) <= 0 ||
                        i.getWeight() <= 0 ||
                        i.getQuantity() < 1) {
                    return false;
                }
            }

            var order = new Order();
            genId(order);
            order.setCid(cid);
            order.setCtime(LocalDateTime.now());
            order.setStatus(Order.ORDER_STATUS_WAITING);
            order.setDiscount(discount);
            var itemsList = order.getItems();
            if (itemsList == null) {
                itemsList = new ArrayList<>();
            }
            itemsList.addAll(items);
            order.setItems(itemsList);

            BigDecimal deliveryCost = deliveryService.calculateDeliveryCost(items);
            order.setDeliveryCost(deliveryCost);

            // save to db
            dao.save(order);

            // send email
            final var sendEmail = mailSender.sendEmail(optional.get().getEmail(),
                    "Your order is placed!",
                    "Thanks for ordering our products. Your order will be send very soon!");
            result = sendEmail;
        }
        return result;
    }

    /**
     * Change order status
     *
     * @param oid
     * @param status
     * @return
     */
    @Transactional
    public boolean updateOrderStatus(UUID oid, int status) {
        var result = false;
        var optional = dao.findById(oid);
        if (optional.isPresent() && status > 0 && status < 4) {
            var order = optional.get();
            if (status - order.getStatus() == 1) {
                order.setStatus(status);
                dao.save(order);
                var customer = customerService.findById(order.getCid()).get();
                var emailSend = false;
                if (status == 2) {
                    emailSend = mailSender.sendEmail(customer.getEmail(),
                            "Order status updated to sent",
                            "Your order changed status to sent. Our courier will deliver your order in 2 business days.");
                } else if (status == 3) {
                    emailSend = mailSender.sendEmail(customer.getEmail(),
                            "Order status updated to delivered",
                            "Your order changed status to delivered. Thank you for ordering our products!");
                }

                result = emailSend;
            }
        }
        return result;
    }

    private void genId(Order order) {
        order.setId(UUID.randomUUID());
    }
}

class ItemsValidationException extends Exception {
    public ItemsValidationException(String msg) {
        super(msg);
    }
}

class MailSendingException extends Exception {
    public MailSendingException(String msg) {
        super(msg);
    }
}

class CouponAlreadyUsedException extends Exception {
    public CouponAlreadyUsedException(String msg) {
        super(msg);
    }
}

class CouponNotExistException extends Exception {
    public CouponNotExistException(String msg) {
        super(msg);
    }
}
