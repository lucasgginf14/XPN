package es.udc.fic.xpn.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Integer create(Order order) {

        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                new NamedParameterJdbcTemplate(jdbcTemplate);

        String sql = "INSERT INTO transfer (date, quantity, origin_warehouse_id, destination_warehouse_id, product_sku) " +
                "VALUES (:date, :quantity, :originWarehouseId, :destinationWarehouseId, :productSku)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,
                new BeanPropertySqlParameterSource(order),
                keyHolder, new String[] {"id"});

        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public Optional<Order> findById(Integer id) {

        String sql = "SELECT * FROM transfer WHERE id = ?";

        try{
            Order order = jdbcTemplate.queryForObject(sql, new OrderRowMapper(), id);
            if (order != null) {
                if (order.getOriginWarehouseId() <= 0) {
                    order.setOriginWarehouseId(null);
                }
            }
            return Optional.ofNullable(order);
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }
}
