package es.udc.fic.xpn.stock;

import java.util.Optional;

public interface StockService {

    public Stock create (Stock stock);

    public Optional<Stock> findById (Integer warehouseId, Integer productSku);

    public void update (Stock stock);

    public Optional<Integer> findFreeWarehouse (Integer productSku);

    public boolean changeQuantity (Stock stock, Integer quantity);
}
