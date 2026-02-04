package es.udc.fic.xpn.reception;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceptionRowMapper implements RowMapper<Reception> {
    @Override
    public Reception mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Reception(
                rs.getInt("id"),
                rs.getInt("quantity"),
                rs.getString("supplier"),
                rs.getDate("date"),
                rs.getInt("warehouse_id"),
                rs.getInt("product_sku")
        );
    }
}
