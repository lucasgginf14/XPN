package es.udc.fic.xpn.reception;

import es.udc.fic.xpn.product.Product;
import es.udc.fic.xpn.product.ProductService;
import es.udc.fic.xpn.stock.Stock;
import es.udc.fic.xpn.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReceptionServiceImpl implements ReceptionService {

    @Autowired
    private ReceptionDao receptionDao;

    @Autowired
    private ProductService productService;

    @Autowired
    private StockService stockService;

    private Stock createStock(ReceptionDto dto){

        Stock stock = new Stock();

        stock.setMinQuantity(dto.getMinQuantity());
        stock.setMaxQuantity(dto.getMaxQuantity());
        stock.setWarehouseId(dto.getWarehouseId());
        stock.setProductSku(dto.getProductSku());
        stock.setQuantity(0);

        return stock;
    }

    @Override
    public Integer create(ReceptionDto dto) {
        Integer quantity = dto.getQuantity();
        Integer productSku = dto.getProductSku();
        Integer warehouseId = dto.getWarehouseId();
        Reception reception = new Reception();

        if (productService.findBySku(dto.getProductSku()).isEmpty()) {
            Product product = new Product();

            product.setSku(dto.getProductSku());
            product.setName(dto.getProductName());
            product.setType(dto.getProductType());

            productService.create(product);
        }

        Stock stock = stockService.findById(warehouseId, productSku).orElseGet(() -> stockService.create(createStock(dto)));

        reception.setWarehouseId(warehouseId);
        reception.setProductSku(productSku);
        reception.setQuantity(quantity);
        reception.setDate(dto.getDate());
        reception.setSupplier(dto.getSupplier());

        Integer id = receptionDao.create(reception);

        if (id == null){
            return null;
        }else{
            stockService.changeQuantity(stock, +quantity);
            return id;
        }
    }

    @Override
    public Optional<Reception> findById(Integer id) {
        return receptionDao.findById(id);
    }
}
