package pl.sda.refactorapp.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import pl.sda.refactorapp.annotation.Entity;
import pl.sda.refactorapp.annotation.Id;
import pl.sda.refactorapp.annotation.OneToMany;

/**
 * The customer order
 */
@Entity
public class Order {

    // order statuses
    public static final int ORDER_STATUS_WAITING = 1;
    public static final int ORDER_STATUS_SENT = 2;
    public static final int ORDER_STATUS_DELIVERED = 3;

    @Id
    private UUID id;

    // customer id
    private UUID cid;

    private LocalDateTime ctime;

    // value between 0 and 1
    private float discount;

    @OneToMany
    private List<Item> items;

    private int status;

    public BigDecimal deliveryCost;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCid() {
        return cid;
    }

    public void setCid(UUID cid) {
        this.cid = cid;
    }

    public LocalDateTime getCtime() {
        return ctime;
    }

    public void setCtime(LocalDateTime ctime) {
        this.ctime = ctime;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BigDecimal getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(BigDecimal deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return Float.compare(order.discount, discount) == 0 && status == order.status && Objects.equals(
            id, order.id) && Objects.equals(cid, order.cid) && Objects.equals(ctime, order.ctime)
            && Objects.equals(items, order.items) && Objects.equals(deliveryCost, order.deliveryCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cid, ctime, discount, items, status, deliveryCost);
    }

    @Override
    public String toString() {
        return "Order{" +
            "id=" + id +
            ", cid=" + cid +
            ", ctime=" + ctime +
            ", discount=" + discount +
            ", items=" + items +
            ", status=" + status +
            ", deliveryCost=" + deliveryCost +
            '}';
    }
}
