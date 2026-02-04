package es.udc.fic.xpn.stock;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StockRowMapper implements RowMapper<Stock> {

    public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
        Stock stock = new Stock();

        stock.setWarehouseId(rs.getInt("warehouse_id"));
        stock.setProductSku(rs.getInt("product_sku"));
        stock.setQuantity(rs.getInt("quantity"));
        stock.setMaxQuantity(rs.getInt("max_quantity"));
        stock.setMinQuantity(rs.getInt("min_quantity"));

        return stock;
    }
}
