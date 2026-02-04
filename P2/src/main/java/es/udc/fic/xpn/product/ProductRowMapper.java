package es.udc.fic.xpn.product;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();

        product.setSku(rs.getInt("sku"));
        product.setName(rs.getString("name"));
        product.setType(rs.getString("type"));

        return product;
    }
}
