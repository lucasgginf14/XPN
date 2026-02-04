package es.udc.fic.xpn.order;

import es.udc.fic.xpn.product.Product;
import es.udc.fic.xpn.product.ProductService;
import es.udc.fic.xpn.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("transfers")
public class OrderController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private StockService stockService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> findById(@PathVariable Integer id) {
        Optional<Order> order = orderService.findById(id);

        if (order.isPresent()) {
            return new ResponseEntity<>(OrderMapper.entityToDto(order.get()), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody OrderDto orderDto) {

        Integer id = null;
        Integer productSku = orderDto.getProductSku();
        Integer originWarehouseId = orderDto.getOriginWarehouseId();

        if (originWarehouseId != null && stockService.findById(originWarehouseId, productSku).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        id = orderService.create(orderDto);

        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(OrderMapper.entityToDto(Objects.requireNonNull(orderService.findById(id).orElse(null))), HttpStatus.CREATED);

    }
}
