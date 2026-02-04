package es.udc.fic.xpn.warehouse;

import java.util.Optional;

public interface WarehouseService {

    public Optional<Warehouse> findById(Integer id);
}
