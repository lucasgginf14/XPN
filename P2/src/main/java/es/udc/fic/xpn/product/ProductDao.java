package es.udc.fic.xpn.product;

import java.util.Optional;

public interface ProductDao {

        public Integer create(Product product);

        public Optional<Product> findBySku(Integer sku);
}
