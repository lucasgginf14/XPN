package es.udc.fic.xpn.service;

import es.udc.fic.xpn.DeleteFunctions;
import es.udc.fic.xpn.product.Product;
import es.udc.fic.xpn.product.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private DeleteFunctions delete;

    @Test
    public void createAndFindProduct() {

        Product p = new Product(-1, "Afgiahei", "Mesita de noche");
        p.setSku(productService.create(p).getSku());

        try {
            Optional<Product> res = productService.findBySku(p.getSku());
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
