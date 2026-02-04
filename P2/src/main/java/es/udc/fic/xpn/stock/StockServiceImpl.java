package es.udc.fic.xpn.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockDao stockDao;

    @Override
    public Stock create(Stock stock) {
        if (stockDao.create(stock)){
            return stock;
        }
        return null;
    }

    @Override
    public Optional<Stock> findById(Integer warehouseId, Integer productSku) {
        return stockDao.findById(warehouseId, productSku);
    }

    @Override
    public void update(Stock stock) {
        stockDao.update(stock);
    }

    @Override
    public Optional<Integer> findFreeWarehouse(Integer productSku) {

        List<Stock> stocks = stockDao.findByProduct(productSku);

        Integer freeWarehouseId = null;
        int maxFreeQuantity = 0;

        for (Stock stock : stocks) {
            int freeQuantity = stock.getMaxQuantity() - stock.getQuantity();
            if (freeQuantity > maxFreeQuantity) {
                maxFreeQuantity = freeQuantity;
                freeWarehouseId = stock.getWarehouseId();
            }
        }

        return Optional.ofNullable(freeWarehouseId);
    }

    @Override
    public boolean changeQuantity(Stock stock, Integer quantity) {

        Optional<Stock> stockOptional = stockDao.findById(stock.getWarehouseId(), stock.getProductSku());

        if (stockOptional.isEmpty()) {
            return false;
        }

        if (stock.getQuantity() + quantity < 0) {
            return false;
        }

        stock.setQuantity(stock.getQuantity() + quantity);

        stockDao.update(stock);

        return true;
    }
}
