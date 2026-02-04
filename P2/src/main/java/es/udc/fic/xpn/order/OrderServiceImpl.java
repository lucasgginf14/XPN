package es.udc.fic.xpn.order;

import es.udc.fic.xpn.product.Product;
import es.udc.fic.xpn.product.ProductService;
import es.udc.fic.xpn.stock.Stock;
import es.udc.fic.xpn.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private StockService stockService;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductService productService;

    @Override
    public Optional<Order> findById(Integer id) {
        return orderDao.findById(id);
    }

    private Stock createStock(OrderDto orderDto){

        Stock stock = new Stock();

        stock.setMinQuantity(orderDto.getMinQuantity());
        stock.setMaxQuantity(orderDto.getMaxQuantity());
        stock.setWarehouseId(orderDto.getDestinationWarehouseId());
        stock.setProductSku(orderDto.getProductSku());
        stock.setQuantity(0);

        return stock;
    }

    @Override
    public Integer create(OrderDto orderDto){

        Integer quantity = orderDto.getQuantity();
        Integer productSku = orderDto.getProductSku();
        Integer destinationWarehouseId = orderDto.getDestinationWarehouseId();
        Integer originWarehouseId = orderDto.getOriginWarehouseId();
        Order order = new Order();

        if (productService.findBySku(productSku).isEmpty()){
            Product product = new Product();

            product.setSku(productSku);
            product.setName(orderDto.getProductName());
            product.setType(orderDto.getProductType());

            productService.create(product);
        }

        Optional<Stock> originStock = stockService.findById(originWarehouseId, productSku);

        Optional<Stock> destinationStock = stockService.findById(destinationWarehouseId, productSku);

        Stock stock = destinationStock.orElseGet(() -> stockService.create(createStock(orderDto)));

        if(originStock.isEmpty()){
            stockService.changeQuantity(stock, quantity);
            order.setOriginWarehouseId(null);
        } else {
            if (originStock.get().getQuantity() < quantity) {
                return null;
            }
            stockService.changeQuantity(stock, +quantity);
            stockService.changeQuantity(originStock.get(), -quantity);
            order.setOriginWarehouseId(originWarehouseId);
        }

        order.setDestinationWarehouseId(destinationWarehouseId);
        order.setProductSku(productSku);
        order.setQuantity(quantity);
        order.setDate(orderDto.getDate());

        return orderDao.create(order);
    }
}
