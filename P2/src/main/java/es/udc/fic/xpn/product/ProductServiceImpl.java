package es.udc.fic.xpn.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    
    @Override
    public Product create(Product product) {
        productDao.create(product);
        return product;
    }

    @Override
    public Optional<Product> findBySku(Integer sku) {
        return productDao.findBySku(sku);
    }
}
