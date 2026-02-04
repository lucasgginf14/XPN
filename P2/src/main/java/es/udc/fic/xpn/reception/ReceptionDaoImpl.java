package es.udc.fic.xpn.reception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Objects;

@Repository
public class ReceptionDaoImpl implements ReceptionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(Reception reception) {
        NamedParameterJdbcTemplate namedJdbc = new NamedParameterJdbcTemplate(jdbcTemplate);
        String sql = "INSERT INTO reception (quantity, supplier, date, warehouse_id, product_sku) " +
                "VALUES (:quantity, :supplier, :date, :warehouseId, :productSku)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbc.update(sql, new BeanPropertySqlParameterSource(reception), keyHolder, new String[]{"id"});
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public Optional<Reception> findById(Integer id) {
        String sql = "SELECT * FROM reception WHERE id = ?";
        try {
            Reception reception = jdbcTemplate.queryForObject(sql, new ReceptionRowMapper(), id);
            return Optional.ofNullable(reception);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
