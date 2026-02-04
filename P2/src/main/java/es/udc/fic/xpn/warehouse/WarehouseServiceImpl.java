package es.udc.fic.xpn.warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseDao warehouseDao;

    @Override
    public Optional<Warehouse> findById(Integer id) {
        return warehouseDao.findById(id);
    }
}
