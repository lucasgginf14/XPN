package es.udc.fic.xpn.order;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {

    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();

        order.setId(rs.getInt("id"));
        order.setDate(rs.getDate("date"));
        order.setQuantity(rs.getInt("quantity"));
        order.setOriginWarehouseId(rs.getInt("origin_warehouse_id"));
        order.setDestinationWarehouseId(rs.getInt("destination_warehouse_id"));
        order.setProductSku(rs.getInt("product_sku"));

        return order;
    }
}
