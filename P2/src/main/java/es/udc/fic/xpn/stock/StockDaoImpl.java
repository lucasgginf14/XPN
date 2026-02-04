package es.udc.fic.xpn.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StockDaoImpl implements StockDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean create(Stock stock) {

        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                new NamedParameterJdbcTemplate(jdbcTemplate);

        String sql = "INSERT INTO Stock (warehouse_id, product_sku, quantity, max_quantity, min_quantity)" +
                "VALUES (:warehouseId, :productSku, :quantity, :maxQuantity, :minQuantity)";

        try {
            namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(stock));
        } catch (DataAccessException e){
            return false;
        }
        return true;
    }

    @Override
    public Optional<Stock> findById(Integer warehouseId, Integer productSku) {

        String sql = "SELECT * FROM Stock WHERE warehouse_id = ? AND product_sku = ?";

        try {
            Stock stock = jdbcTemplate.queryForObject(sql, new StockRowMapper(), warehouseId, productSku);
            return Optional.ofNullable(stock);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Stock> findByProduct(Integer productSku) {

        String sql = "SELECT * FROM Stock WHERE product_sku = ?";

        return jdbcTemplate.query(sql, new StockRowMapper(), productSku);
    }

    @Override
    public void update(Stock stock) {

        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                new NamedParameterJdbcTemplate(jdbcTemplate);

        String sql = "UPDATE Stock SET quantity = :quantity, " +
                "max_quantity = :maxQuantity, min_quantity = :minQuantity " +
                "WHERE warehouse_id = :warehouseId AND product_sku = :productSku;";

        namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(stock));
    }
}
