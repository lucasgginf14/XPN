package es.udc.fic.xpn.order;

import java.util.Date;
import java.util.Objects;

public class Order {

    private Integer id;
    private Date date;
    private Integer quantity;
    private Integer originWarehouseId;
    private Integer destinationWarehouseId;
    private Integer productSku;

    public Order() {}

    public Order(Integer id, Date date, Integer quantity, Integer destinationWarehouseId, Integer productSku) {
        this.id = id;
        this.date = date;
        this.quantity = quantity;
        this.destinationWarehouseId = destinationWarehouseId;
        this.productSku = productSku;
    }

    public Order(Integer id, Date date, Integer quantity, Integer destinationWarehouseId, Integer productSku, Integer originWarehouseId) {
        this(id, date, quantity, destinationWarehouseId, productSku);
        this.originWarehouseId = originWarehouseId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getOriginWarehouseId() {
        return originWarehouseId;
    }

    public void setOriginWarehouseId(Integer originWarehouseId) {
        this.originWarehouseId = originWarehouseId;
    }

    public Integer getDestinationWarehouseId() {
        return destinationWarehouseId;
    }

    public void setDestinationWarehouseId(Integer destinationWarehouseId) {
        this.destinationWarehouseId = destinationWarehouseId;
    }

    public Integer getProductSku() {
        return productSku;
    }

    public void setProductSku(Integer productSku) {
        this.productSku = productSku;
    }

    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return order.hashCode() == this.hashCode();
    }

    public int hashCode(){
        return Objects.hash(id, date, quantity, originWarehouseId, destinationWarehouseId, productSku);
    }
}
