package es.udc.fic.xpn.product;

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
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(Product product) {

        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                new NamedParameterJdbcTemplate(jdbcTemplate);

        String sql = "INSERT INTO Product (sku, name, type) " +
                "VALUES (:sku, :name, :type)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,
                new BeanPropertySqlParameterSource(product),
                keyHolder, new String[]{"sku"});

        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public Optional<Product> findBySku(Integer sku) {

        String sql = "SELECT * FROM Product WHERE sku = ?";

        try {
            Product product = jdbcTemplate.queryForObject(sql, new ProductRowMapper(), sku);
            return Optional.ofNullable(product);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
