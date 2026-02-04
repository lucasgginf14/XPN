package es.udc.fic.xpn.dao;

import es.udc.fic.xpn.DeleteFunctions;
import es.udc.fic.xpn.product.Product;
import es.udc.fic.xpn.product.ProductDao;
import es.udc.fic.xpn.stock.Stock;
import es.udc.fic.xpn.stock.StockDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@SpringBootTest
public class StockDaoTest {

    @Autowired
    private StockDao stockDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private DeleteFunctions delete;

    @Test
    public void createAndFindStock() {

        Product p = new Product(-1, "Afgiahei", "Mesita de noche");
        p.setSku(productDao.create(p));

        Stock s = new Stock(1, -1, 10, 20, 5);
        stockDao.create(s);

        Optional<Stock> res = stockDao.findById(1, -1);

        try {
            assert(res.isPresent());

            assertEquals(s.getWarehouseId(), res.get().getWarehouseId());
            assertEquals(s.getProductSku(), res.get().getProductSku());
            assertEquals(s.getMaxQuantity(), res.get().getMaxQuantity());
            assertEquals(s.getMinQuantity(), res.get().getMinQuantity());
            assertEquals(s.getQuantity(), res.get().getQuantity());

        } finally {

            if (res.isPresent()) {
                delete.deleteStockById(1, -1);
            }

            delete.deleteProductById(-1);
        }
    }

    @Test
    public void findByProductStock() {

        Product p = new Product(-1, "Afgiahei", "Mesita de noche");
        p.setSku(productDao.create(p));

        Stock s1 = new Stock(1, -1, 10, 20, 5);
        Stock s2 = new Stock(2, -1, 10, 12, 5);
        Stock s3 = new Stock(3, -1, 10, 100, 5);
        stockDao.create(s1);
        stockDao.create(s2);
        stockDao.create(s3);

        List<Stock> stocks = List.of(s1, s2, s3);

        List<Stock> res = stockDao.findByProduct(-1);

        try {
            assertIterableEquals(stocks, res);

        } finally {

            delete.deleteStockById(1, -1);
            delete.deleteStockById(2, -1);
            delete.deleteStockById(3, -1);
            delete.deleteProductById(-1);
        }
    }

    @Test
    public void updateStock() {

        Product p = new Product(-1, "Afgiahei", "Mesita de noche");
        p.setSku(productDao.create(p));

        Stock s = new Stock(1, -1, 10, 20, 5);
        stockDao.create(s);

        s.setQuantity(20);
        s.setMaxQuantity(40);
        s.setMinQuantity(10);
        stockDao.update(s);

        try {
            Optional<Stock> res = stockDao.findById(1, -1);
            assert(res.isPresent());

            assertEquals(20, res.get().getQuantity());
            assertEquals(40, res.get().getMaxQuantity());
            assertEquals(10, res.get().getMinQuantity());

        } finally {
            delete.deleteStockById(1, -1);
            delete.deleteProductById(-1);
        }
    }
}
