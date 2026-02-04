package es.udc.fic.xpn.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("warehouses/{warehouseId}/products")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/{productSku}")
    public ResponseEntity<StockDto> findById (@PathVariable Integer warehouseId, @PathVariable Integer productSku) {
        Optional<Stock> stock = stockService.findById(warehouseId, productSku);

        if (stock.isPresent()) {
            return ResponseEntity.ok(StockMapper.entityToDto(stock.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{productSku}")
    public ResponseEntity<StockDto> update (@PathVariable Integer warehouseId, @PathVariable Integer productSku, @RequestBody StockDto stockDto) {
        Optional<Stock> stock = stockService.findById(warehouseId, productSku);

        if (stock.isPresent()) {
            stockDto.setWarehouseId(warehouseId);
            stockDto.setProductSku(productSku);

            stockService.update(StockMapper.dtoToEntity(stockDto));
            return ResponseEntity.ok(StockMapper.entityToDto(stockService.findById(warehouseId, productSku).get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{productSku}")
    public ResponseEntity<StockDto> changeQuantity (@PathVariable Integer warehouseId, @PathVariable Integer productSku, @RequestParam Integer quantity) {
        Optional<Stock> stock = stockService.findById(warehouseId, productSku);
        if (stock.isPresent()) {
            if (stockService.changeQuantity(stock.get(), quantity)){
                return ResponseEntity.ok(StockMapper.entityToDto(stockService.findById(warehouseId, productSku).get()));
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
