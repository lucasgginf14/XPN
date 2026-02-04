package es.udc.fic.xpn.stock;

import java.util.Objects;

public class Stock {

    private Integer warehouseId;
    private Integer productSku;
    private Integer quantity;
    private Integer maxQuantity;
    private Integer minQuantity;

    public Stock(Integer warehouseId, Integer productSku, Integer quantity, Integer maxQuantity, Integer minQuantity) {
        this.warehouseId = warehouseId;
        this.productSku = productSku;
        this.quantity = quantity;
        this.maxQuantity = maxQuantity;
        this.minQuantity = minQuantity;
    }

    public Stock() {}

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return stock.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(warehouseId, productSku, quantity, maxQuantity, minQuantity);
    }
}
