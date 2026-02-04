package es.udc.fic.xpn.dao;

import es.udc.fic.xpn.DeleteFunctions;
import es.udc.fic.xpn.product.Product;
import es.udc.fic.xpn.product.ProductDao;
import es.udc.fic.xpn.reception.Reception;
import es.udc.fic.xpn.reception.ReceptionDao;
import es.udc.fic.xpn.stock.Stock;
import es.udc.fic.xpn.stock.StockDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReceptionDaoTest {

    @Autowired
    private StockDao stockDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ReceptionDao receptionDao;

    @Autowired
    private DeleteFunctions delete;

    @Test
    public void createAndFindReception() {

        Product p = new Product(-1, "Afgiahei", "Mesita de noche");
        p.setSku(productDao.create(p));

        Stock s = new Stock(1, -1, 10, 20, 5);
        stockDao.create(s);

        Reception r = new Reception(-1,10,"Antonio", Date.valueOf(LocalDate.parse("1900-10-20")), 1, -1);
        r.setId(receptionDao.create(r));

        try {
            Optional<Reception> res = receptionDao.findById(r.getId());
            assert(res.isPresent());

            assertEquals(r.getId(), res.get().getId());
            assertEquals(r.getDate(), res.get().getDate());
            assertEquals(r.getProductSku(), res.get().getProductSku());
            assertEquals(r.getQuantity(), res.get().getQuantity());
            assertEquals(r.getSupplier(), res.get().getSupplier());
            assertEquals(r.getWarehouseId(), res.get().getWarehouseId());

        } finally {

            if (r.getId() != -1) {
                delete.deleteReceptionById(r.getId());
            }

            delete.deleteStockById(1, -1);
            delete.deleteProductById(-1);
        }
    }
}
