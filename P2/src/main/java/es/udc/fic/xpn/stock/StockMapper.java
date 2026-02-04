package es.udc.fic.xpn.stock;

public class StockMapper {

    public static StockDto entityToDto(Stock stock) {
        StockDto stockDto = new StockDto();

        stockDto.setWarehouseId(stock.getWarehouseId());
        stockDto.setProductSku(stock.getProductSku());
        stockDto.setQuantity(stock.getQuantity());
        stockDto.setMaxQuantity(stock.getMaxQuantity());
        stockDto.setMinQuantity(stock.getMinQuantity());

        return stockDto;
    }

    public static Stock dtoToEntity(StockDto stockDto) {
        Stock stock = new Stock();

        stock.setWarehouseId(stockDto.getWarehouseId());
        stock.setProductSku(stockDto.getProductSku());
        stock.setQuantity(stockDto.getQuantity());
        stock.setMaxQuantity(stockDto.getMaxQuantity());
        stock.setMinQuantity(stockDto.getMinQuantity());

        return stock;
    }
}
