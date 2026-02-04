package es.udc.fic.xpn.warehouse;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WarehouseRowMapper implements RowMapper<Warehouse> {

    public Warehouse mapRow(ResultSet rs, int rowNum) throws SQLException {
        Warehouse warehouse = new Warehouse();

        warehouse.setId(rs.getInt("id"));
        warehouse.setName(rs.getString("name"));
        warehouse.setLocation(rs.getString("location"));

        return warehouse;
    }
}
