package es.udc.fic.xpn.dao;

import es.udc.fic.xpn.DeleteFunctions;
import es.udc.fic.xpn.order.Order;
import es.udc.fic.xpn.order.OrderDao;
import es.udc.fic.xpn.product.Product;
import es.udc.fic.xpn.product.ProductDao;
import es.udc.fic.xpn.stock.Stock;
import es.udc.fic.xpn.stock.StockDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OrderDaoTest {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private StockDao stockDao;

    @Autowired
    private DeleteFunctions delete;

    @Test
    public void createAndFindOrderReceive() {
        Product p = new Product(-1, "Afgiahei", "Mesita de noche");
        p.setSku(productDao.create(p));

        Stock s = new Stock(1, -1, 10, 20, 5);
        stockDao.create(s);

        Order o = new Order(-1, Date.valueOf(LocalDate.parse("1900-10-20")), 10, 1, -1);
        o.setId(orderDao.create(o));

        try {
            Optional<Order> res = orderDao.findById(o.getId());
            assert(res.isPresent());

            assertEquals(o.getId(), res.get().getId());
            assertEquals(o.getQuantity(), res.get().getQuantity());
            assertEquals(o.getDate(), res.get().getDate());
            assertEquals(o.getProductSku(), res.get().getProductSku());
            assertEquals(o.getDestinationWarehouseId(), res.get().getDestinationWarehouseId());
            assertEquals(o.getOriginWarehouseId(), res.get().getOriginWarehouseId());

        } finally {

            if(o.getId() != -1){
                delete.deleteOrderById(o.getId());
            }

            delete.deleteStockById(1, -1);
            delete.deleteProductById(-1);
        }
    }

    @Test
    public void createAndFindOrderTransfer() {
        Product p = new Product(-1, "Afgiahei", "Mesita de noche");
        p.setSku(productDao.create(p));

        Stock s1 = new Stock(1, -1, 10, 20, 5);
        stockDao.create(s1);

        Stock s2 = new Stock(2, -1, 20, 20, 5);
        stockDao.create(s2);

        Order o = new Order(-1, Date.valueOf(LocalDate.parse("1900-10-20")), 10, 1, -1, 2);
        o.setId(orderDao.create(o));

        try {
            Optional<Order> res = orderDao.findById(o.getId());
            assert(res.isPresent());

            assertEquals(o.getId(), res.get().getId());
            assertEquals(o.getQuantity(), res.get().getQuantity());
            assertEquals(o.getDate(), res.get().getDate());
            assertEquals(o.getProductSku(), res.get().getProductSku());
            assertEquals(o.getDestinationWarehouseId(), res.get().getDestinationWarehouseId());
            assertEquals(o.getOriginWarehouseId(), res.get().getOriginWarehouseId());

        } finally {

            if(o.getId() != -1){
                delete.deleteOrderById(o.getId());
            }

            delete.deleteStockById(1, -1);
            delete.deleteStockById(2, -1);
            delete.deleteProductById(-1);
        }
    }
}
