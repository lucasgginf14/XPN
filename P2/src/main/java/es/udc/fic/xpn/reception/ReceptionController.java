package es.udc.fic.xpn.reception;

import es.udc.fic.xpn.product.Product;
import es.udc.fic.xpn.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("receptions")
public class ReceptionController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ReceptionService receptionService;

    @GetMapping("/{id}")
    public ResponseEntity<ReceptionDto> findById(@PathVariable Integer id) {
        Optional<Reception> reception = receptionService.findById(id);

        if (reception.isPresent()) {
            return new ResponseEntity<>(ReceptionMapper.entityToDto(reception.get()), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ReceptionDto> create(@RequestBody ReceptionDto receptionDto) {

        Integer id = receptionService.create(receptionDto);

        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(ReceptionMapper.entityToDto(
                Objects.requireNonNull(receptionService.findById(id).orElse(null))), HttpStatus.CREATED);
    }
}
