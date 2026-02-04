package es.udc.fic.xpn.warehouse;

import java.util.Optional;

public interface WarehouseDao {

    public Optional<Warehouse> findById(Integer id);
}
