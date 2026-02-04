package es.udc.fic.xpn.reception;

import java.util.Date;

public class ReceptionDto {

    private Integer id;
    private Date date;
    private Integer quantity;
    private Integer warehouseId;
    private Integer productSku;
    private String productName;
    private String productType;
    private String supplier;

    private Integer maxQuantity;
    private Integer minQuantity;

    public ReceptionDto() {}

    public ReceptionDto(Integer id, Date date, Integer quantity, Integer warehouseId, Integer productSku, String supplier) {
        this.id = id;
        this.date = date;
        this.quantity = quantity;
        this.warehouseId = warehouseId;
        this.productSku = productSku;
        this.supplier = supplier;
    }

    public ReceptionDto(Integer id, Date date, Integer quantity, Integer warehouseId,
                        Integer productSku, String productName, String productType,
                        String supplierName, Integer maxQuantity, Integer minQuantity) {
        this(id, date, quantity, warehouseId, productSku, supplierName);
        this.productName = productName;
        this.productType = productType;
        this.maxQuantity = maxQuantity;
        this.minQuantity = minQuantity;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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
        return "ReceptionDto{" +
                "id=" + id +
                ", date=" + date +
                ", quantity=" + quantity +
                ", warehouseId=" + warehouseId +
                ", productSku=" + productSku +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", supplier='" + supplier + '\'' +
                ", maxQuantity=" + maxQuantity +
                ", minQuantity=" + minQuantity +
                '}';
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
