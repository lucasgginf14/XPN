package es.udc.fic.xpn.product;

public class ProductDto {

    private Integer sku;
    private String name;
    private String type;
    private Integer freeWarehouseId;

    public ProductDto() {
    }

    public ProductDto(Integer sku, String name, String type, Integer freeWarehouseId) {
        this.sku = sku;
        this.name = name;
        this.type = type;
        this.freeWarehouseId = freeWarehouseId;
    }

    public Integer getSku() {
        return sku;
    }

    public void setSku(Integer sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getFreeWarehouseId() {
        return freeWarehouseId;
    }

    public void setFreeWarehouseId(Integer freeWarehouseId) {
        this.freeWarehouseId = freeWarehouseId;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "sku=" + sku +
                ", name=" + name +
                ", type=" + type +
                ", freeWarehouseId=" + freeWarehouseId +
                '}';
    }
}
