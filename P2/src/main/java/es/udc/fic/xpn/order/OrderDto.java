package es.udc.fic.xpn.order;

import java.util.Date;

public class OrderDto {

    private Integer id;
    private Date date;
    private Integer quantity;
    private Integer originWarehouseId;
    private Integer destinationWarehouseId;
    private Integer productSku;

    private String productName;
    private String productType;

    private Integer maxQuantity;
    private Integer minQuantity;

    public OrderDto(){}

    public OrderDto (Integer id, Date date, Integer quantity, Integer destinationWarehouseId,
                     Integer productSku){
        this.id = id;
        this.date = date;
        this.quantity = quantity;
        this.destinationWarehouseId = destinationWarehouseId;
        this.productSku = productSku;
    }

    public OrderDto(Integer id, Date date, Integer quantity, Integer destinationWarehouseId,
                    Integer productSku, Integer originWarehouseId){
        this(id, date, quantity, destinationWarehouseId, productSku);
        this.originWarehouseId = originWarehouseId;
    }

    public OrderDto(Integer id, Date date, Integer quantity, Integer destinationWarehouseId,
                    Integer productSku, String productName, String productType,
                    Integer maxQuantity, Integer minQuantity){

        this(id, date, quantity, destinationWarehouseId, productSku);
        this.productName = productName;
        this.productType = productType;
        this.maxQuantity = maxQuantity;
        this.minQuantity = minQuantity;
    }

    public OrderDto(Integer id, Date date, Integer quantity, Integer destinationWarehouseId,
                    Integer productSku, String productName, String productType,
                    Integer maxQuantity, Integer minQuantity, Integer originWarehouseId){
        this(id, date, quantity, destinationWarehouseId, productSku, productName, productType, maxQuantity, minQuantity);
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
        return "TransferDto{" +
                "id=" + id +
                ", date=" + date +
                ", quantity=" + quantity +
                ", originWarehouseId=" + originWarehouseId +
                ", destinationWarehouseId=" + destinationWarehouseId +
                ", productSku=" + productSku +
                '}';
    }
}
