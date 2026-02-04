package es.udc.fic.xpn.product;

import java.util.Optional;

public interface ProductService {

    public Product create(Product product);

    public Optional<Product> findBySku(Integer sku);
}
