package es.udc.fic.xpn.stock;

public class StockDto {

    private Integer warehouseId;
    private Integer productSku;
    private Integer quantity;
    private Integer maxQuantity;
    private Integer minQuantity;

    public StockDto(Integer warehouseId, Integer productSku, Integer quantity, Integer maxQuantity, Integer minQuantity) {
        this.warehouseId = warehouseId;
        this.productSku = productSku;
        this.quantity = quantity;
        this.maxQuantity = maxQuantity;
        this.minQuantity = minQuantity;
    }

    public StockDto() {}

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
    public String toString() {
        return "StockDto{" +
                "warehouseId=" + warehouseId +
                ", productSku=" + productSku +
                ", quantity=" + quantity +
                ", maxQuantity=" + maxQuantity +
                ", minQuantity=" + minQuantity +
                "}";
    }
}
