package es.udc.fic.xpn.stock;

import java.util.List;
import java.util.Optional;

public interface StockDao {

    public boolean create(Stock stock);

    public Optional<Stock> findById(Integer warehouseId, Integer productSku);

    public List<Stock> findByProduct(Integer productSku);

    public void update(Stock stock);
}
