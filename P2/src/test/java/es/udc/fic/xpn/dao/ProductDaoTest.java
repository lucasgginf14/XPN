package es.udc.fic.xpn.dao;

import es.udc.fic.xpn.DeleteFunctions;
import es.udc.fic.xpn.product.Product;
import es.udc.fic.xpn.product.ProductDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductDaoTest {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private DeleteFunctions delete;

    @Test
    public void createAndFindProduct() {

        Product p = new Product(-1, "Afgiahei", "Mesita de noche");
        p.setSku(productDao.create(p));

        try {
            Optional<Product> res = productDao.findBySku(p.getSku());
            assert(res.isPresent());

            assertEquals(p.getSku(), res.get().getSku());
            assertEquals(p.getName(), res.get().getName());
            assertEquals(p.getType(), res.get().getType());

        } finally {

            if( p.getSku() != null )
                delete.deleteProductById(-1);
        }
    }
}