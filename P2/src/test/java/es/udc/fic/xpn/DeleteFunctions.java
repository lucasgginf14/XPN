package es.udc.fic.xpn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DeleteFunctions {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void deleteProductById(Integer sku) {
        String sql = "DELETE FROM Product WHERE sku = ?";
        jdbcTemplate.update(sql, sku);
    }

    public void deleteStockById( Integer warehouseId, Integer productSku) {
        String sql = "DELETE FROM Stock WHERE warehouse_id = ? AND product_sku = ?";
        jdbcTemplate.update(sql, warehouseId, productSku);
    }

    public void deleteReceptionById(Integer id) {
        String sql = "DELETE FROM Reception WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void deleteOrderById(Integer id) {
        String sql = "DELETE FROM Transfer WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
