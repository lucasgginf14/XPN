package es.udc.fic.xpn.warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WarehouseDaoImpl implements WarehouseDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Warehouse> findById(Integer id) {

        String sql = "SELECT * FROM Warehouse WHERE id = ?";

        try {
            Warehouse warehouse = jdbcTemplate.queryForObject(sql, new WarehouseRowMapper(), id);
            return Optional.ofNullable(warehouse);
        } catch(EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }
}
