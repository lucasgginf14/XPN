package es.udc.fic.xpn.product;

import es.udc.fic.xpn.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductService productService;

    @GetMapping("/{sku}")
    public ResponseEntity<ProductDto> findBySku(@PathVariable Integer sku) {

        Optional<Product> product = productService.findBySku(sku);

        if (product.isPresent()) {
            ProductDto productDto = ProductMapper.entityToDto(product.get());

            productDto.setFreeWarehouseId(stockService.findFreeWarehouse(sku).orElse(null));

            return new ResponseEntity<>(productDto, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
