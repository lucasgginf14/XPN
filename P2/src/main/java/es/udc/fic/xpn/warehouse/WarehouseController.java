package es.udc.fic.xpn.warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseDto> findById(@PathVariable Integer id) {
        Optional<Warehouse> warehouse = warehouseService.findById(id);

        if (warehouse.isPresent()) {
            return new ResponseEntity<>(WarehouseMapper.entityToDto(warehouse.get()), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
