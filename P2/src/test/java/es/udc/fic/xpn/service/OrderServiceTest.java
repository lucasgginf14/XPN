package es.udc.fic.xpn.service;

import es.udc.fic.xpn.DeleteFunctions;
import es.udc.fic.xpn.order.Order;
import es.udc.fic.xpn.order.OrderDto;
import es.udc.fic.xpn.order.OrderService;
import es.udc.fic.xpn.product.Product;
import es.udc.fic.xpn.product.ProductService;
import es.udc.fic.xpn.stock.Stock;
import es.udc.fic.xpn.stock.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private StockService stockService;

    @Autowired
    private DeleteFunctions delete;

    @Test
    public void createAndFindOrderReceive() {

        OrderDto o = new OrderDto(-1, Date.valueOf(LocalDate.parse("1900-10-20")), 10, 1, -1, "Afgiahei", "Mesita de noche", 20, 10);
        o.setId(orderService.create(o));

        Integer o2 = -1;

        try {
            Optional<Product> p = productService.findBySku(-1);
            assert(p.isPresent());

            Optional<Order> res = orderService.findById(o.getId());
            assert(res.isPresent());

            assertEquals(o.getId(), res.get().getId());
            assertEquals(o.getQuantity(), res.get().getQuantity());
            assertEquals(o.getDate(), res.get().getDate());
            assertEquals(o.getProductSku(), res.get().getProductSku());
            assertEquals(o.getDestinationWarehouseId(), res.get().getDestinationWarehouseId());
            assertEquals(o.getOriginWarehouseId(), res.get().getOriginWarehouseId());

            Optional<Stock> stock = stockService.findById(1, -1);
            assert(stock.isPresent());
            assertEquals(10, stock.get().getQuantity());

            o2 = orderService.create(o);

            Optional<Stock> stockAdded = stockService.findById(1, -1);
            assert(stockAdded.isPresent());
            assertEquals(20, stockAdded.get().getQuantity());


        } finally {

            if(o.getId() != -1){
                delete.deleteOrderById(o.getId());
            }

            if (o2 != -1){
                delete.deleteOrderById(o2);
            }

            delete.deleteStockById(1, -1);
            delete.deleteProductById(-1);
        }
    }

    @Test
    public void createAndFindOrderTransfer() {
        Product p = new Product(-1, "Afgiahei", "Mesita de noche");
        p.setSku(productService.create(p).getSku());

        Stock s1 = new Stock(1, -1, 10, 20, 5);
        stockService.create(s1);

        Stock s2 = new Stock(2, -1, 20, 20, 5);
        stockService.create(s2);

        OrderDto o = new OrderDto(-1, Date.valueOf(LocalDate.parse("1900-10-20")), 10, 1, -1, 2);
        o.setId(orderService.create(o));

        try {
            Optional<Order> res = orderService.findById(o.getId());
            Optional<Stock> stockAdded = stockService.findById(1, -1);
            Optional<Stock> stockRemoved = stockService.findById(2, -1);
            assert(res.isPresent());
            assert(stockAdded.isPresent());
            assert(stockRemoved.isPresent());

            assertEquals(o.getId(), res.get().getId());
            assertEquals(o.getQuantity(), res.get().getQuantity());
            assertEquals(o.getDate(), res.get().getDate());
            assertEquals(o.getProductSku(), res.get().getProductSku());
            assertEquals(o.getDestinationWarehouseId(), res.get().getDestinationWarehouseId());
            assertEquals(o.getOriginWarehouseId(), res.get().getOriginWarehouseId());
            assertEquals(stockAdded.get().getQuantity(), o.getQuantity() + s1.getQuantity());
            assertEquals(stockRemoved.get().getQuantity(), s2.getQuantity() - o.getQuantity());

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
