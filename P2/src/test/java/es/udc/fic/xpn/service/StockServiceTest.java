package es.udc.fic.xpn.service;

import es.udc.fic.xpn.DeleteFunctions;
import es.udc.fic.xpn.product.Product;
import es.udc.fic.xpn.product.ProductService;
import es.udc.fic.xpn.stock.Stock;
import es.udc.fic.xpn.stock.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StockServiceTest {

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductService productService;

    @Autowired
    private DeleteFunctions delete;

    @Test
    public void createAndFindStock() {
        Product p = new Product(-1, "Afgiahei", "Mesita de noche");
        p.setSku(productService.create(p).getSku());

        Stock s = new Stock(1, -1, 10, 20, 5);
        stockService.create(s);

        Optional<Stock> res = stockService.findById(1, -1);

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
    public void findFreeWarehouseStock() {

        Product p1 = new Product(-1, "Afgiahei", "Mesita de noche");
        Product p2 = new Product(-2, "Afgiahei", "Mesita de noche");

        p1.setSku(productService.create(p1).getSku());
        p2.setSku(productService.create(p2).getSku());

        Stock s1 = new Stock(1, -1, 10, 20, 5);
        Stock s2 = new Stock(2, -1, 10, 12, 5);
        Stock s3 = new Stock(3, -1, 10, 100, 5);

        stockService.create(s1);
        stockService.create(s2);
        stockService.create(s3);

        Optional<Integer> res1 = stockService.findFreeWarehouse(-1);
        Optional<Integer> res2 = stockService.findFreeWarehouse(-2);

        try {
            res1.ifPresent(integer -> assertEquals(s3.getWarehouseId(), integer));
            res2.ifPresent(integer -> assertEquals(s1.getWarehouseId(), integer));
        } finally {

            delete.deleteStockById(1, -1);
            delete.deleteStockById(2, -1);
            delete.deleteStockById(3, -1);
            delete.deleteProductById(-1);
            delete.deleteProductById(-2);
        }
    }

    @Test
    public void updateStock() {

        Product p = new Product(-1, "Afgiahei", "Mesita de noche");
        p.setSku(productService.create(p).getSku());

        Stock s = new Stock(1, -1, 10, 20, 5);
        stockService.create(s);

        s.setQuantity(20);
        s.setMaxQuantity(40);
        s.setMinQuantity(10);
        stockService.update(s);

        try {
            Optional<Stock> res = stockService.findById(1, -1);
            assert(res.isPresent());

            assertEquals(20, res.get().getQuantity());
            assertEquals(40, res.get().getMaxQuantity());
            assertEquals(10, res.get().getMinQuantity());

        } finally {
            delete.deleteStockById(1, -1);
            delete.deleteProductById(-1);
        }
    }

    @Test
    public void changeQuantityStock() {

        Product p = new Product(-1, "Afgiahei", "Mesita de noche");
        p.setSku(productService.create(p).getSku());

        Stock s = new Stock(1, -1, 10, 40, 10);
        stockService.create(s);

        stockService.changeQuantity(s,10);

        try {
            Optional<Stock> res = stockService.findById(1, -1);
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
