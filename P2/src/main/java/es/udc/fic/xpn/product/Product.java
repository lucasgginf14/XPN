package es.udc.fic.xpn.product;

import java.util.Objects;

public class Product {

    private Integer sku;
    private String name;
    private String type;

    public Product() {
    }

    public Product(Integer sku, String name, String type) {
        this.sku = sku;
        this.name = name;
        this.type = type;
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

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return product.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, name, type);
    }
}
