package es.udc.fic.xpn.reception;

import java.util.Date;
import java.util.Objects;

public class Reception {

    private Integer id;
    private Integer quantity;
    private String supplier;
    private Date date;
    private Integer warehouseId;
    private Integer productSku;

    public Reception() {}

    public Reception(Integer id, Integer quantity, String supplier, Date date, Integer warehouseId, Integer productSku) {
        this.id = id;
        this.quantity = quantity;
        this.supplier = supplier;
        this.date = date;
        this.warehouseId = warehouseId;
        this.productSku = productSku;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
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
        Reception reception = (Reception) o;
        return reception.hashCode() == this.hashCode();
    }

    public int hashCode() {
        return Objects.hash(id, date, quantity, supplier, warehouseId, productSku);
    }
}
