package es.udc.fic.xpn.service;

import es.udc.fic.xpn.warehouse.Warehouse;
import es.udc.fic.xpn.warehouse.WarehouseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WarehouseServiceTest {

    @Autowired
    private WarehouseService warehouseService;

    @Test
    public void findById(){
        Warehouse w = new Warehouse();
        w.setId(1);
        w.setName("A Coruña");
        w.setLocation("Polígono de POCOMACO");

        Optional<Warehouse> res = warehouseService.findById(1);
        assert(res.isPresent());

        assertEquals(w.getId(), res.get().getId());
        assertEquals(w.getName(), res.get().getName());
        assertEquals(w.getLocation(), res.get().getLocation());
    }
}
