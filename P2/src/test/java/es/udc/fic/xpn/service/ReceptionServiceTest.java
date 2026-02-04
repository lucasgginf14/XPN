package es.udc.fic.xpn.service;

import es.udc.fic.xpn.DeleteFunctions;
import es.udc.fic.xpn.product.Product;
import es.udc.fic.xpn.product.ProductService;
import es.udc.fic.xpn.reception.Reception;
import es.udc.fic.xpn.reception.ReceptionDto;
import es.udc.fic.xpn.reception.ReceptionService;
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
public class ReceptionServiceTest {

    @Autowired
    private ReceptionService receptionService;

    @Autowired
    private ProductService productService;

    @Autowired
    private StockService stockService;

    @Autowired
    private DeleteFunctions delete;

    @Test
    public void createAndFindReception() {

        ReceptionDto r = new ReceptionDto(-1, Date.valueOf(LocalDate.parse("1900-10-20")),
                10, 1, -1,
                "Afgiahei", "Mesita de noche",
                "Antonio", 20, 5);
        r.setId(receptionService.create(r));

        Integer r2 = -1;

        try {
            Optional<Product> p = productService.findBySku(-1);
            assert(p.isPresent());

            Optional<Reception> res = receptionService.findById(r.getId());
            assert(res.isPresent());

            assertEquals(r.getId(), res.get().getId());
            assertEquals(r.getDate(), res.get().getDate());
            assertEquals(r.getProductSku(), res.get().getProductSku());
            assertEquals(r.getQuantity(), res.get().getQuantity());
            assertEquals(r.getSupplier(), res.get().getSupplier());
            assertEquals(r.getWarehouseId(), res.get().getWarehouseId());

            Optional<Stock> stock = stockService.findById(1, -1);
            assert(stock.isPresent());
            assertEquals(10, stock.get().getQuantity());

            r2 = receptionService.create(r);

            Optional<Stock> stock_res = stockService.findById(1, -1);
            assert(stock_res.isPresent());
            assertEquals(20, stock_res.get().getQuantity());

        } finally {

            if (r.getId() != -1) {
                delete.deleteReceptionById(r.getId());
            }

            if (r2 != -1) {
                delete.deleteReceptionById(r2);
            }

            delete.deleteStockById(1, -1);
            delete.deleteProductById(-1);
        }
    }
}
