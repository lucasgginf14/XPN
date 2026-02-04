package es.udc.fic.xpn.dao;

import es.udc.fic.xpn.warehouse.Warehouse;
import es.udc.fic.xpn.warehouse.WarehouseDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

@SpringBootTest
public class WarehouseDaoTest {

    @Autowired
    private WarehouseDao warehouseDao;

    @Test
    public void findById() {
        Warehouse w = new Warehouse();
        w.setId(1);
        w.setName("A Coruña");
        w.setLocation("Polígono de POCOMACO");

        Optional<Warehouse> res = warehouseDao.findById(1);
        assert(res.isPresent());

        assertEquals(w.getId(), res.get().getId());
        assertEquals(w.getName(), res.get().getName());
        assertEquals(w.getLocation(), res.get().getLocation());
    }
}
